package com.bourntec.vetris.module.usermanagement.v1.service.impl;

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
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	/**
	 * Getting user by id
	 */
	@Override
	public UserResponseDTO getUserById(String id) throws Exception {
		UserResponseDTO userRespDTO = new UserResponseDTO();
		if (userRepository.existsById(id)) {
			User existingUser = userRepository.findById(id).orElse(null);
			BeanUtils.copyProperties(existingUser, userRespDTO);
			userRespDTO.setResponseMessage("Fetched User Successfully");
		} else {
			userRespDTO.setResponseMessage("No User found");
		}
		return userRespDTO;
	}

	/**
	 * updating the existing user
	 */
	@Override
	public UserResponseDTO updateUser(UserRequestDTO userDto, String id) throws Exception {
		UserResponseDTO userRespDTO = new UserResponseDTO();
		if (userRepository.existsById(id)) {
			User resultUser = userDto.toModel(userDto);
			resultUser.setId(id);
			userRepository.save(resultUser);
			BeanUtils.copyProperties(resultUser, userRespDTO);
			userRespDTO.setResponseMessage("Updated User Successfully");
		} else {
			userRespDTO.setResponseMessage("No User found");
		}
		return userRespDTO;
	}

	/**
	 * Deleting the user by id
	 */
	@Override
	public UserResponseDTO deleteUser(String id) throws Exception {
		UserResponseDTO userRespDTO = new UserResponseDTO();
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			userRepository.deleteById(id);
			userRespDTO.setResponseMessage("Deleted User Successfully");
		} else {
			userRespDTO.setResponseMessage("No user found");
		}
		return userRespDTO;
	}

	/**
	 * Adding a user using request DTO
	 */
	@Override
	public UserResponseDTO addUser(UserRequestDTO userDto) throws Exception {
		
		UserResponseDTO userRespDTO = new UserResponseDTO();
		
		UUID uuid = UUID.randomUUID();
		User resultUser = userDto.toModel(userDto);
		resultUser.setId(uuid.toString());
		userRepository.save(resultUser);
		BeanUtils.copyProperties(resultUser, userRespDTO);
		userRespDTO.setResponseMessage("Saved User successfully");
		
		return userRespDTO;
	}

}
