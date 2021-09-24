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
	public Optional<User> getUserById(Integer id) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser!=null) {
			return this.userRepository.findById(id);
		}
		return null;
	}

	/**
	 *updating the existing user
	 */
	@Override
	public User updateUser(User user, Integer id) {
		User existingUser = userRepository.findById(id).orElse(null);
		existingUser.setAge(user.getAge());
		existingUser.setCity(user.getCity());
		existingUser.setCountry(user.getCountry());
		existingUser.setEmailId(user.getEmailId());
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setPhoneNumber(user.getPhoneNumber());
		userRepository.save(existingUser);
		return existingUser;
	}

	/**
	 *Deleting the user by id
	 */
	@Override
	public String deleteUser(Integer id) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser!=null) {
			userRepository.deleteById(id);
		}
		return "User Deleted";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRespDTO;
	}
	

}
