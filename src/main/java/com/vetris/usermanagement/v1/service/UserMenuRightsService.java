package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.UserMenuRightsRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * @author anandu
 *
 */
public interface UserMenuRightsService {
	
	public CommonResponseDTO addUserMenuRights(UserMenuRightsRequestDTO requestDTO) throws Exception;

	public CommonResponseDTO getAllUserMenuRights() throws Exception;

	public CommonResponseDTO getUserMenuRightsByUserId(String id) throws Exception;

	public CommonResponseDTO updateUserMenuRights(UserMenuRightsRequestDTO userMenuRequestDto, String id) throws Exception;

	public CommonResponseDTO deleteUserMenuRights(String id) throws Exception;
	
}
