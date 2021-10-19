package com.vetris.usermanagement.v1.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.User;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.UserRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.UserResponseDTO;
import com.vetris.usermanagement.v1.repository.UserRepository;
import com.vetris.usermanagement.v1.service.UserService;
import com.vetris.utils.JWTSecurityContextUtil;

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
		List<UserResponseDTO> resultresponseDto= new ArrayList<>();
		if (users.isEmpty()) {
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setPayload("");
			resultDto.setMessage("No user found");
		} else {
			users.stream().forEach(userItem->{
				resultresponseDto.add(objectMapper.convertValue(userItem,UserResponseDTO.class));
			});
			
			resultDto.setStatus(StatusType.Success.toString());
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
		User existingUser = userRepository.findById(id).orElse(null);
		if (Objects.nonNull(existingUser) && id.equals(existingUser.getId())) {
			UserResponseDTO userRespDTO = objectMapper.convertValue(existingUser, UserResponseDTO.class);
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setPayload(userRespDTO);
			resultDto.setMessage("Fetched user successfully");
		} else {
			resultDto.setStatus(StatusType.Failure.toString());
			resultDto.setMessage("Unable to fetch user details");
		}
		return resultDto;
	}

	/**
	 * updating the existing user
	 */
	@Override
	public CommonResponseDTO updateUser(UserRequestDTO userDto, String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		if (userRepository.existsById(id)) {
			User resultUser = objectMapper.convertValue(userDto, User.class);
			resultUser.setId(id);
			resultUser.setUpdateBy(jwtSecurityContextUtil.getId());
			resultUser.setDateUpdated(jwtSecurityContextUtil.getCurrentDate());
			userRepository.save(resultUser);
			UserResponseDTO userRespDTO = objectMapper.convertValue(resultUser, UserResponseDTO.class);

			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setPayload(userRespDTO);
			resultDto.setMessage("Fetched user successfully");

		} else {
			resultDto.setStatus(StatusType.Failure.toString());
			resultDto.setMessage("Unable to fetch user details");
		}
		return resultDto;
	}

	/**
	 * Deleting the user by id
	 */
	@Override
	public CommonResponseDTO deleteUser(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			userRepository.deleteById(id);
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setMessage("Deleted user successfully");
		} else {
			resultDto.setStatus(StatusType.Failure.toString());
			resultDto.setMessage("Unable to fetch user details");
		}
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
		// Set User details
		resultUser.setId(uuid.toString());
		resultUser.setPassword(encodePassword(userDto.getPassword()));
		resultUser.setPacsPassword(userDto.getUserRoleId().equals("5")?encodePassword(userDto.getPacsPassword()):" ");
		resultUser.setCreatedBy(jwtSecurityContextUtil.getId());
		resultUser.setDateCreated(jwtSecurityContextUtil.getCurrentDate());
		
		userRepository.save(resultUser);
		BeanUtils.copyProperties(resultUser, userRespDTO);
		resultDto.setStatus(StatusType.Success.toString());
		resultDto.setPayload(userRespDTO);
		resultDto.setMessage("Saved user successfully");

		return resultDto;
	}

	/**
	 * This method used to encode password TODO needs to be changed according real
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

}
