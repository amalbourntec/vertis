package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionRatesFeeScheduleRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.repository.InstitutionRatesFeeScheduleRepository;
import com.vetris.entity.InstitutionRatesFeeSchedule;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * @author Aldrin Sunny
 * 
 * Test class for InstitutionRatesFeeScheduleServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionRatesFeeSchedule.class })
class InstitutionRatesFeeScheduleSeviceImplTest {

	@InjectMocks
	InstitutionRatesFeeScheduleServiceImpl institutionRatesFeeScheduleServiceImpl;

	@Mock
	InstitutionRatesFeeScheduleRepository institutionRatesFeeScheduleRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionRatesFeeScheduleRequestDTO institutionRatesFeeScheduleRequestDto;

	static InstitutionRatesFeeSchedule institutionRatesFeeSchedule;

	@BeforeAll
	static void setUp() {
		System.out.println("test");
		institutionRatesFeeScheduleRequestDto = new InstitutionRatesFeeScheduleRequestDTO();
		institutionRatesFeeScheduleRequestDto.setInstitutionId("asd");
		institutionRatesFeeScheduleRequestDto.setFeeAmount(200.3);
		institutionRatesFeeScheduleRequestDto.setDiscountPer(10.2);
		mapper = new ObjectMapper();
		institutionRatesFeeSchedule = mapper.convertValue(institutionRatesFeeScheduleRequestDto,
				InstitutionRatesFeeSchedule.class);
	}

	@Test
	public void saveInstitutionRatesFeeScheduleTest() throws Exception {
		when(objectMapper.convertValue(institutionRatesFeeScheduleRequestDto, InstitutionRatesFeeSchedule.class))
				.thenReturn(institutionRatesFeeSchedule);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionRatesFeeScheduleRepository.save(institutionRatesFeeSchedule))
				.thenReturn(institutionRatesFeeSchedule);
		
		CommonResponseDTO commonResponseDTO = institutionRatesFeeScheduleServiceImpl
				.addRateFee(institutionRatesFeeScheduleRequestDto);
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getInstitutionRatesFeeScheduleByIdTest() throws Exception {
		when(institutionRatesFeeScheduleRepository.findById("abc"))
				.thenReturn(Optional.of(institutionRatesFeeSchedule));
		CommonResponseDTO commonResponseDTO = institutionRatesFeeScheduleServiceImpl.getRateFeeById("abc");
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getAllInstitutionRatesFeeScheduleTest() throws Exception {
		List<InstitutionRatesFeeSchedule> institutionRatesFeeScheduleList = new ArrayList<>();
		institutionRatesFeeScheduleList.add(institutionRatesFeeSchedule);
		when(institutionRatesFeeScheduleRepository.findAll()).thenReturn(institutionRatesFeeScheduleList);
		CommonResponseDTO commonResponseDTO = institutionRatesFeeScheduleServiceImpl.getAllRateFee();
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void deleteInstitutionRatesFeeScheduleByIdTest() throws Exception {
		when(institutionRatesFeeScheduleRepository.findById("rate12"))
				.thenReturn(Optional.of(institutionRatesFeeSchedule));

		CommonResponseDTO commonResponse = institutionRatesFeeScheduleServiceImpl.deleteRateFee("rate12");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void updateInstitutionRatesFeeScheduleTest() throws Exception {
		when(institutionRatesFeeScheduleRepository.findById("fr12"))
				.thenReturn(Optional.of(institutionRatesFeeSchedule));
		when(objectMapper.convertValue(institutionRatesFeeScheduleRequestDto, InstitutionRatesFeeSchedule.class))
				.thenReturn(institutionRatesFeeSchedule);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionRatesFeeScheduleRepository.save(institutionRatesFeeSchedule))
				.thenReturn(institutionRatesFeeSchedule);

		CommonResponseDTO commonResponse = institutionRatesFeeScheduleServiceImpl
				.updateRateFee(institutionRatesFeeScheduleRequestDto, "fr12");
		assertEquals("Success", commonResponse.getStatus());
	}

}
