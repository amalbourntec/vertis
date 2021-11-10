package com.vetris.adminmanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRuleRadiologistDtlsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.CaseNotificationRuleRadiologistDtlsRepository;
import com.vetris.adminmanagement.v1.service.CaseNotificationRuleRadiologistDtlsService;
import com.vetris.entity.CaseNotificationRuleRadiologistDtls;
import com.vetris.utils.JWTSecurityContextUtil;

import lombok.NoArgsConstructor;

/**
 * Test class for CaseNotificationRuleRadiologistDtlsServiceImpl
 * 
 * @author Jini
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CaseNotificationRuleRadiologistDtlsService.class })
@NoArgsConstructor
public class CaseNotificationRuleRadiologistDtlsServiceImplTest {

	@InjectMocks
	CaseNotificationRuleRadiologistDtlsServiceImpl caseNotifiRuleRadiologistDtlsServiceImpl;

	@Mock
	CaseNotificationRuleRadiologistDtlsRepository caseNotifiRuleRadiologistDtlsRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static CaseNotificationRuleRadiologistDtlsRequestDTO caseNotifiRuleRadiologistDtlsReqDTO;

	static CaseNotificationRuleRadiologistDtls caseNotifiRuleRadiologistDtls;

	@BeforeClass
	public static void setUp() {
		System.out.println("Test CaseNotificationRuleRadiologistDtls");
		caseNotifiRuleRadiologistDtlsReqDTO = new CaseNotificationRuleRadiologistDtlsRequestDTO();
		caseNotifiRuleRadiologistDtlsReqDTO.setRadiologistId("abcd");
		caseNotifiRuleRadiologistDtlsReqDTO.setUserId("bcda");
		caseNotifiRuleRadiologistDtlsReqDTO.setNotifyAlways("Y");
		caseNotifiRuleRadiologistDtlsReqDTO.setNotifyIfScheduled("N");
		mapper = new ObjectMapper();
		caseNotifiRuleRadiologistDtls = mapper.convertValue(caseNotifiRuleRadiologistDtlsReqDTO,
				CaseNotificationRuleRadiologistDtls.class);
	}

	@Test
	public void testAddCaseNotificationRuleRadiologistDtls() throws Exception {
		when(objectMapper.convertValue(caseNotifiRuleRadiologistDtlsReqDTO, CaseNotificationRuleRadiologistDtls.class))
				.thenReturn(caseNotifiRuleRadiologistDtls);
		when(caseNotifiRuleRadiologistDtlsRepository.save(caseNotifiRuleRadiologistDtls))
				.thenReturn(caseNotifiRuleRadiologistDtls);
		CommonResponseDTO commonResponse = caseNotifiRuleRadiologistDtlsServiceImpl
				.addCaseNotificationRuleRadiologistDtls(caseNotifiRuleRadiologistDtlsReqDTO);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetAllCaseNotificationRuleRadiologistDtls() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRuleRadiologistDtls caseNotifiRuleRadiologistDtls1 = mapper
				.convertValue(caseNotifiRuleRadiologistDtlsReqDTO, CaseNotificationRuleRadiologistDtls.class);
		List<CaseNotificationRuleRadiologistDtls> caseNotifiRuleRadiologistDtls = new ArrayList<CaseNotificationRuleRadiologistDtls>();
		caseNotifiRuleRadiologistDtls.add(caseNotifiRuleRadiologistDtls1);
		when(caseNotifiRuleRadiologistDtlsRepository.findById(66))
				.thenReturn(Optional.of(caseNotifiRuleRadiologistDtls1));
		when(caseNotifiRuleRadiologistDtlsRepository.findAll()).thenReturn(caseNotifiRuleRadiologistDtls);
		CommonResponseDTO commonResponse = caseNotifiRuleRadiologistDtlsServiceImpl
				.getAllCaseNotificationRuleRadiologistDtls();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetCaseNotificationRuleRadiologistDtlsById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRuleRadiologistDtls caseNotifiRuleRadiologistDtls1 = mapper
				.convertValue(caseNotifiRuleRadiologistDtlsReqDTO, CaseNotificationRuleRadiologistDtls.class);
		when(caseNotifiRuleRadiologistDtlsRepository.findById(34))
				.thenReturn(Optional.of(caseNotifiRuleRadiologistDtls1));
		CommonResponseDTO commonResponse = caseNotifiRuleRadiologistDtlsServiceImpl
				.getCaseNotificationRuleRadiologistDtlsById(34);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			caseNotifiRuleRadiologistDtlsServiceImpl.getCaseNotificationRuleRadiologistDtlsById(10);
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("CaseNotificationRuleRadiologistDtls not found"));
	}

	@Test
	public void testUpdateCaseNotificationRuleRadiologistDtls() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRuleRadiologistDtls caseNotifiRuleRadiologistDtls1 = mapper
				.convertValue(caseNotifiRuleRadiologistDtlsReqDTO, CaseNotificationRuleRadiologistDtls.class);
		when(caseNotifiRuleRadiologistDtlsRepository.findById(23))
				.thenReturn(Optional.of(caseNotifiRuleRadiologistDtls1));
		when(objectMapper.convertValue(caseNotifiRuleRadiologistDtlsReqDTO, CaseNotificationRuleRadiologistDtls.class))
				.thenReturn(caseNotifiRuleRadiologistDtls);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(caseNotifiRuleRadiologistDtlsRepository.save(caseNotifiRuleRadiologistDtls))
				.thenReturn(caseNotifiRuleRadiologistDtls);

		CommonResponseDTO commonResponse = caseNotifiRuleRadiologistDtlsServiceImpl
				.updateCaseNotificationRuleRadiologistDtls(caseNotifiRuleRadiologistDtlsReqDTO, 23);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testDeleteCaseNotificationRuleRadiologistDtls() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CaseNotificationRuleRadiologistDtls caseNotifiRuleRadiologistDtls1 = mapper
				.convertValue(caseNotifiRuleRadiologistDtlsReqDTO, CaseNotificationRuleRadiologistDtls.class);
		when(caseNotifiRuleRadiologistDtlsRepository.findById(2))
				.thenReturn(Optional.of(caseNotifiRuleRadiologistDtls1));
		CommonResponseDTO commonResponse = caseNotifiRuleRadiologistDtlsServiceImpl
				.deleteCaseNotificationRuleRadiologistDtls(2);
		assertEquals("Success", commonResponse.getStatus());
	}

}
