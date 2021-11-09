package com.vetris.adminmanagement.v1.service.impl;

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
import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRuleHdrRequestDTO;
import com.vetris.adminmanagement.v1.repository.CaseNotificationRuleHdrRepository;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.entity.CaseNotificationRuleHdr;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * @author Aldrin Sunny
 * 
 *         Test class for CaseNotificationRuleHdrServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CaseNotificationRuleHdr.class })
class CaseNotificationRuleHdrServiceImplTest {

	@InjectMocks
	CaseNotificationRuleHdrServiceImpl caseNotificationRuleHdrServiceImpl;

	@MockBean
	CaseNotificationRuleHdrRepository caseNotificationRuleHdrRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static CaseNotificationRuleHdrRequestDTO caseNotificationRuleHdrRequestDto;

	static CaseNotificationRuleHdr caseNotificationRuleHdr;

	@BeforeAll
	static void setUp() {
		System.out.println("test");
		caseNotificationRuleHdrRequestDto = new CaseNotificationRuleHdrRequestDTO();
		caseNotificationRuleHdrRequestDto.setRuleNo(12);
		caseNotificationRuleHdrRequestDto.setForSmsVerification("Y");
		caseNotificationRuleHdrRequestDto.setIsActive("Y");
		caseNotificationRuleHdrRequestDto.setNotifyByTime("Y");
		caseNotificationRuleHdrRequestDto.setPacsStatusId(1);
		caseNotificationRuleHdrRequestDto.setPriorityId(1);
		caseNotificationRuleHdrRequestDto.setRuleDesc("default");
		caseNotificationRuleHdrRequestDto.setTimeEllapsedMins(10);
		caseNotificationRuleHdrRequestDto.setTimeLeftMins(10);
		mapper = new ObjectMapper();
		caseNotificationRuleHdr = mapper.convertValue(caseNotificationRuleHdrRequestDto, CaseNotificationRuleHdr.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveCaseNotificationRuleHdrTest() throws Exception {
		when(objectMapper.convertValue(caseNotificationRuleHdrRequestDto, CaseNotificationRuleHdr.class))
				.thenReturn(caseNotificationRuleHdr);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(caseNotificationRuleHdrRepository.save(caseNotificationRuleHdr)).thenReturn(caseNotificationRuleHdr);

		CommonResponseDTO commonResponseDTO = caseNotificationRuleHdrServiceImpl
				.addCaseNotificationRuleHdr(caseNotificationRuleHdrRequestDto);
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getCaseNotificationRuleHdrByIdTest() throws Exception {
		when(caseNotificationRuleHdrRepository.findById(12)).thenReturn(Optional.of(caseNotificationRuleHdr));
		CommonResponseDTO commonResponseDTO = caseNotificationRuleHdrServiceImpl.getCaseNotificationRuleHdrById(12);
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getAllCaseNotificationRuleHdrTest() throws Exception {
		List<CaseNotificationRuleHdr> caseNotificationRuleHdrList = new ArrayList<>();
		caseNotificationRuleHdrList.add(caseNotificationRuleHdr);
		when(caseNotificationRuleHdrRepository.findAll()).thenReturn(caseNotificationRuleHdrList);
		CommonResponseDTO commonResponseDTO = caseNotificationRuleHdrServiceImpl.getAllCaseNotificationRuleHdr();
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void deleteCaseNotificationRuleHdrByIdTest() throws Exception {
		when(caseNotificationRuleHdrRepository.findById(12)).thenReturn(Optional.of(caseNotificationRuleHdr));

		CommonResponseDTO commonResponse = caseNotificationRuleHdrServiceImpl.deleteCaseNotificationRuleHdr(12);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void updateCaseNotificationRuleHdrTest() throws Exception {
		when(caseNotificationRuleHdrRepository.findById(12)).thenReturn(Optional.of(caseNotificationRuleHdr));
		when(objectMapper.convertValue(caseNotificationRuleHdrRequestDto, CaseNotificationRuleHdr.class))
				.thenReturn(caseNotificationRuleHdr);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(caseNotificationRuleHdrRepository.save(caseNotificationRuleHdr)).thenReturn(caseNotificationRuleHdr);

		CommonResponseDTO commonResponse = caseNotificationRuleHdrServiceImpl
				.updateCaseNotificationRuleHdr(caseNotificationRuleHdrRequestDto, 12);
		assertEquals("Success", commonResponse.getStatus());
	}
}
