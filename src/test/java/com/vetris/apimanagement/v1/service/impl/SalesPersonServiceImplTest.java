package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
import com.vetris.apimanagement.v1.dto.request.SalesPersonRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.SalesPersonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.SalesPersonRepository;
import com.vetris.apimanagement.v1.service.SalesPersonService;
import com.vetris.apimanagement.v1.service.impl.SalesPersonServiceImpl;
import com.vetris.entity.SalesPerson;
import com.vetris.utils.JWTSecurityContextUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SalesPersonService.class })
class SalesPersonServiceImplTest {

	@InjectMocks
	SalesPersonServiceImpl salesPersonService;

	@MockBean
	SalesPersonRepository salesPersonRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static SalesPersonRequestDTO salesPersonDto;

	static SalesPerson salesPerson;

	@BeforeAll
	static void setUp() {
		System.out.println("test");
		salesPersonDto = new SalesPersonRequestDTO();
		salesPersonDto.setCode("xyz");
		salesPersonDto.setFName("Shekar");
		salesPersonDto.setIName("samreddy");
		salesPersonDto.setName("reddy");
		salesPersonDto.setAddress1("telangana");
		salesPersonDto.setAddress2("hyd");
		salesPersonDto.setCity("Vpuram");
		salesPersonDto.setStateId(1);
		salesPersonDto.setCountryId(2);
		salesPersonDto.setZip("a");
		salesPersonDto.setEmailId("sam");
		salesPersonDto.setPhoneNo("9640");
		salesPersonDto.setMobileNo("7550");
		salesPersonDto.setPacsUserId("pacs");
		salesPersonDto.setPacsPassword("pacspwd");
		salesPersonDto.setIsActive("y");
		salesPersonDto.setLoginUserId("abc");
		salesPersonDto.setLoginId("def");
		salesPersonDto.setLoginPwd("123");
		salesPersonDto.setNotificationPref("q");
		mapper = new ObjectMapper();
		salesPerson = mapper.convertValue(salesPersonDto, SalesPerson.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void TestSaveSalesPersonTest() throws Exception {
		when(objectMapper.convertValue(salesPersonDto, SalesPerson.class)).thenReturn(salesPerson);
		when(salesPersonRepository.save(salesPerson)).thenReturn(salesPerson);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = salesPersonService.saveSalesPerson(salesPersonDto);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetSalesPersonById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SalesPerson salesPerson1 = mapper.convertValue(salesPersonDto, SalesPerson.class);
		when(salesPersonRepository.findById("abc")).thenReturn(Optional.of(salesPerson1));

		CommonResponseDTO commonResponse = salesPersonService.getSalesPersonById("abc");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			salesPersonService.getSalesPersonById("abc");
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("SalesPerson not found"));
	}

	@Test
	public void testdeleteSalesPerson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SalesPerson salesPerson2 = mapper.convertValue(salesPersonDto, SalesPerson.class);
		when(salesPersonRepository.findById("bac")).thenReturn(Optional.of(salesPerson2));

		CommonResponseDTO commonResponse = salesPersonService.deleteSalesPerson("bac");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testupdateSalesPerson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SalesPerson salesPerson3 = mapper.convertValue(salesPersonDto, SalesPerson.class);
		salesPerson3.setId("cab");
		when(salesPersonRepository.findById("cab")).thenReturn(Optional.of(salesPerson3));
		when(objectMapper.convertValue(salesPersonDto, SalesPerson.class)).thenReturn(salesPerson);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(salesPersonRepository.save(salesPerson)).thenReturn(salesPerson);

		CommonResponseDTO commonResponse = salesPersonService.updateSalesPerson(salesPersonDto, "cab");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}
}
