package com.bourntec.vetris.module.usermanagement.v1.service.impl;

import java.util.List;
import java.util.Optional;

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
 * @author Anandu
 *
 */

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	/**
	 *Get all users
	 */
	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	/**
	 *Getting user by id
	 */
	@Override
	public UserResponseDTO getUserById(Integer id) {
		UserResponseDTO userRespDTO=new UserResponseDTO();
		try {	
			if (userRepository.existsById(id)) {
				User existingUser = userRepository.findById(id).orElse(null);
				BeanUtils.copyProperties(existingUser, userRespDTO);
				userRespDTO.setResponseMessage("Fetched User Successfully");
			}else {
				userRespDTO.setResponseMessage("No User found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRespDTO;
	}

	/**
	 *updating the existing user
	 */
	@Override
	public UserResponseDTO updateUser(UserRequestDTO userDto, Integer id) {
		UserResponseDTO userRespDTO=new UserResponseDTO();
		try {
			if (userRepository.existsById(id)) {
				User resultUser = userDto.toModel(userDto);
				resultUser.setId(id);
				userRepository.save(resultUser);
				BeanUtils.copyProperties(resultUser, userRespDTO);
				userRespDTO.setResponseMessage("Updated User Successfully");
			}else {
				userRespDTO.setResponseMessage("No User found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userRespDTO;
	}

	/**
	 *Deleting the user by id
	 */
	@Override
	public UserResponseDTO deleteUser(Integer id) {
		UserResponseDTO userRespDTO=new UserResponseDTO();
		try {
			Optional<User> existingUser = userRepository.findById(id);
			if (existingUser.isPresent()) {
				userRepository.deleteById(id);
				userRespDTO.setResponseMessage("Deleted User Successfully");
			}else{
				userRespDTO.setResponseMessage("No user found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userRespDTO;
	}

	/**
	 *Adding a user using request DTO
	 */
	@Override
	public UserResponseDTO addUser(UserRequestDTO userDto) {
		UserResponseDTO userRespDTO=new UserResponseDTO();
		try {
			User resultUser = userDto.toModel(userDto);
			userRepository.save(resultUser);
			BeanUtils.copyProperties(resultUser, userRespDTO);
			userRespDTO.setResponseMessage("Saved User successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRespDTO;
	}
	

}
