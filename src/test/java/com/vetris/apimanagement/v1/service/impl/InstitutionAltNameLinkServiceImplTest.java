package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.vetris.apimanagement.v1.dto.request.InstitutionAltNameLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.repository.InstitutionAltNameLinkRepository;
import com.vetris.entity.InstitutionAltNameLink;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * @author Aldrin Sunny
 * 
 *Test class for InstitutionAltNameLinkServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionAltNameLink.class })
class InstitutionAltNameLinkServiceImplTest {

	@InjectMocks
	InstitutionAltNameLinkServiceImpl institutionAltNameLinkServiceImpl;

	@MockBean
	InstitutionAltNameLinkRepository institutionAltNameLinkRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionAltNameLinkRequestDTO institutionAltNameLinkRequestDto;

	static InstitutionAltNameLink institutionAltNameLink;

	@BeforeAll
	static void setUp() {
		System.out.println("test");
		institutionAltNameLinkRequestDto = new InstitutionAltNameLinkRequestDTO();
		institutionAltNameLinkRequestDto.setInstitutionId("abc");
		institutionAltNameLinkRequestDto.setAlternateName("Bosch");
		mapper = new ObjectMapper();
		institutionAltNameLink = mapper.convertValue(institutionAltNameLinkRequestDto, InstitutionAltNameLink.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveInstitutionAltNameLinkTest() throws Exception {
		when(objectMapper.convertValue(institutionAltNameLinkRequestDto, InstitutionAltNameLink.class))
				.thenReturn(institutionAltNameLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionAltNameLinkRepository.save(institutionAltNameLink)).thenReturn(institutionAltNameLink);
		
		CommonResponseDTO commonResponseDTO = institutionAltNameLinkServiceImpl
				.addInstitutionAltName(institutionAltNameLinkRequestDto);
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getInstitutionAltNameLinkByIdTest() throws Exception {
		when(institutionAltNameLinkRepository.findById("abc")).thenReturn(Optional.of(institutionAltNameLink));
		CommonResponseDTO commonResponseDTO = institutionAltNameLinkServiceImpl.getInstitutionAltNameById("abc");
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getAllInstitutionAltNameLinkTest() throws Exception {
		List<InstitutionAltNameLink> institutionAltNameLinkList = new ArrayList<>();
		institutionAltNameLinkList.add(institutionAltNameLink);
		when(institutionAltNameLinkRepository.findAll()).thenReturn(institutionAltNameLinkList);
		CommonResponseDTO commonResponseDTO = institutionAltNameLinkServiceImpl.getAllInstitutionAltName();
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void deleteInstitutionAltNameLinkByIdTest() throws Exception {
		when(institutionAltNameLinkRepository.findById("abc")).thenReturn(Optional.of(institutionAltNameLink));

		CommonResponseDTO commonResponse = institutionAltNameLinkServiceImpl.deleteInstitutionAltName("abc");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void updateInstitutionAltNameLinkTest() throws Exception {
		when(institutionAltNameLinkRepository.findById("abc")).thenReturn(Optional.of(institutionAltNameLink));
		when(objectMapper.convertValue(institutionAltNameLinkRequestDto, InstitutionAltNameLink.class))
				.thenReturn(institutionAltNameLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionAltNameLinkRepository.save(institutionAltNameLink)).thenReturn(institutionAltNameLink);

		CommonResponseDTO commonResponse = institutionAltNameLinkServiceImpl
				.updateInstitutionAltName(institutionAltNameLinkRequestDto, "abc");
		assertEquals("Success", commonResponse.getStatus());
	}

}
