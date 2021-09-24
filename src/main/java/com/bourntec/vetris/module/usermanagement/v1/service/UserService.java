package com.bourntec.vetris.module.usermanagement.v1.service;

import java.util.List;
import java.util.Optional;

import com.bourntec.vetris.entity.User;
import com.bourntec.vetris.module.usermanagement.v1.dto.request.UserRequestDTO;
import com.bourntec.vetris.module.usermanagement.v1.dto.response.UserResponseDTO;


/**
 * Service for UserManagement
 * 
 * @author Anandu
 *
 */

public interface UserService {

	public UserResponseDTO addUser(UserRequestDTO user);

	public List<User> getAllUsers();

	public UserResponseDTO getUserById(Integer id);

	public UserResponseDTO updateUser(UserRequestDTO user, Integer id);

	public UserResponseDTO deleteUser(Integer id);
	
}
