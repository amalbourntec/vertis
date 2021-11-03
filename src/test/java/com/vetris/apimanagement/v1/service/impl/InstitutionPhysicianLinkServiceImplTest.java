package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionPhysicianLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionPhysicianLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionPhysicianLinkService;
import com.vetris.entity.InstitutionPhysicianLink;
import com.vetris.utils.JWTSecurityContextUtil;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionPhysicianLinkService.class })
@NoArgsConstructor
public class InstitutionPhysicianLinkServiceImplTest {

	@InjectMocks
	InstitutionPhysicianLinkServiceImpl institutionPhysicianLinkServiceImpl;

	@MockBean
	InstitutionPhysicianLinkRepository institutionPhysicianLinkRepository;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionPhysicianLinkRequestDTO institutionPhysicianLinkReqDTO;

	static InstitutionPhysicianLink institutionPhysicianLink;

	@BeforeClass
	public static void setUp() {
		System.out.println("Test Institution Physician Link");
		institutionPhysicianLinkReqDTO = new InstitutionPhysicianLinkRequestDTO();
		institutionPhysicianLinkReqDTO.setInstitutionId("abcd1");
		institutionPhysicianLinkReqDTO.setPhysicianName("testName");
		institutionPhysicianLinkReqDTO.setPhysicianFname("testFname");
		institutionPhysicianLinkReqDTO.setPhysicianLname("testLname");
		institutionPhysicianLinkReqDTO.setPhysicianCredentials("testCredential");
		institutionPhysicianLinkReqDTO.setPhysicianLoginEmail("testLoginEmail");
		institutionPhysicianLinkReqDTO.setPhysicianEmail("test@gmail.com");
		institutionPhysicianLinkReqDTO.setPhysicianMobile("879456123");
		institutionPhysicianLinkReqDTO.setPhysicianUserId("testUserId");
		institutionPhysicianLinkReqDTO.setPhysicianPacsPassword("test");
		institutionPhysicianLinkReqDTO.setPhysicianPacsUserId("tstid");
		institutionPhysicianLinkReqDTO.setBillingAccountId("testBillId");
		mapper = new ObjectMapper();
		institutionPhysicianLink = mapper.convertValue(institutionPhysicianLinkReqDTO, InstitutionPhysicianLink.class);
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddInstitutionPhysicianLink() throws Exception {
		when(objectMapper.convertValue(institutionPhysicianLinkReqDTO, InstitutionPhysicianLink.class))
				.thenReturn(institutionPhysicianLink);
		when(institutionPhysicianLinkRepository.save(institutionPhysicianLink)).thenReturn(institutionPhysicianLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = institutionPhysicianLinkServiceImpl
				.addInstitutionPhysicianLink(institutionPhysicianLinkReqDTO);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetAllInstitutionPhysicianLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionPhysicianLink institutionPhysicianLink1 = mapper.convertValue(institutionPhysicianLinkReqDTO,
				InstitutionPhysicianLink.class);
		List<InstitutionPhysicianLink> institutionPhysicianLink = new ArrayList<InstitutionPhysicianLink>();
		institutionPhysicianLink.add(institutionPhysicianLink1);
		when(institutionPhysicianLinkRepository.findById("abcd")).thenReturn(Optional.of(institutionPhysicianLink1));
		when(institutionPhysicianLinkRepository.findAll()).thenReturn(institutionPhysicianLink);
		CommonResponseDTO commonResponse = institutionPhysicianLinkServiceImpl.getAllInstitutionPhysicianLink();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetInstitutionPhysicianLinkById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionPhysicianLink institutionPhysicianLink1 = mapper.convertValue(institutionPhysicianLinkReqDTO,
				InstitutionPhysicianLink.class);
		when(institutionPhysicianLinkRepository.findById("bacd")).thenReturn(Optional.of(institutionPhysicianLink1));
		CommonResponseDTO commonResponse = institutionPhysicianLinkServiceImpl.getInstitutionPhysicianLinkById("bacd");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionPhysicianLinkServiceImpl.getInstitutionPhysicianLinkById("acbd");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("InstitutionPhysicianLink not found"));
	}

	@Test
	public void testUpdateInstitutionPhysicianLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionPhysicianLink institutionPhysicianLink1 = mapper.convertValue(institutionPhysicianLinkReqDTO,
				InstitutionPhysicianLink.class);
		when(institutionPhysicianLinkRepository.findById("adcb")).thenReturn(Optional.of(institutionPhysicianLink1));
		when(objectMapper.convertValue(institutionPhysicianLinkReqDTO, InstitutionPhysicianLink.class))
				.thenReturn(institutionPhysicianLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionPhysicianLinkRepository.save(institutionPhysicianLink)).thenReturn(institutionPhysicianLink);

		CommonResponseDTO commonResponse = institutionPhysicianLinkServiceImpl
				.updateInstitutionPhysicianLink(institutionPhysicianLinkReqDTO, "adcb");
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testDeleteInstitutionPhysicianLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionPhysicianLink institutionPhysicianLink1 = mapper.convertValue(institutionPhysicianLinkReqDTO,
				InstitutionPhysicianLink.class);
		when(institutionPhysicianLinkRepository.findById("dcba")).thenReturn(Optional.of(institutionPhysicianLink1));
		CommonResponseDTO commonResponse = institutionPhysicianLinkServiceImpl.deleteInstitutionPhysicianLink("dcba");
		assertEquals("Success", commonResponse.getStatus());
	}
}
