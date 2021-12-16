package com.vetris.adminmanagement.v1.service;

import com.vetris.adminmanagement.v1.dto.request.UserMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;

/**
 * @author anandu
 *
 */
public interface UserMenuRightsService {
	
	public CommonResponseDTO addUserMenuRights(UserMenuRightsRequestDTO requestDTO) throws Exception;

	public CommonResponseDTO getAllUserMenuRights() throws Exception;

	public CommonResponseDTO getUserMenuRightsByUserId(Integer id) throws Exception;

	public CommonResponseDTO updateUserMenuRights(UserMenuRightsRequestDTO userMenuRequestDto, Integer id) throws Exception;

	public CommonResponseDTO deleteUserMenuRights(Integer id) throws Exception;
	
}
