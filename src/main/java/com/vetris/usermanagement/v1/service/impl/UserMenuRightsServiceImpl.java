package com.vetris.usermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.UserMenuRights;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.UserMenuRightsRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.UserMenuRightsResponseDTO;
import com.vetris.usermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.usermanagement.v1.repository.UserMenuRightsRepository;
import com.vetris.usermanagement.v1.repository.UserRepository;
import com.vetris.usermanagement.v1.service.UserMenuRightsService;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * @author anandu
 *user menu rights service impl
 */
@Service
public class UserMenuRightsServiceImpl implements UserMenuRightsService{
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	UserMenuRightsRepository userMenuRightsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	/**
	 *adding a user menu right
	 */
	@Override
	public CommonResponseDTO addUserMenuRights(UserMenuRightsRequestDTO requestDTO) throws Exception {

		UserMenuRightsResponseDTO userRightsRespDTO = new UserMenuRightsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();		
		userRepository.findById(requestDTO.getUserId())
		.orElseThrow(() -> new ResourceNotFoundException("User" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
			UserMenuRights resultUserMenuRights = objectMapper.convertValue(requestDTO, UserMenuRights.class);
			resultUserMenuRights.setCreatedBy(jwtSecurityContextUtil.getId());
			resultUserMenuRights = userMenuRightsRepository.save(resultUserMenuRights);
			BeanUtils.copyProperties(resultUserMenuRights, userRightsRespDTO);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(userRightsRespDTO);
			resultDto.setMessage("Saved user menu rights successfully");
		
		return resultDto;
	}

	/**
	 * returns all user menu rights
	 */
	@Override
	public CommonResponseDTO getAllUserMenuRights() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<UserMenuRights> userMenuRights = userMenuRightsRepository.findAll();
		List<UserMenuRightsResponseDTO> resultresponseDto = new ArrayList<>();
		if (userMenuRights.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No user menu rights found");
		} else {
			userMenuRights.stream().forEach(existingUserMenuRights -> {
				resultresponseDto.add(objectMapper.convertValue(existingUserMenuRights, UserMenuRightsResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched user menu rights successfully");
		}

		return resultDto;
	}

	/**
	 * returns the user menu rights of one user
	 */
	@Override
	public CommonResponseDTO getUserMenuRightsByUserId(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UserMenuRights existingUserMenu = userMenuRightsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user menu rights " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		UserMenuRightsResponseDTO userMenuRespDTO = objectMapper.convertValue(existingUserMenu, UserMenuRightsResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userMenuRespDTO);
		resultDto.setMessage("Fetched user menu rights successfully");

		return resultDto;
	}

	/**
	 * update the menu rights of existing user
	 */
	@Override
	public CommonResponseDTO updateUserMenuRights(UserMenuRightsRequestDTO userMenuRequestDto, String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UserMenuRights resultUserMenu =userMenuRightsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user menu rights" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
			BeanUtils.copyProperties(userMenuRequestDto, resultUserMenu);
			resultUserMenu.setUpdateBy(jwtSecurityContextUtil.getId());
			resultUserMenu = userMenuRightsRepository.save(resultUserMenu);
			UserMenuRightsResponseDTO userMenuRespDTO = objectMapper.convertValue(resultUserMenu, UserMenuRightsResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(userMenuRespDTO);
			resultDto.setMessage("Fetched user menu rights successfully");
		return resultDto;
	}

	/**
	 * delete the user rights of an existing user
	 * 
	 */
	@Override
	public CommonResponseDTO deleteUserMenuRights(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		userMenuRightsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user menu rights " + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		userMenuRightsRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted user menu rights successfully");
		return resultDto;
	}

}
