package com.vetris.adminmanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
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
import com.vetris.apimanagement.v1.dto.request.PhysicianRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.PhysicianResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.PhysicianRepository;
import com.vetris.apimanagement.v1.service.PhysicianService;
import com.vetris.apimanagement.v1.service.impl.PhysicianServiceImpl;
import com.vetris.entity.Physicians;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for Physician Impl
 * 
 * @author Jose Eldhose
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PhysicianService.class })
public class PhysicianServiceImplTest {
	@InjectMocks
	PhysicianServiceImpl physiciansService;

	@Mock
	PhysicianRepository physicianRepo;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static PhysicianRequestDTO physicianDto;

	static Physicians physicians;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		physicianDto = new PhysicianRequestDTO();
		physicianDto.setCode("21");
		physicianDto.setIsActive("Y");
		physicianDto.setName("Manu");
		physicianDto.setAddress1("abs");
		physicianDto.setCity("ywtyey");
		physicianDto.setCountryId(01);
		physicianDto.setCredentials("jfje");
		physicianDto.setEmailId("abc@gmail.com");
		physicianDto.setFname("Ma");
		physicianDto.setInstitutionId("1");
		physicianDto.setLname("Nu");
		physicianDto.setMobileNo("567252");
		physicianDto.setPhoneNo("2222");
		physicianDto.setStateId(1);
		physicianDto.setZip("zip");
		mapper = new ObjectMapper();
		physicians = mapper.convertValue(physicianDto, Physicians.class);
	}

	@Test
	public void physiciansPostTest() throws Exception {
		when(objectMapper.convertValue(physicianDto, Physicians.class)).thenReturn(physicians);
		when(physicianRepo.save(physicians)).thenReturn(physicians);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = physiciansService.addPhysician(physicianDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void physiciansTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Physicians physicians1 = mapper.convertValue(physicianDto, Physicians.class);
		List<Physicians> physicians = new ArrayList<Physicians>();
		physicians.add(physicians1);
		when(physicianRepo.findById("1")).thenReturn(Optional.of(physicians1));
		when(physicianRepo.findAll()).thenReturn(physicians);
		CommonResponseDTO commonResponse = physiciansService.getAllPhysician();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void physiciansById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Physicians physicians1 = mapper.convertValue(physicianDto, Physicians.class);
		PhysicianResponseDTO physicianRespDto = new PhysicianResponseDTO();
		physicianRespDto.setCreatedBy("test");
		physicianRespDto.setUpdateBy("test");
		physicianRespDto.setDateCreated(new Date());
		physicianRespDto.setDateupdated(new Date());
		physicianRespDto.setCode("abc");
		physicianRespDto.setIsActive("Y");
		physicianRespDto.setName("Manu");
		physicianRespDto.setAddress1("abs");
		physicianRespDto.setCity("ywtyey");
		physicianRespDto.setCountryId(01);
		physicianRespDto.setCredentials("jfje");
		physicianRespDto.setEmailId("abc@gmail.com");
		physicianRespDto.setFname("Ma");
		physicianRespDto.setInstitutionId("1");
		physicianRespDto.setLname("Nu");
		physicianRespDto.setMobileNo("567252");
		physicianRespDto.setPhoneNo("2222");
		physicianRespDto.setStateId(1);
		physicianRespDto.setZip("zip");
		// PhysicianResponseDTO userRoleRespDTO = mapper.convertValue(physicians1,
		// PhysicianResponseDTO.class);
		when(physicianRepo.findById("1")).thenReturn(Optional.of(physicians1));
		when(objectMapper.convertValue(physicians1, PhysicianResponseDTO.class)).thenReturn(physicianRespDto);
		CommonResponseDTO commonResponse = physiciansService.getPhysicianById("1");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			physiciansService.getPhysicianById("1");
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("Physician not found"));
	}

	@Test
	public void testDeletephysicians() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Physicians physicians = mapper.convertValue(physicianDto, Physicians.class);
		when(physicianRepo.findById("1")).thenReturn(Optional.of(physicians));

		CommonResponseDTO commonResponse = physiciansService.deletePhysician("1");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testUpdatephysicians() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Physicians physicians3 = mapper.convertValue(physicianDto, Physicians.class);
		physicians3.setId("1");
		when(physicianRepo.findById("1")).thenReturn(Optional.of(physicians3));
		when(objectMapper.convertValue(physicianDto, Physicians.class)).thenReturn(physicians);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(physicianRepo.save(physicians3)).thenReturn(physicians);

		CommonResponseDTO commonResponse = physiciansService.updatePhysician(physicianDto, "1");
		assertEquals("Success", commonResponse.getStatus());
	}
}
