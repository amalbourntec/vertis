package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.UserRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for UserManagement
 * 
 * @author Anandu
 *
 */

public interface UserService {

	public CommonResponseDTO addUser(UserRequestDTO user) throws Exception;

	public CommonResponseDTO getAllUsers() throws Exception;

	public CommonResponseDTO getUserById(String id) throws Exception;

	public CommonResponseDTO updateUser(UserRequestDTO user, String id) throws Exception;

	public CommonResponseDTO deleteUser(String id) throws Exception;
	
}
