package com.bourntec.vetris.module.usermanagement.v1.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bourntec.vetris.entity.User;
import com.bourntec.vetris.module.usermanagement.v1.dto.request.UserRequestDTO;
import com.bourntec.vetris.module.usermanagement.v1.dto.response.UserResponseDTO;
import com.bourntec.vetris.module.usermanagement.v1.repository.UserRepository;
import com.bourntec.vetris.module.usermanagement.v1.service.UserService;
import com.bourntec.vetris.utils.ResponseUtil;

/**
 * Service Impementation for UserManagement
 * 
 * @author Anandu
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * Get all users
	 */
	@Override
	public ResponseUtil getAllUsers() {
		ResponseUtil resultDto= new ResponseUtil();
		List<User> users= userRepository.findAll();
		resultDto.setStatus("success");
		resultDto.setPayload(users);
		resultDto.setMessage("Fetched user successfully");
		return resultDto;
	}

	/**
	 * Getting user by id
	 */
	@Override
	public ResponseUtil getUserById(String id) throws Exception {
		ResponseUtil resultDto= new ResponseUtil();
		UserResponseDTO userRespDTO = new UserResponseDTO();
		if (userRepository.existsById(id)) {
			User existingUser = userRepository.findById(id).orElse(null);
			BeanUtils.copyProperties(existingUser, userRespDTO);
			resultDto.setStatus("success");
			resultDto.setPayload(userRespDTO);
			resultDto.setMessage("Fetched user successfully");
		} else {
			resultDto.setStatus("Error");
			resultDto.setMessage("Unable to fetch user details");
		}
		return resultDto;
	}

	/**
	 * updating the existing user
	 */
	@Override
	public ResponseUtil updateUser(UserRequestDTO userDto, String id) throws Exception {
		ResponseUtil resultDto= new ResponseUtil();
		UserResponseDTO userRespDTO = new UserResponseDTO();
		if (userRepository.existsById(id)) {
			User resultUser = userDto.toModel(userDto);
			resultUser.setId(id);
			userRepository.save(resultUser);
			BeanUtils.copyProperties(resultUser, userRespDTO);
			
			resultDto.setStatus("Success");
			resultDto.setPayload(userRespDTO);
			resultDto.setMessage("Fetched user successfully");
			
		} else {
			resultDto.setStatus("Error");
			resultDto.setMessage("Unable to fetch user details");
		}
		return resultDto;
	}

	/**
	 * Deleting the user by id
	 */
	@Override
	public ResponseUtil deleteUser(String id) throws Exception {
		ResponseUtil resultDto= new ResponseUtil();
		UserResponseDTO userRespDTO = new UserResponseDTO();
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			userRepository.deleteById(id);
			
			resultDto.setStatus("Success");
			resultDto.setPayload(userRespDTO);
			resultDto.setMessage("Deleted user successfully");
		} else {
			resultDto.setStatus("Error");
			resultDto.setMessage("Unable to fetch user details");
		}
		return resultDto;
	}

	/**
	 * Adding a user using request DTO
	 */
	@Override
	public ResponseUtil addUser(UserRequestDTO userDto) throws Exception {
		
		UserResponseDTO userRespDTO = new UserResponseDTO();
		ResponseUtil resultDto= new ResponseUtil();
		
		UUID uuid = UUID.randomUUID();
		User resultUser = userDto.toModel(userDto);
		resultUser.setId(uuid.toString());
		resultUser.setPacsPassword(encodePassword(userDto.getPacsPassword()));
		resultUser.setPassword(encodePassword(userDto.getPassword()));
		userRepository.save(resultUser);
		BeanUtils.copyProperties(resultUser, userRespDTO);
		resultDto.setStatus("Success");
		resultDto.setPayload(userRespDTO);
		resultDto.setMessage("Saved user successfully");
		
		return resultDto;
	}
	
	/**
	 * This method used to encode password 
	 * TODO needs to be changed according real application
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
