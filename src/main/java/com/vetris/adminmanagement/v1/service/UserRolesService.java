package com.vetris.adminmanagement.v1.service;

import com.vetris.adminmanagement.v1.dto.request.UserRolesRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for USerRoles
 * @author Jose Eldhose
 *
 */

public interface UserRolesService {

	public CommonResponseDTO getUserRolesById(int id) throws Throwable;

	public CommonResponseDTO getAllUserRoles() throws Throwable;

	public CommonResponseDTO addUserRoles(UserRolesRequestDTO data) throws Throwable;

	public CommonResponseDTO updateUserRoles(UserRolesRequestDTO data, int id) throws Throwable;

	public CommonResponseDTO deleteUserRoles(int id) throws Throwable;

}
