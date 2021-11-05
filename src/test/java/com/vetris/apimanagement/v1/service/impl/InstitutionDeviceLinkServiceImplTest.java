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

	@MockBean
	InstitutionDeviceLinkRepository institutionDeviceLinkRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
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

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveDeviceLinkTest() throws Exception {
		when(objectMapper.convertValue(institutionDeviceLinkRequestDto, InstitutionDeviceLink.class))
				.thenReturn(institutionDeviceLink);
		when(institutionDeviceLinkRepository.save(institutionDeviceLink)).thenReturn(institutionDeviceLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponseDTO = institutionDeviceLinkServiceImpl
				.addDevice(institutionDeviceLinkRequestDto);
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void getDeviceLinkByIdTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDeviceLink institutionDeviceLink1 = mapper.convertValue(institutionDeviceLinkRequestDto,
				InstitutionDeviceLink.class);
		when(institutionDeviceLinkRepository.findById("abc")).thenReturn(Optional.of(institutionDeviceLink1));
		CommonResponseDTO commonResponseDTO = institutionDeviceLinkServiceImpl.getDeviceById("abc");
		assertEquals("Success", commonResponseDTO.getStatus());
	}
	
	@Test
	public void getAllDeviceLinkTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDeviceLink institutionDeviceLink = mapper.convertValue(institutionDeviceLinkRequestDto,
				InstitutionDeviceLink.class);
		List<InstitutionDeviceLink> institutionDeviceLinkList=new ArrayList<>();
		institutionDeviceLinkList.add(institutionDeviceLink);
		when(institutionDeviceLinkRepository.findAll()).thenReturn(institutionDeviceLinkList);
		CommonResponseDTO commonResponseDTO = institutionDeviceLinkServiceImpl.getAllDevice();
		assertEquals("Success", commonResponseDTO.getStatus());
	}

	@Test
	public void deleteDeviceLinkByIdTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDeviceLink institutionDeviceLink2 = mapper.convertValue(institutionDeviceLinkRequestDto,
				InstitutionDeviceLink.class);
		when(institutionDeviceLinkRepository.findById("abc")).thenReturn(Optional.of(institutionDeviceLink2));

		CommonResponseDTO commonResponse = institutionDeviceLinkServiceImpl.getDeviceById("abc");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void updateDeviceLinkTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDeviceLink institutionDeviceLink3 = mapper.convertValue(institutionDeviceLinkRequestDto,
				InstitutionDeviceLink.class);
		institutionDeviceLink3.setDeviceId("ab");
		when(institutionDeviceLinkRepository.findById("ab")).thenReturn(Optional.of(institutionDeviceLink3));
		when(objectMapper.convertValue(institutionDeviceLinkRequestDto, InstitutionDeviceLink.class))
				.thenReturn(institutionDeviceLink3);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionDeviceLinkRepository.save(institutionDeviceLink3)).thenReturn(institutionDeviceLink3);

		CommonResponseDTO commonResponse = institutionDeviceLinkServiceImpl
				.updateDevice(institutionDeviceLinkRequestDto, "ab");
		assertEquals("Success", commonResponse.getStatus());
	}
}
