package com.vetris.usermanagement.v1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vetris.entity.UserRoles;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.UserRolesRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.UserRolesResponseDTO;
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
	 */
	@Override
	public CommonResponseDTO getUserRolesById(int id) throws Exception {
		UserRolesResponseDTO userRoleRespDTO = new UserRolesResponseDTO();
		CommonResponseDTO resultDto= new CommonResponseDTO();
		Optional<UserRoles> userroles=userRoleRepository.findById(id);
		try {
			if (userroles.isPresent()) {
				UserRoles existingUser = userroles.get();
				BeanUtils.copyProperties(existingUser, userRoleRespDTO);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(userRoleRespDTO);
				resultDto.setMessage("Fetched user role successfully");
			} else {
				resultDto.setStatus(StatusType.Failure.toString());
				resultDto.setMessage("Unable to fetch user role details");
			}
		} catch (BeansException e) {
			e.printStackTrace();
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
		try {
			userRoleRepository.save(resultUserRoles);
			BeanUtils.copyProperties(resultUserRoles, userRoleRespDto);
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setPayload(userRoleRespDto);
			resultDto.setMessage("Saved User Role successfully");
		} catch (BeansException e) {
			e.printStackTrace();
		}

		return resultDto;
	}

	/**
	 * Update user roles by id
	 */
	@Override
	public CommonResponseDTO updateUserRoles(UserRolesRequestDTO userRoleDto, int id) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Optional<UserRoles> existingUserRole = userRoleRepository.findById(id);
		try {
			if (existingUserRole.isPresent()) {
				UserRoles resultUserRole = userRoleDto.toUserRolesRequestModel(userRoleDto);
				resultUserRole.setId(id);
				userRoleRepository.save(resultUserRole);
				BeanUtils.copyProperties(userRoleDto, userRoleRespDto);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(userRoleRespDto);
				resultDto.setMessage("Updated  User Role Succesfully");
			} else {
				resultDto.setStatus(StatusType.Failure.toString());
				resultDto.setMessage("Unable to update use role");
			}
		} catch (BeansException e) {
			e.printStackTrace();
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
		Optional<UserRoles> existingUserRole = userRoleRepository.findById(id);
		try {
			if (existingUserRole.isPresent()) {
				userRoleRepository.deleteById(id);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(userRoleRespDto);
				resultDto.setMessage("Deleted user role successfully");
			} else {
				resultDto.setStatus(StatusType.Failure.toString());
				resultDto.setMessage("Unable to delete user role");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;

	}

}
