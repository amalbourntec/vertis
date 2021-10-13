package com.bourntec.vetris.module.usermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bourntec.vetris.entity.UserRoles;
import com.bourntec.vetris.module.usermanagement.v1.dto.request.UserRolesRequestDTO;
import com.bourntec.vetris.module.usermanagement.v1.dto.response.UserRolesResponseDTO;
import com.bourntec.vetris.module.usermanagement.v1.repository.UserRolesRepostitory;
import com.bourntec.vetris.module.usermanagement.v1.service.UserRolesService;

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
	public UserRolesResponseDTO getUserRolesById(int id) throws Exception {
		UserRolesResponseDTO userRoleRespDTO = new UserRolesResponseDTO();
		try {
			if (userRoleRepository.existsById(id)) {
				UserRoles existingUser = userRoleRepository.findById(id).orElse(null);
				BeanUtils.copyProperties(existingUser, userRoleRespDTO);
				userRoleRespDTO.setResponseMessage("Fetched User Role Successfully");
			} else {
				userRoleRespDTO.setResponseMessage("No User found");
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return userRoleRespDTO;
	}
	
	

	/**
	 * Get all user roles
	 */
	@Override
	public List<UserRolesResponseDTO> getAllUserRoles() throws Exception {
		List<UserRolesResponseDTO> userRolesDto = new ArrayList<>();
		List<UserRoles> userrolesList = this.userRoleRepository.findAll();
		UserRolesResponseDTO userRoleNotFound = new UserRolesResponseDTO();
		try {
			if (userrolesList.isEmpty()) {
				userRoleNotFound.setResponseMessage("No User Role Found");
				userRolesDto.add(userRoleNotFound);
			} else {
				for (UserRoles userroles:userrolesList) {
					UserRolesResponseDTO userRoleRespDTO = new UserRolesResponseDTO();
					BeanUtils.copyProperties(userroles, userRoleRespDTO);
					userRoleRespDTO.setResponseMessage("Fetched User Role Successfully");
					userRolesDto.add(userRoleRespDTO);
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return userRolesDto;
	}

	/**
	 * Insert user roles using request DTO
	 */
	@Override
	public UserRolesResponseDTO addUserRoles(UserRolesRequestDTO userRoleDto) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		UserRoles resultUserRoles = userRoleDto.toUserRolesRequestModel(userRoleDto);
		try {
			userRoleRepository.save(resultUserRoles);
			BeanUtils.copyProperties(resultUserRoles, userRoleRespDto);
			userRoleRespDto.setResponseMessage("Saved User Role successfully");
		} catch (BeansException e) {
			e.printStackTrace();
		}

		return userRoleRespDto;
	}

	/**
	 * Update user roles by id
	 */
	@Override
	public UserRolesResponseDTO updateUserRoles(UserRolesRequestDTO userRoleDto, int id) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		Optional<UserRoles> existingUserRole = userRoleRepository.findById(id);
		try {
			if (existingUserRole.isPresent()) {
				UserRoles resultUserRole = userRoleDto.toUserRolesRequestModel(userRoleDto);
				resultUserRole.setId(id);
				userRoleRepository.save(resultUserRole);
				BeanUtils.copyProperties(userRoleDto, userRoleRespDto);
				userRoleRespDto.setResponseMessage("Updated  User Role Succesfully");
			} else {
				userRoleRespDto.setResponseMessage("No User Role Found");
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}

		return userRoleRespDto;
	}

	/**
	 * Delete the user role by id
	 */
	@Override
	public UserRolesResponseDTO deleteUserRoles(int id) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		Optional<UserRoles> existingUserRole = userRoleRepository.findById(id);
		try {
			if (existingUserRole.isPresent()) {
				userRoleRepository.deleteById(id);
				userRoleRespDto.setResponseMessage("Deleted User Role Succesfully");
			} else {
				userRoleRespDto.setResponseMessage("No User Role Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userRoleRespDto;

	}

}
