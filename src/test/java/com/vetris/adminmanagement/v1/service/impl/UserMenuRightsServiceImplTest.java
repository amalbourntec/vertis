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
import com.vetris.adminmanagement.v1.dto.request.UserMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.UserMenuRightsResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.UserMenuRightsRepository;
import com.vetris.adminmanagement.v1.repository.UserRepository;
import com.vetris.adminmanagement.v1.service.UserMenuRightsService;
import com.vetris.entity.User;
import com.vetris.entity.UserMenuRights;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for User Role Menu Rights Impl
 * 
 * @author Jose Eldhose
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UserMenuRightsService.class })
public class UserMenuRightsServiceImplTest {
	@InjectMocks
	UserMenuRightsServiceImpl userMenuRightsService;

	@Mock
	UserMenuRightsRepository userMenuRightsRepo;

	@Mock
	UserRepository userRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static UserMenuRightsRequestDTO userMenuRightsDto;

	static UserMenuRights userMenuRights;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		userMenuRightsDto = new UserMenuRightsRequestDTO();
		userMenuRightsDto.setMenuId(1);
		userMenuRightsDto.setUserId("123");
		mapper = new ObjectMapper();
		userMenuRights = mapper.convertValue(userMenuRightsDto, UserMenuRights.class);
	}

	@Test
	public void testUserMenuRightsPostTest() throws Exception {
		User user = new User();
		when(objectMapper.convertValue(userMenuRightsDto, UserMenuRights.class)).thenReturn(userMenuRights);
		when(userMenuRightsRepo.save(userMenuRights)).thenReturn(userMenuRights);
		when(userRepository.findById(userMenuRightsDto.getUserId())).thenReturn(Optional.of(user));
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = userMenuRightsService.addUserMenuRights(userMenuRightsDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetUserMenuRightsById() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		UserMenuRights userMenuRights1 = mapper.convertValue(userMenuRightsDto, UserMenuRights.class);
		UserMenuRightsResponseDTO UserMenuRightsRespDto = mapper.convertValue(userMenuRights1,
				UserMenuRightsResponseDTO.class);
		when(userMenuRightsRepo.findById(123)).thenReturn(Optional.of(userMenuRights1));
		when(objectMapper.convertValue(userMenuRights1, UserMenuRightsResponseDTO.class))
				.thenReturn(UserMenuRightsRespDto);
		CommonResponseDTO commonResponse = userMenuRightsService.getUserMenuRightsByUserId(123);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetAllUserMenuRightsTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserMenuRights userMenuRights1 = mapper.convertValue(userMenuRightsDto, UserMenuRights.class);
		List<UserMenuRights> UserMenuRights = new ArrayList<UserMenuRights>();
		UserMenuRights.add(userMenuRights1);
		when(userMenuRightsRepo.findAll()).thenReturn(UserMenuRights);
		CommonResponseDTO commonResponse = userMenuRightsService.getAllUserMenuRights();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			userMenuRightsService.getUserMenuRightsByUserId(123);
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("user menu rights  not found"));
	}

	@Test
	public void testdeleteUserMenuRights() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserMenuRights UserMenuRights = mapper.convertValue(userMenuRightsDto, UserMenuRights.class);
		when(userMenuRightsRepo.findById(123)).thenReturn(Optional.of(UserMenuRights));
		CommonResponseDTO commonResponse = userMenuRightsService.deleteUserMenuRights(123);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testupdateUserMenuRights() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserMenuRights UserMenuRights3 = mapper.convertValue(userMenuRightsDto, UserMenuRights.class);
		when(userMenuRightsRepo.findById(123)).thenReturn(Optional.of(UserMenuRights3));
		when(objectMapper.convertValue(userMenuRightsDto, UserMenuRights.class)).thenReturn(UserMenuRights3);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(userMenuRightsRepo.save(UserMenuRights3)).thenReturn(userMenuRights);
		CommonResponseDTO commonResponse = userMenuRightsService.updateUserMenuRights(userMenuRightsDto, 123);
		assertEquals("Success", commonResponse.getStatus());
	}
}
