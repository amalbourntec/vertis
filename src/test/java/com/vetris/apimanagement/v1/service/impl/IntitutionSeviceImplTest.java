package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
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
import com.vetris.apimanagement.v1.dto.request.InstitutionRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionRepository;
import com.vetris.apimanagement.v1.service.InstitutionService;
import com.vetris.entity.Institution;
import com.vetris.utils.JWTSecurityContextUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionService.class })
class IntitutionSeviceImplTest {

	@InjectMocks
	InstitutionServiceImpl institutionService;

	@MockBean
	InstitutionRepository institutionRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionRequestDTO institutionDto;

	static Institution institution;

	@BeforeAll
	static void setUp() {
		institutionDto = new InstitutionRequestDTO();
		institutionDto.setCode("code");
		institutionDto.setName("shekar");
		institutionDto.setAddress1("a");
		institutionDto.setAddress2("b");
		institutionDto.setCity("Hyd");
		institutionDto.setStateId(1);
		institutionDto.setCountryId(2);
		institutionDto.setZip("c");
		institutionDto.setEmailId("h");
		institutionDto.setPhoneNo("i");
		institutionDto.setContactPersonName("i");
		institutionDto.setContactPersonMobile("k");
		institutionDto.setIsActive("y");
		institutionDto.setIsNew("y");
		institutionDto.setNotes("l");
		institutionDto.setDiscountPer(9.00);
		institutionDto.setDiscountUpdatedby("n");
		institutionDto.setInfoSource("p");
		institutionDto.setBusinessSourceID(11);
		institutionDto.setOldCode("r");
		institutionDto.setAccountantName("s");
		institutionDto.setPatientIdSrl("3");
		institutionDto.setIsOnline("y");
		institutionDto.setIsEmailVerified("u");
		institutionDto.setIsMobileVerified("v");
		institutionDto.setLinkExistingBillAcct("w");
		institutionDto.setBillingAcctId("x");
		institutionDto.setFormatDcmFiles("z");
		institutionDto.setDcmFilesXferPacsMode("ews");
		institutionDto.setStudyImageManualRecievePath("ghf");
		institutionDto.setConsultApplicable("K");
		institutionDto.setCustomReport("F");
		institutionDto.setImageContentType("z");
		institutionDto.setStorageApplicable("q");
		institutionDto.setXferFilesCompress("j");
		institutionDto.setSubmittedBy("l");
		institutionDto.setFaxRpt("c");
		institutionDto.setFaxNo("6789");
		institutionDto.setRptFormat("z");
		mapper = new ObjectMapper();
		institution = mapper.convertValue(institutionDto, Institution.class);
	}

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testaddInstitutionTest() throws Exception {
		when(objectMapper.convertValue(institutionDto, Institution.class)).thenReturn(institution);
		when(institutionRepository.save(institution)).thenReturn(institution);
		when(jwtSecurityContextUtil.getId()).thenReturn("test1");
		CommonResponseDTO commonResponse = institutionService.addInstitution(institutionDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testgetallInstitutionTest() throws Exception {
		List<Institution> institutions = new ArrayList<Institution>();
		institutions.add(institution);
		when(institutionRepository.findById("def")).thenReturn(Optional.of(institution));
		when(institutionRepository.findAll()).thenReturn(institutions);
		CommonResponseDTO commonResponse = institutionService.getAllInstitutions();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetInstitutionById() throws Exception {
		when(institutionRepository.findById("def")).thenReturn(Optional.of(institution));
		CommonResponseDTO commonResponse = institutionService.getInstitutionById("def");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionService.getInstitutionById("def");
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("Institution not found"));
	}

	@Test
	public void testdeleteinstitution() throws Exception {
		when(institutionRepository.findById("fed")).thenReturn(Optional.of(institution));

		CommonResponseDTO commonResponse = institutionService.deleteInstitution("fed");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testupdateInstitution() throws Exception {
		when(institutionRepository.findById("xyz")).thenReturn(Optional.of(institution));
		when(objectMapper.convertValue(institutionDto, Institution.class)).thenReturn(institution);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionRepository.save(institution)).thenReturn(institution);
		CommonResponseDTO commonResponse = institutionService.updateInstitution(institutionDto, "xyz");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}
}
