package com.vetris.adminmanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.UserRoleMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.UserRoleMenuRightsResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.UserRoleMenuRightsRepository;
import com.vetris.adminmanagement.v1.repository.UserRolesRepostitory;
import com.vetris.adminmanagement.v1.service.UserRoleMenuRightsService;
import com.vetris.entity.UserRoleMenuRights;
import com.vetris.entity.UserRoles;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for User Menu Rights Impl
 * 
 * @author Jose Eldhose
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UserRoleMenuRightsService.class })
public class UserRoleMenuRightsTest {
	@InjectMocks
	UserRoleMenuRightsServiceImpl userRoleMenuRightsService;

	@Mock
	UserRoleMenuRightsRepository userRoleMenuRightsRepo;

	@Mock
	UserRolesRepostitory userRoleRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static UserRoleMenuRightsRequestDTO userRoleMenuRightsDto;

	static UserRoleMenuRights userRoleMenuRights;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		userRoleMenuRightsDto = new UserRoleMenuRightsRequestDTO();
		userRoleMenuRightsDto.setMenuId(1);
		userRoleMenuRightsDto.setUserRoleId(2);
		mapper = new ObjectMapper();
		userRoleMenuRights = mapper.convertValue(userRoleMenuRightsDto, UserRoleMenuRights.class);
	}

	@Test
	public void testUserRoleMenuRightsPostTest() throws Exception {
		UserRoles userRoles = new UserRoles();
		userRoles.setCode("abc");
		when(objectMapper.convertValue(userRoleMenuRightsDto, UserRoleMenuRights.class)).thenReturn(userRoleMenuRights);
		when(userRoleMenuRightsRepo.save(userRoleMenuRights)).thenReturn(userRoleMenuRights);
		when(userRoleRepository.findById(userRoleMenuRightsDto.getUserRoleId())).thenReturn(Optional.of(userRoles));
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = userRoleMenuRightsService.addUserRoleMenuRights(userRoleMenuRightsDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetUserRoleMenuRightsById() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		UserRoleMenuRights userRoleMenuRights1 = mapper.convertValue(userRoleMenuRightsDto, UserRoleMenuRights.class);
		UserRoleMenuRightsResponseDTO userRoleMenuRightsRespDto = mapper.convertValue(userRoleMenuRights1,
				UserRoleMenuRightsResponseDTO.class);
		when(userRoleMenuRightsRepo.findById(2)).thenReturn(Optional.of(userRoleMenuRights1));
		when(objectMapper.convertValue(userRoleMenuRights1, UserRoleMenuRightsResponseDTO.class))
				.thenReturn(userRoleMenuRightsRespDto);
		CommonResponseDTO commonResponse = userRoleMenuRightsService.getUserRoleMenuRightsByUserId(2);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetAllUserRoleMenuRightsTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserRoleMenuRights userRoleMenuRights1 = mapper.convertValue(userRoleMenuRightsDto, UserRoleMenuRights.class);
		List<UserRoleMenuRights> userRoleMenuRights = new ArrayList<UserRoleMenuRights>();
		userRoleMenuRights.add(userRoleMenuRights1);
		when(userRoleMenuRightsRepo.findAll()).thenReturn(userRoleMenuRights);
		CommonResponseDTO commonResponse = userRoleMenuRightsService.getAllUserRoleMenuRights();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			userRoleMenuRightsService.getUserRoleMenuRightsByUserId(2);
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("user menu rights  not found"));
	}

	@Test
	public void testdeleteUserRoleMenuRights() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserRoleMenuRights userRoleMenuRights = mapper.convertValue(userRoleMenuRightsDto, UserRoleMenuRights.class);
		when(userRoleMenuRightsRepo.findById(2)).thenReturn(Optional.of(userRoleMenuRights));
		CommonResponseDTO commonResponse = userRoleMenuRightsService.deleteUserRoleMenuRights(2);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testupdateUserRoleMenuRights() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserRoleMenuRights userRoleMenuRights3 = mapper.convertValue(userRoleMenuRightsDto, UserRoleMenuRights.class);
		when(userRoleMenuRightsRepo.findById(2)).thenReturn(Optional.of(userRoleMenuRights3));
		when(objectMapper.convertValue(userRoleMenuRightsDto, UserRoleMenuRights.class))
				.thenReturn(userRoleMenuRights3);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(userRoleMenuRightsRepo.save(userRoleMenuRights3)).thenReturn(userRoleMenuRights);
		CommonResponseDTO commonResponse = userRoleMenuRightsService.updateUserRoleMenuRights(userRoleMenuRightsDto, 2);
		assertEquals("Success", commonResponse.getStatus());
	}
}
