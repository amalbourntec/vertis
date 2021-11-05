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
import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRulesRequestDTO;
import com.vetris.adminmanagement.v1.repository.CaseNotificationRulesRepository;
import com.vetris.adminmanagement.v1.service.CaseNotificationRulesService;
import com.vetris.adminmanagement.v1.service.impl.CaseNotificationRulesServiceImpl;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.entity.CaseNotificationRules;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for Case Notification Rules Impl
 * 
 * @author Jose Eldhose
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CaseNotificationRulesService.class })
public class CaseNotificationRulesTest {

	@InjectMocks
	CaseNotificationRulesServiceImpl CaseNotificationRulesService;

	@MockBean
	CaseNotificationRulesRepository CaseNotificationRulesRepo;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static CaseNotificationRulesRequestDTO caseNotificationRulesDto;

	static CaseNotificationRules caseNotificationRules;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		caseNotificationRulesDto = new CaseNotificationRulesRequestDTO();
		caseNotificationRulesDto.setDefaultUserRole("SUPP");
		caseNotificationRulesDto.setIsActive("Y");
		caseNotificationRulesDto.setPacsStatusId(1);
		caseNotificationRulesDto.setPriorityId(10);
		caseNotificationRulesDto.setRuleDesc("Support");
		caseNotificationRulesDto.setRuleNo(101);
		caseNotificationRulesDto.setSecondLevelAlertReceipientId("1213");
		caseNotificationRulesDto.setTimeEllapsedMins(5);
		mapper = new ObjectMapper();
		caseNotificationRules = mapper.convertValue(caseNotificationRulesDto, CaseNotificationRules.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testcaseNotificationRulesPostTest() throws Exception {
		when(objectMapper.convertValue(caseNotificationRulesDto, CaseNotificationRules.class))
				.thenReturn(caseNotificationRules);

		when(CaseNotificationRulesRepo.save(caseNotificationRules)).thenReturn(caseNotificationRules);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = CaseNotificationRulesService
				.addCaseNotificationRules(caseNotificationRulesDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetcaseNotificationRulesByRuleNo() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRules caseNotificationRules1 = mapper.convertValue(caseNotificationRulesDto,
				CaseNotificationRules.class);
		List<CaseNotificationRules> caseNotificationRulesList = new ArrayList<CaseNotificationRules>();
		caseNotificationRulesList.add(caseNotificationRules1);
		when(CaseNotificationRulesRepo.findByRuleNo(1)).thenReturn(caseNotificationRulesList);

		CommonResponseDTO commonResponse = CaseNotificationRulesService.getCaseNotificationRulesByRuleNo(1);
		System.out.println(commonResponse.getMessage());
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetcaseNotificationRulesByPriorityId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRules caseNotificationRules1 = mapper.convertValue(caseNotificationRulesDto,
				CaseNotificationRules.class);
		List<CaseNotificationRules> caseNotificationRulesList = new ArrayList<CaseNotificationRules>();
		caseNotificationRulesList.add(caseNotificationRules1);
		when(CaseNotificationRulesRepo.findByPriorityId(1)).thenReturn(caseNotificationRulesList);

		CommonResponseDTO commonResponse = CaseNotificationRulesService.getCaseNotificationRulesByPriorityId(1);
		System.out.println(commonResponse.getMessage());
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetcaseNotificationRulesByPacsId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRules caseNotificationRules1 = mapper.convertValue(caseNotificationRulesDto,
				CaseNotificationRules.class);
		List<CaseNotificationRules> caseNotificationRulesList = new ArrayList<CaseNotificationRules>();
		caseNotificationRulesList.add(caseNotificationRules1);
		when(CaseNotificationRulesRepo.findByPacsStatusId(1)).thenReturn(caseNotificationRulesList);

		CommonResponseDTO commonResponse = CaseNotificationRulesService.getCaseNotificationRulesByPacsStatusId(1);
		System.out.println(commonResponse.getMessage());
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetallInstitutionTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRules caseNotificationRules1 = mapper.convertValue(caseNotificationRulesDto,
				CaseNotificationRules.class);
		List<CaseNotificationRules> caseNotificationRules = new ArrayList<CaseNotificationRules>();
		caseNotificationRules.add(caseNotificationRules1);
		when(CaseNotificationRulesRepo.findByRuleNoANDPacsStatusIdANDPriorityId(2, 1, 100))
				.thenReturn(Optional.of(caseNotificationRules1));
		when(CaseNotificationRulesRepo.findAll()).thenReturn(caseNotificationRules);
		CommonResponseDTO commonResponse = CaseNotificationRulesService.getAllCaseNotificationRules(2, 1, 100);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			CaseNotificationRulesService.getAllCaseNotificationRules(1, 2, 3);
		});
		assertTrue(exception.getMessage().equalsIgnoreCase(" not found"));
	}

	@Test
	public void testdeletecaseNotificationRules() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRules caseNotificationRules = mapper.convertValue(caseNotificationRulesDto,
				CaseNotificationRules.class);
		when(CaseNotificationRulesRepo.findByRuleNoANDPacsStatusIdANDPriorityId(1, 2, 3))
				.thenReturn(Optional.of(caseNotificationRules));
		CommonResponseDTO commonResponse = CaseNotificationRulesService.deleteCaseNotificationRules(1, 2, 3);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testupdatecaseNotificationRules() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRules caseNotificationRules3 = mapper.convertValue(caseNotificationRulesDto,
				CaseNotificationRules.class);
		when(CaseNotificationRulesRepo.findByRuleNoANDPacsStatusIdANDPriorityId(2, 1, 100))
				.thenReturn(Optional.of(caseNotificationRules3));
		when(objectMapper.convertValue(caseNotificationRulesDto, CaseNotificationRules.class))
				.thenReturn(caseNotificationRules);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(CaseNotificationRulesRepo.save(caseNotificationRules3)).thenReturn(caseNotificationRules);

		CommonResponseDTO commonResponse = CaseNotificationRulesService
				.updateCaseNotificationRules(caseNotificationRulesDto, 2, 1, 100);
		assertEquals("Success", commonResponse.getStatus());
	}
}
