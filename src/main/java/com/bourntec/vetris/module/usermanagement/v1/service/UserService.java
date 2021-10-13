package com.bourntec.vetris.module.usermanagement.v1.service;

import com.bourntec.vetris.module.usermanagement.v1.dto.request.UserRequestDTO;
import com.bourntec.vetris.utils.ResponseUtil;


/**
 * Service for UserManagement
 * 
 * @author Anandu
 *
 */

public interface UserService {

	public ResponseUtil addUser(UserRequestDTO user) throws Exception;

	public ResponseUtil getAllUsers() throws Exception;

	public ResponseUtil getUserById(String id) throws Exception;

	public ResponseUtil updateUser(UserRequestDTO user, String id) throws Exception;

	public ResponseUtil deleteUser(String id) throws Exception;
	
}
