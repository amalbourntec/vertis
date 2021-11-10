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
import com.vetris.apimanagement.v1.dto.request.InstitutionDeviceLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.repository.InstitutionDeviceLinkRepository;
import com.vetris.entity.InstitutionDeviceLink;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * @author Aldrin Sunny 
 * 
 * Test class for InstitutionDeviceLinkServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionDeviceLink.class })
class InstitutionDeviceLinkServiceImplTest {

	@InjectMocks
	InstitutionDeviceLinkServiceImpl institutionDeviceLinkServiceImpl;

	@Mock
	InstitutionDeviceLinkRepository institutionDeviceLinkRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionDeviceLinkRequestDTO institutionDeviceLinkRequestDto;

	static InstitutionDeviceLink institutionDeviceLink;

	@BeforeAll
	static void setUp() {
		System.out.println("test");
		institutionDeviceLinkRequestDto = new InstitutionDeviceLinkRequestDTO();
		institutionDeviceLinkRequestDto.setInstitutionId("1234");
		institutionDeviceLinkRequestDto.setManufacturer("Bosch");
		institutionDeviceLinkRequestDto.setModality("modality");
		institutionDeviceLinkRequestDto.setModalityAeTitle("model");
		institutionDeviceLinkRequestDto.setModel("device23");
		institutionDeviceLinkRequestDto.setSerialNo("D2344");
		institutionDeviceLinkRequestDto.setWeightUom("25");
		mapper = new ObjectMapper();
		institutionDeviceLink = mapper.convertValue(institutionDeviceLinkRequestDto, InstitutionDeviceLink.class);
	}

	@Test
	public void saveDeviceLinkTest() throws Exception {
		when(objectMapper.convertValue(institutionDeviceLinkRequestDto, InstitutionDeviceLink.class))
				.thenReturn(institutionDeviceLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionDeviceLinkRepository.save(institutionDeviceLink)).thenReturn(institutionDeviceLink);
		
		CommonResponseDTO commonResponseDTO = institutionDeviceLinkServiceImpl
				.addDevice(institutionDeviceLinkRequestDto);
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getDeviceLinkByIdTest() throws Exception {
		when(institutionDeviceLinkRepository.findById("abc")).thenReturn(Optional.of(institutionDeviceLink));
		CommonResponseDTO commonResponseDTO = institutionDeviceLinkServiceImpl.getDeviceById("abc");
		assertEquals("Success", commonResponseDTO.getStatus());
	}
	
	@Test
	public void getAllDeviceLinkTest() throws Exception {
		List<InstitutionDeviceLink> institutionDeviceLinkList=new ArrayList<>();
		institutionDeviceLinkList.add(institutionDeviceLink);
		when(institutionDeviceLinkRepository.findAll()).thenReturn(institutionDeviceLinkList);
		CommonResponseDTO commonResponseDTO = institutionDeviceLinkServiceImpl.getAllDevice();
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void deleteDeviceLinkByIdTest() throws Exception {
		when(institutionDeviceLinkRepository.findById("abc")).thenReturn(Optional.of(institutionDeviceLink));

		CommonResponseDTO commonResponse = institutionDeviceLinkServiceImpl.deleteDevice("abc");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void updateDeviceLinkTest() throws Exception {
		when(institutionDeviceLinkRepository.findById("abc")).thenReturn(Optional.of(institutionDeviceLink));
		when(objectMapper.convertValue(institutionDeviceLinkRequestDto, InstitutionDeviceLink.class))
				.thenReturn(institutionDeviceLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionDeviceLinkRepository.save(institutionDeviceLink)).thenReturn(institutionDeviceLink);

		CommonResponseDTO commonResponse = institutionDeviceLinkServiceImpl
				.updateDevice(institutionDeviceLinkRequestDto, "abc");
		assertEquals("Success", commonResponse.getStatus());
	}
}
