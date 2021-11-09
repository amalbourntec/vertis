package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.vetris.apimanagement.v1.dto.request.InstitutionRegModalityLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionRegModalityLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionRegModalityLinkService;
import com.vetris.entity.InstitutionRegModalityLink;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for InstitutionRegModalityLink Impl
 * 
 * @author Jose Eldhose
 * 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionRegModalityLinkService.class })
public class InstitutionRegModalityServiceImplTest {

	@InjectMocks
	InstitutionRegModalityLinkServiceImpl reModalityService;

	@Mock
	InstitutionRegModalityLinkRepository regModalityRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionRegModalityLinkRequestDTO regModalityDto;

	static InstitutionRegModalityLink regModality;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		regModalityDto = new InstitutionRegModalityLinkRequestDTO();
		regModalityDto.setInstitutionId("21");
		mapper = new ObjectMapper();
		regModality = mapper.convertValue(regModalityDto, InstitutionRegModalityLink.class);
	}

	@Test
	public void testRegModalityPostTest() throws Exception {
		when(objectMapper.convertValue(regModalityDto, InstitutionRegModalityLink.class)).thenReturn(regModality);

		when(regModalityRepository.save(regModality)).thenReturn(regModality);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = reModalityService.addRegModality(regModalityDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetRegModalityById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegModalityLink regModality1 = mapper.convertValue(regModalityDto, InstitutionRegModalityLink.class);
		when(regModalityRepository.findById("55b51e98-07dc-4c51-ad85-f0a7148270f9"))
				.thenReturn(Optional.of(regModality1));

		CommonResponseDTO commonResponse = reModalityService.getRegModalityById("55b51e98-07dc-4c51-ad85-f0a7148270f9");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetallInstitutionTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegModalityLink regModality1 = mapper.convertValue(regModalityDto, InstitutionRegModalityLink.class);
		List<InstitutionRegModalityLink> regModality = new ArrayList<InstitutionRegModalityLink>();
		regModality.add(regModality1);
		when(regModalityRepository.findById("abc")).thenReturn(Optional.of(regModality1));
		when(regModalityRepository.findAll()).thenReturn(regModality);
		CommonResponseDTO commonResponse = reModalityService.getAllRegModality();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			reModalityService.getRegModalityById("55b51e98-07dc-4c51-ad85-f0a7148270f9");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase(" not found"));
	}

	@Test
	public void testDeleteRegModality() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegModalityLink regModality = mapper.convertValue(regModalityDto, InstitutionRegModalityLink.class);
		when(regModalityRepository.findById("bac")).thenReturn(Optional.of(regModality));

		CommonResponseDTO commonResponse = reModalityService.deleteInstitutionRegModality("bac");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testUpdateRegModality() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionRegModalityLink regModality3 = mapper.convertValue(regModalityDto, InstitutionRegModalityLink.class);
		regModality3.setModalityId("cab");
		when(regModalityRepository.findById("cab")).thenReturn(Optional.of(regModality3));
		when(objectMapper.convertValue(regModalityDto, InstitutionRegModalityLink.class)).thenReturn(regModality);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(regModalityRepository.save(regModality3)).thenReturn(regModality);

		CommonResponseDTO commonResponse = reModalityService.updateInstitutionRegModality(regModalityDto, "cab");
		assertEquals("Success", commonResponse.getStatus());
	}
}
