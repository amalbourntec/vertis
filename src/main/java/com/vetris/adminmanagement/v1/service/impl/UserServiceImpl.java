package com.vetris.adminmanagement.v1.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.vetris.adminmanagement.v1.dto.request.UserRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.UserResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.UserRepository;
import com.vetris.adminmanagement.v1.service.UserService;
import com.vetris.entity.User;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;
import com.vetris.utils.MfaUtil;

/**
 * Service Implementation for UserManagement
 * 
 * @author Anandu
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Get all users
	 */
	@Override
	public CommonResponseDTO getAllUsers() {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<User> users = userRepository.findAll();
		List<UserResponseDTO> resultresponseDto = new ArrayList<>();
		if (users.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No user found");
		} else {
			users.stream().forEach(existingUser -> {
				resultresponseDto.add(objectMapper.convertValue(existingUser, UserResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched user successfully");
		}

		return resultDto;
	}

	/**
	 * Getting user by id
	 */
	@Override
	public CommonResponseDTO getUserById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		UserResponseDTO userRespDTO = objectMapper.convertValue(existingUser, UserResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userRespDTO);
		resultDto.setMessage("Fetched user successfully");

		return resultDto;
	}

	/**
	 * updating the existing user
	 */
	@Override
	public CommonResponseDTO updateUser(UserRequestDTO userDto, String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		User resultUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		BeanUtils.copyProperties(userDto, resultUser);
		resultUser.setUpdateBy(jwtSecurityContextUtil.getId());
		resultUser.setPassword(encodePassword(userDto.getPassword()));
		resultUser.setPacsPassword(userDto.getUserRoleId().equals(5) ? encodePassword(userDto.getPacsPassword()) : " ");
		resultUser = userRepository.save(resultUser);
		UserResponseDTO userRespDTO = objectMapper.convertValue(resultUser, UserResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userRespDTO);
		resultDto.setMessage("Fetched user successfully");

		return resultDto;
	}

	/**
	 * Deleting the user by id
	 */
	@Override
	public CommonResponseDTO deleteUser(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		System.out.println(user.getEmailId());
		userRepository.delete(user);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted user successfully");
		return resultDto;
	}

	/**
	 * Adding a user using request DTO
	 */
	@Override
	public CommonResponseDTO addUser(UserRequestDTO userDto) throws Exception {

		UserResponseDTO userRespDTO = new UserResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		UUID uuid = UUID.randomUUID();
		User resultUser = objectMapper.convertValue(userDto, User.class);
		resultUser.setId(uuid.toString());
		resultUser.setPassword(encodePassword(userDto.getPassword()));
		resultUser.setPacsPassword(userDto.getUserRoleId().equals(5) ? encodePassword(userDto.getPacsPassword()) : " ");
		//Generate secret key for MFA opted users
		if(userDto.getEnableMfa().equals("Y")) {
			resultUser.setEnableMfa(userDto.getEnableMfa());
			resultUser.setSecretKey(generateSecretKey(userDto.getLoginId()));
		}
		resultUser.setCreatedBy(jwtSecurityContextUtil.getId());
		resultUser = userRepository.save(resultUser);
		BeanUtils.copyProperties(resultUser, userRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userRespDTO);
		resultDto.setMessage("Saved user successfully");

		return resultDto;
	}

	/**
	 * This method used to encode password 
	 * TODO needs to be changed according real
	 * application
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String encodePassword(String password) throws NoSuchAlgorithmException {
		String encryptedpassword = null;

		MessageDigest m = MessageDigest.getInstance("MD5");

		m.update(password.getBytes());

		byte[] bytes = m.digest();

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		encryptedpassword = s.toString();

		return encryptedpassword;
	}

	/**
	 * This method is to generate secret key for MFA opted users
	 * @param userId
	 * @return
	 */
	private String generateSecretKey(String userId) {

		String mfaSecretKey = null;
		String companyName = "vetris";
		String barCodeData = null;

		mfaSecretKey = MfaUtil.generateSecretKey();
		barCodeData = MfaUtil.getGoogleAuthenticatorBarCode(mfaSecretKey, userId, companyName);

		Path path = Paths.get(FileUtils.getTempDirectory().getAbsolutePath(), userId + "_QR_CODE_" + System.nanoTime());
		String tmpdir = null;
		try {
			tmpdir = Files.createDirectories(path).toFile().getAbsolutePath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File generateFile = new File(tmpdir, userId + "_QR_CODE" + ".png");

		try {
			MfaUtil.createQRCode(barCodeData, generateFile.getPath(), 400, 400);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		
		//TODO need to delete the temp QR code file after sending it.

		return mfaSecretKey;

	}

}
