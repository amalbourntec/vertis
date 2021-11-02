package com.vetris.adminmanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.UserRoleMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.UserRoleMenuRightsResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.UserRoleMenuRightsRepository;
import com.vetris.adminmanagement.v1.repository.UserRolesRepostitory;
import com.vetris.adminmanagement.v1.service.UserRoleMenuRightsService;
import com.vetris.entity.UserRoleMenuRights;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

@Service
public class UserRoleMenuRightsServiceImpl  implements UserRoleMenuRightsService{

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	UserRoleMenuRightsRepository userRoleMenuRightsRepository;
	
	@Autowired
	UserRolesRepostitory userRoleRepository;
	
	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;
	
	@Override
	public CommonResponseDTO addUserRoleMenuRights(UserRoleMenuRightsRequestDTO requestDTO) throws Exception {
		UserRoleMenuRightsResponseDTO userRightsRespDTO = new UserRoleMenuRightsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();		
		userRoleRepository.findById(requestDTO.getUserRoleId())
		.orElseThrow(() -> new ResourceNotFoundException("User Role" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
			UserRoleMenuRights resultUserRoleMenuRights = objectMapper.convertValue(requestDTO, UserRoleMenuRights.class);
			resultUserRoleMenuRights.setCreatedBy(jwtSecurityContextUtil.getId());
			resultUserRoleMenuRights = userRoleMenuRightsRepository.save(resultUserRoleMenuRights);
			BeanUtils.copyProperties(resultUserRoleMenuRights, userRightsRespDTO);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(userRightsRespDTO);
			resultDto.setMessage("Saved user role menu rights successfully");
		
		return resultDto;
	}

	@Override
	public CommonResponseDTO getAllUserRoleMenuRights() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<UserRoleMenuRights> userRoleMenuRights = userRoleMenuRightsRepository.findAll();
		List<UserRoleMenuRightsResponseDTO> resultresponseDto = new ArrayList<>();
		if (userRoleMenuRights.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No user role menu rights found");
		} else {
			userRoleMenuRights.stream().forEach(existingUserRoleMenuRights -> {
				resultresponseDto.add(objectMapper.convertValue(existingUserRoleMenuRights, UserRoleMenuRightsResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched user role menu rights successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getUserRoleMenuRightsByUserId(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UserRoleMenuRights existingUserMenu = userRoleMenuRightsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user menu rights " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		UserRoleMenuRightsResponseDTO userMenuRespDTO = objectMapper.convertValue(existingUserMenu, UserRoleMenuRightsResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userMenuRespDTO);
		resultDto.setMessage("Fetched user role menu rights successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO updateUserRoleMenuRights(UserRoleMenuRightsRequestDTO userRoleMenuRequestDto, Integer id)
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UserRoleMenuRights resultUserRoleMenu =userRoleMenuRightsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user menu rights" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
			BeanUtils.copyProperties(userRoleMenuRequestDto, resultUserRoleMenu);
			resultUserRoleMenu.setUpdateBy(jwtSecurityContextUtil.getId());
			resultUserRoleMenu = userRoleMenuRightsRepository.save(resultUserRoleMenu);
			UserRoleMenuRightsResponseDTO userMenuRespDTO = objectMapper.convertValue(resultUserRoleMenu, UserRoleMenuRightsResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(userMenuRespDTO);
			resultDto.setMessage("updated user role menu rights successfully");
		return resultDto;
	}

	@Override
	public CommonResponseDTO deleteUserRoleMenuRights(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		userRoleMenuRightsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user menu rights " + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		userRoleMenuRightsRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted user role menu rights successfully");
		return resultDto;
	}

}
