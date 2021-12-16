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
import com.vetris.apimanagement.v1.dto.request.InstitutionsRegRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionsRegRepository;
import com.vetris.apimanagement.v1.service.InstitutionsRegService;
import com.vetris.entity.InstitutionsReg;
import com.vetris.utils.JWTSecurityContextUtil;

/*
 * @author Dhanesh C P
 * Test class for InstitutionsRegServiceImpl
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionsRegService.class })
class InstitutionsRegServiceImplTest {

	@InjectMocks
	InstitutionsRegServiceImpl institutionsRegService;

	@Mock
	InstitutionsRegRepository institutionsRegRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionsRegRequestDTO institutionsRegRequestDTO;

	static InstitutionsReg institutionsReg;

	@BeforeAll
	public static void setUp() throws Exception {
		System.out.println("test");
		institutionsRegRequestDTO = new InstitutionsRegRequestDTO();
		institutionsRegRequestDTO.setAddress1("address1");
		institutionsRegRequestDTO.setAddress2("address2");
		institutionsRegRequestDTO.setCity("city");
		institutionsRegRequestDTO.setCode("code");
		institutionsRegRequestDTO.setContactPersonMobile("9495269828");
		institutionsRegRequestDTO.setContactPersonName("dhanesh");
		institutionsRegRequestDTO.setCountryId(2);
		institutionsRegRequestDTO.setEmailId("dhaneshcp@gmail.com");
		institutionsRegRequestDTO.setIsEmailVerified("y");
		institutionsRegRequestDTO.setIsMobileVerified("y");
		institutionsRegRequestDTO.setLoginEmailId("dcp@gmail.com");
		institutionsRegRequestDTO.setLoginId("l1");
		institutionsRegRequestDTO.setLoginMobileNumber("9495269828");
		institutionsRegRequestDTO.setLoginPassword("password");
		institutionsRegRequestDTO.setMobileNo("9495269828");
		institutionsRegRequestDTO.setName("manu");
		institutionsRegRequestDTO.setPhoneNo("9495269828");
		institutionsRegRequestDTO.setPreferredPmtMethod("one");
		institutionsRegRequestDTO.setStateId(12);
		institutionsRegRequestDTO.setZip("688555");
		mapper = new ObjectMapper();
		institutionsReg = mapper.convertValue(institutionsRegRequestDTO, InstitutionsReg.class);
	}

	@Test
	public void testAddInstitutionsReg() throws Exception {
		when(objectMapper.convertValue(institutionsRegRequestDTO, InstitutionsReg.class)).thenReturn(institutionsReg);
		when(institutionsRegRepository.save(institutionsReg)).thenReturn(institutionsReg);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = institutionsRegService.addInstitutionsReg(institutionsRegRequestDTO);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetInstitutionsRegById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionsReg institutionsReg1 = mapper.convertValue(institutionsRegRequestDTO, InstitutionsReg.class);
		when(institutionsRegRepository.findById("abc")).thenReturn(Optional.of(institutionsReg1));

		CommonResponseDTO commonResponse = institutionsRegService.getInstitutionsRegById("abc");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetAllInstitutionsReg() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionsReg institutionsReg4 = mapper.convertValue(institutionsRegRequestDTO, InstitutionsReg.class);
		List<InstitutionsReg> institutionsReg = new ArrayList<InstitutionsReg>();
		institutionsReg.add(institutionsReg4);
		when(institutionsRegRepository.findById("abc")).thenReturn(Optional.of(institutionsReg4));
		when(institutionsRegRepository.findAll()).thenReturn(institutionsReg);
		CommonResponseDTO commonResponse = institutionsRegService.getAllInstitutionsReg();
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testDeleteInstitutionsReg() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionsReg institutionsReg2 = mapper.convertValue(institutionsRegRequestDTO, InstitutionsReg.class);
		when(institutionsRegRepository.findById("bac")).thenReturn(Optional.of(institutionsReg2));

		CommonResponseDTO commonResponse = institutionsRegService.deleteInstitutionsReg("bac");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionsRegService.getInstitutionsRegById("09a35919-8e5b-416b-8c7d-3de3fd834629");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}

	@Test
	public void testGetAllResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionsRegService.getAllInstitutionsReg();
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testUpdateResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionsRegService.updateInstitutionsReg(institutionsRegRequestDTO, "abc");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testDeleteResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionsRegService.deleteInstitutionsReg("abc");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testUpdateInstitutionsReg() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionsReg institutionsReg3 = mapper.convertValue(institutionsRegRequestDTO, InstitutionsReg.class);
		institutionsReg3.setId("cab");
		when(institutionsRegRepository.findById("cab")).thenReturn(Optional.of(institutionsReg3));
		when(objectMapper.convertValue(institutionsRegRequestDTO, InstitutionsReg.class)).thenReturn(institutionsReg);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionsRegRepository.save(institutionsReg)).thenReturn(institutionsReg);

		CommonResponseDTO commonResponse = institutionsRegService.updateInstitutionsReg(institutionsRegRequestDTO,
				"cab");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}
}
