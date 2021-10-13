package com.bourntec.vetris.module.usermanagement.v1.service;

import java.util.List;
import com.bourntec.vetris.module.usermanagement.v1.dto.request.UserRolesRequestDTO;
import com.bourntec.vetris.module.usermanagement.v1.dto.response.UserRolesResponseDTO;

/**
 * Service for USerRoles
 * @author Jose Eldhose
 *
 */

public interface UserRolesService {

	public UserRolesResponseDTO getUserRolesById(int id) throws Exception;

	public List<UserRolesResponseDTO> getAllUserRoles() throws Exception;

	public UserRolesResponseDTO addUserRoles(UserRolesRequestDTO data) throws Exception;

	public UserRolesResponseDTO updateUserRoles(UserRolesRequestDTO data, int id) throws Exception;

	public UserRolesResponseDTO deleteUserRoles(int id) throws Exception;

}
