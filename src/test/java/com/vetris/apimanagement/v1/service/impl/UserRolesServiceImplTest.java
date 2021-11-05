package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.UserRolesRequestDTO;
import com.vetris.adminmanagement.v1.repository.UserRolesRepostitory;
import com.vetris.adminmanagement.v1.service.UserRolesService;
import com.vetris.adminmanagement.v1.service.impl.UserRolesServiceImpl;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.UserRolesResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.entity.UserRoles;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for User Roles Impl
 * 
 * @author Jose Eldhose
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UserRolesService.class })
public class UserRolesServiceImplTest {

	@InjectMocks
	UserRolesServiceImpl userRolesService;

	@MockBean
	UserRolesRepostitory userRolesRepostitory;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static UserRolesRequestDTO userRolesDto;

	static UserRoles userRoles;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		userRolesDto = new UserRolesRequestDTO();
		userRolesDto.setCode("21");
		userRolesDto.setIsActive("Y");
		userRolesDto.setIsVisible("Y");
		userRolesDto.setName("Manu");
		userRolesDto.setSysDefined("Y");
		mapper = new ObjectMapper();
		userRoles = mapper.convertValue(userRolesDto, UserRoles.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void userRolesPostTest() throws Exception {
		when(objectMapper.convertValue(userRolesDto, UserRoles.class)).thenReturn(userRoles);
		when(userRolesRepostitory.save(userRoles)).thenReturn(userRoles);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = userRolesService.addUserRoles(userRolesDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void userRolesTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserRoles userRoles1 = mapper.convertValue(userRolesDto, UserRoles.class);
		List<UserRoles> userRoles = new ArrayList<UserRoles>();
		userRoles.add(userRoles1);
		when(userRolesRepostitory.findById(1)).thenReturn(Optional.of(userRoles1));
		when(userRolesRepostitory.findAll()).thenReturn(userRoles);
		CommonResponseDTO commonResponse = userRolesService.getAllUserRoles();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void userRolesById() throws Exception {
		System.out.println("Inside by Id");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Inside by Id2");
		UserRoles userRoles1 = mapper.convertValue(userRolesDto, UserRoles.class);
		UserRolesResponseDTO userRoleRespDTO = mapper.convertValue(userRoles1, UserRolesResponseDTO.class);
		System.out.println("Inside by Id3");
		when(userRolesRepostitory.findById(1)).thenReturn(Optional.of(userRoles1));
		when(objectMapper.convertValue(userRoles1, UserRolesResponseDTO.class)).thenReturn(userRoleRespDTO);
		System.out.println("Inside by Id4");
		CommonResponseDTO commonResponse = userRolesService.getUserRolesById(1);
		System.out.println("Inside by Id5");
		System.out.println(commonResponse.getMessage());
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			userRolesService.getUserRolesById(1);
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("User not found"));
	}

	@Test
	public void testDeleteuserRoles() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserRoles userRoles = mapper.convertValue(userRolesDto, UserRoles.class);
		when(userRolesRepostitory.findById(1)).thenReturn(Optional.of(userRoles));

		CommonResponseDTO commonResponse = userRolesService.deleteUserRoles(1);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testUpdateuserRoles() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserRoles userRoles3 = mapper.convertValue(userRolesDto, UserRoles.class);
		userRoles3.setId(1);
		when(userRolesRepostitory.findById(1)).thenReturn(Optional.of(userRoles3));
		when(objectMapper.convertValue(userRolesDto, UserRoles.class)).thenReturn(userRoles);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(userRolesRepostitory.save(userRoles3)).thenReturn(userRoles);

		CommonResponseDTO commonResponse = userRolesService.updateUserRoles(userRolesDto, 1);
		assertEquals("Success", commonResponse.getStatus());
	}
}
