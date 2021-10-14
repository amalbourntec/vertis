package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.UserRolesRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.UserRolesResponseDTO;

/**
 * Service for USerRoles
 * @author Jose Eldhose
 *
 */

public interface UserRolesService {

	public CommonResponseDTO getUserRolesById(int id) throws Exception;

	public CommonResponseDTO getAllUserRoles() throws Exception;

	public CommonResponseDTO addUserRoles(UserRolesRequestDTO data) throws Exception;

	public CommonResponseDTO updateUserRoles(UserRolesRequestDTO data, int id) throws Exception;

	public CommonResponseDTO deleteUserRoles(int id) throws Exception;

}
