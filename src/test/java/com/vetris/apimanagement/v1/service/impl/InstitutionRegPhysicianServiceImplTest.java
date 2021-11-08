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
import com.vetris.apimanagement.v1.dto.request.InstitutionRegPhysicianLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionRegPhysicianLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionRegPhysicianLinkService;
import com.vetris.entity.InstitutionRegPhysicianLink;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for InstitutionRegPhysicianLink Impl
 * 
 * @author Jose Eldhose
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionRegPhysicianLinkService.class })
public class InstitutionRegPhysicianServiceImplTest {

	@InjectMocks
	InstitutionRegPhysicianLinkServiceImpl regPhysicianService;

	@MockBean
	InstitutionRegPhysicianLinkRepository regPhysicianRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionRegPhysicianLinkRequestDTO regPhysicianDto;

	static InstitutionRegPhysicianLink regPhysician;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		regPhysicianDto = new InstitutionRegPhysicianLinkRequestDTO();
		regPhysicianDto.setInstitutionId("21");
		regPhysicianDto.setPhysicianCredentials("2323");
		regPhysicianDto.setPhysicianEmail("abc@gmail.com");
		regPhysicianDto.setPhysicianFname("Manu");
		regPhysicianDto.setPhysicianLname("Thambi");
		regPhysicianDto.setPhysicianMobile("2323232");
		regPhysicianDto.setPhysicianName("Manu Thambi");
		mapper = new ObjectMapper();
		regPhysician = mapper.convertValue(regPhysicianDto, InstitutionRegPhysicianLink.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void regPhysicianPostTest() throws Exception {
		when(objectMapper.convertValue(regPhysicianDto, InstitutionRegPhysicianLink.class)).thenReturn(regPhysician);

		when(regPhysicianRepository.save(regPhysician)).thenReturn(regPhysician);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = regPhysicianService.addPhysician(regPhysicianDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testgetallInstitutionTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegPhysicianLink regPhysician1 = mapper.convertValue(regPhysicianDto	, InstitutionRegPhysicianLink.class);
		List<InstitutionRegPhysicianLink> regPhysician = new ArrayList<InstitutionRegPhysicianLink>();
		regPhysician.add(regPhysician1);
		when(regPhysicianRepository.findById("def")).thenReturn(Optional.of(regPhysician1));
		when(regPhysicianRepository.findAll()).thenReturn(regPhysician);
		CommonResponseDTO commonResponse = regPhysicianService.getAllPhysicians();
		assertEquals("Success", commonResponse.getStatus());
	}
	
	@Test
	public void testGetregPhysicianById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegPhysicianLink regPhysician1 = mapper.convertValue(regPhysicianDto, InstitutionRegPhysicianLink.class);
		when(regPhysicianRepository.findById("55b51e98-07dc-4c51-ad85-f0a7148270f9"))
				.thenReturn(Optional.of(regPhysician1));

		CommonResponseDTO commonResponse = regPhysicianService.getPhysicianById("55b51e98-07dc-4c51-ad85-f0a7148270f9");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			regPhysicianService.getPhysicianById("9");
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("Physician not found"));
	}

	@Test
	public void testDeleteRegPhysician() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegPhysicianLink regPhysician = mapper.convertValue(regPhysicianDto, InstitutionRegPhysicianLink.class);
		when(regPhysicianRepository.findById("bac")).thenReturn(Optional.of(regPhysician));

		CommonResponseDTO commonResponse = regPhysicianService.deletePhysician("bac");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testUpdateRegPhysician() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegPhysicianLink regPhysician3 = mapper.convertValue(regPhysicianDto, InstitutionRegPhysicianLink.class);
		regPhysician3.setInstitutionId("abc");
		when(regPhysicianRepository.findById("cab")).thenReturn(Optional.of(regPhysician3));
		when(objectMapper.convertValue(regPhysicianDto, InstitutionRegPhysicianLink.class)).thenReturn(regPhysician);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(regPhysicianRepository.save(regPhysician3)).thenReturn(regPhysician);

		CommonResponseDTO commonResponse = regPhysicianService.updatePhysician(regPhysicianDto, "cab");
		assertEquals("Success", commonResponse.getStatus());
	}
}

