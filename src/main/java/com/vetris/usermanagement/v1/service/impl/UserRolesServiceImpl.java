package com.vetris.usermanagement.v1.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vetris.entity.UserRoles;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.UserRolesRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.UserRolesResponseDTO;
import com.vetris.usermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.usermanagement.v1.repository.UserRolesRepostitory;
import com.vetris.usermanagement.v1.service.UserRolesService;

/**
 * Service Impementation for USerRoles
 * 
 * @author Jose Eldhose
 *
 */
@Service
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	UserRolesRepostitory userRoleRepository;

	/**
	 * Getting user roles by id
	 * @throws ResourceNotFoundException 
	 */
	@Override
	public CommonResponseDTO getUserRolesById(int id) throws Exception {
		UserRolesResponseDTO userRoleRespDTO = new UserRolesResponseDTO();
		CommonResponseDTO resultDto= new CommonResponseDTO();
		UserRoles userroles=userRoleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User"+ErrorCodes.DATA_NOT_FOUND.getMessage()));
		try {
			BeanUtils.copyProperties(userroles, userRoleRespDTO);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(userRoleRespDTO);
				resultDto.setMessage("Fetched user role successfully");
		} catch (Exception e) {
			throw new Exception();
		} 
		return resultDto;
	}
	
	

	/**
	 * Get all user roles
	 */
	@Override
	public CommonResponseDTO getAllUserRoles() throws Exception {
		List<UserRoles> userrolesList = this.userRoleRepository.findAll();
		CommonResponseDTO resultDto= new CommonResponseDTO();
		try {
			if (userrolesList.isEmpty()) {
				resultDto.setStatus(StatusType.Failure.toString());
				resultDto.setPayload("");
				resultDto.setMessage("No user role found");
			} else {
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(userrolesList);
				resultDto.setMessage("Fetched user roles successfully");
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return resultDto;
	}

	/**
	 * Insert user roles using request DTO
	 */
	@Override
	public CommonResponseDTO addUserRoles(UserRolesRequestDTO userRoleDto) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		UserRoles resultUserRoles = userRoleDto.toUserRolesRequestModel(userRoleDto);
		CommonResponseDTO resultDto = new CommonResponseDTO();
	//	try {
			userRoleRepository.save(resultUserRoles);
			BeanUtils.copyProperties(resultUserRoles, userRoleRespDto);
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setPayload(userRoleRespDto);
			resultDto.setMessage("Saved User Role successfully");
//		} catch (Exception e) {
//			//e.printStackTrace();
//			throw new Exception();
//		}

		return resultDto;
	}

	/**
	 * Update user roles by id
	 */
	@Override
	public CommonResponseDTO updateUserRoles(UserRolesRequestDTO userRoleDto, int id) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		userRoleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		try {
				UserRoles resultUserRole = userRoleDto.toUserRolesRequestModel(userRoleDto);
				resultUserRole.setId(id);
				userRoleRepository.save(resultUserRole);
				BeanUtils.copyProperties(userRoleDto, userRoleRespDto);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(userRoleRespDto);
				resultDto.setMessage("Updated  User Role Succesfully");
		} catch (Exception e) {
			throw new Exception();
		}

		return resultDto;
	}

	/**
	 * Delete the user role by id
	 */
	@Override
	public CommonResponseDTO deleteUserRoles(int id) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		userRoleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		try {
				userRoleRepository.deleteById(id);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(userRoleRespDto);
				resultDto.setMessage("Deleted user role successfully");
		} catch (Exception e) {
			throw new Exception();
		}

		return resultDto;

	}

}
