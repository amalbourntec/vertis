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
import com.vetris.apimanagement.v1.dto.request.InstitutionSalespersonLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.repository.InstitutionSalespersonLinkRepository;
import com.vetris.entity.InstitutionSalespersonLink;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * @author Aldrin Sunny
 * 
 * Test class for InstitutionSalespersonLinkServiceImpl
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionSalespersonLink.class })
class InstitutionSalespersonLinkServiceImplTest {

	@InjectMocks
	InstitutionSalespersonLinkServiceImpl institutionSalespersonLinkServiceImpl;

	@MockBean
	InstitutionSalespersonLinkRepository institutionSalespersonLinkRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionSalespersonLinkRequestDTO institutionSalespersonLinkRequestDto;

	static InstitutionSalespersonLink institutionSalespersonLink;

	@BeforeAll
	static void setUp() {
		System.out.println("test");
		institutionSalespersonLinkRequestDto = new InstitutionSalespersonLinkRequestDTO();
		institutionSalespersonLinkRequestDto.setSalespersonId("S1234");
		institutionSalespersonLinkRequestDto.setInstitutionId("abc");
		institutionSalespersonLinkRequestDto.setBillingAccountId("account1");
		institutionSalespersonLinkRequestDto.setCommission1stYr(45.6);
		institutionSalespersonLinkRequestDto.setCommission2ndYr(56.8);
		institutionSalespersonLinkRequestDto.setSalespersonCredentials("credential");
		institutionSalespersonLinkRequestDto.setSalespersonEmail("alan@gmail.com");
		institutionSalespersonLinkRequestDto.setSalespersonFName("Alan");
		institutionSalespersonLinkRequestDto.setSalespersonLName("Job");
		institutionSalespersonLinkRequestDto.setSalespersonLoginEmail("alan@123");
		institutionSalespersonLinkRequestDto.setSalespersonMobile("45698744");
		institutionSalespersonLinkRequestDto.setSalespersonName("Alan Job");
		institutionSalespersonLinkRequestDto.setSalespersonPacsPassword("dhasjk");
		institutionSalespersonLinkRequestDto.setSalespersonPacsUserId("alan");
		institutionSalespersonLinkRequestDto.setSalespersonUserId("alan1234");
		mapper = new ObjectMapper();
		institutionSalespersonLink = mapper.convertValue(institutionSalespersonLinkRequestDto,
				InstitutionSalespersonLink.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveInstitutionSalespersonLinkTest() throws Exception {
		when(objectMapper.convertValue(institutionSalespersonLinkRequestDto, InstitutionSalespersonLink.class))
				.thenReturn(institutionSalespersonLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionSalespersonLinkRepository.save(institutionSalespersonLink))
				.thenReturn(institutionSalespersonLink);
		
		CommonResponseDTO commonResponseDTO = institutionSalespersonLinkServiceImpl
				.addSalesperson(institutionSalespersonLinkRequestDto);
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getInstitutionSalespersonLinkByIdTest() throws Exception {
		when(institutionSalespersonLinkRepository.findById("S1234"))
				.thenReturn(Optional.of(institutionSalespersonLink));
		CommonResponseDTO commonResponseDTO = institutionSalespersonLinkServiceImpl.getSalespersonById("S1234");
		assertEquals("Success", commonResponseDTO.getStatus());
	}
	
	@Test
	public void getAllInstitutionSalespersonLinkTest() throws Exception {
		List<InstitutionSalespersonLink> institutionSalespersonLinkList=new ArrayList<>();
		institutionSalespersonLinkList.add(institutionSalespersonLink);
		when(institutionSalespersonLinkRepository.findAll())
				.thenReturn(institutionSalespersonLinkList);
		CommonResponseDTO commonResponseDTO = institutionSalespersonLinkServiceImpl.getAllSalesperson();
		assertEquals("Success", commonResponseDTO.getStatus());
	}
	
	@Test
	public void deleteInstitutionSalespersonLinkByIdTest() throws Exception {
		when(institutionSalespersonLinkRepository.findById("S1234"))
				.thenReturn(Optional.of(institutionSalespersonLink));

		CommonResponseDTO commonResponse = institutionSalespersonLinkServiceImpl.deleteSalesperson("S1234");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void updateInstitutionSalespersonLinkTest() throws Exception {
		when(institutionSalespersonLinkRepository.findById("S1234"))
				.thenReturn(Optional.of(institutionSalespersonLink));
		when(objectMapper.convertValue(institutionSalespersonLinkRequestDto, InstitutionSalespersonLink.class))
				.thenReturn(institutionSalespersonLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionSalespersonLinkRepository.save(institutionSalespersonLink))
				.thenReturn(institutionSalespersonLink);

		CommonResponseDTO commonResponse = institutionSalespersonLinkServiceImpl
				.updateSalesperson(institutionSalespersonLinkRequestDto, "S1234");
		assertEquals("Success", commonResponse.getStatus());
	}
}
