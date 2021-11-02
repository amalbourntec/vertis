package com.vetris.adminmanagement.v1.service;

import com.vetris.adminmanagement.v1.dto.request.UserMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.request.UserRoleMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;

/**
 * @author anandu
 *
 */
public interface UserRoleMenuRightsService {
	
	public CommonResponseDTO addUserRoleMenuRights(UserRoleMenuRightsRequestDTO requestDTO) throws Exception;

	public CommonResponseDTO getAllUserRoleMenuRights() throws Exception;

	public CommonResponseDTO getUserRoleMenuRightsByUserId(Integer id) throws Exception;

	public CommonResponseDTO updateUserRoleMenuRights(UserRoleMenuRightsRequestDTO userRoleMenuRequestDto, Integer id) throws Exception;

	public CommonResponseDTO deleteUserRoleMenuRights(Integer id) throws Exception;

}
