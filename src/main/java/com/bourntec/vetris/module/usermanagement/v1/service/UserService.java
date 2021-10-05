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

	public UserResponseDTO addUser(UserRequestDTO user) throws Exception;

	public List<User> getAllUsers() throws Exception;

	public UserResponseDTO getUserById(String id) throws Exception;

	public UserResponseDTO updateUser(UserRequestDTO user, String id) throws Exception;

	public UserResponseDTO deleteUser(String id) throws Exception;
	
}
