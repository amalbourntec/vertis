package com.vetris.apimanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionUserLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionUserLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionUserLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionUserLinkService;
import com.vetris.entity.InstitutionUserLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/*
 * @author Dhanesh C P
 * Test class for InstitutionUserLinkServiceImpl
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionUserLinkService.class })
class InstitutionUserLinkServiceImplTest {

	@InjectMocks
	InstitutionUserLinkServiceImpl institutionUserLinkService;

	@Mock
	InstitutionUserLinkRepository institutionUserLinkRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO;

	static InstitutionUserLink institutionUserLink;

	@BeforeAll
	public static void setUp() throws Exception {
		System.out.println("test");
		institutionUserLinkRequestDTO = new InstitutionUserLinkRequestDTO();
		institutionUserLinkRequestDTO.setDateUpdatedInPacs(new Date());
		institutionUserLinkRequestDTO.setGrantedRightsPacs("yes");
		institutionUserLinkRequestDTO.setInstitutionId("1");
		institutionUserLinkRequestDTO.setUpdatedInPacs("Y");
		institutionUserLinkRequestDTO.setUserContactNo("9495269828");
		institutionUserLinkRequestDTO.setUserEmail("dhanesh@gmail.com");
		institutionUserLinkRequestDTO.setUserId("1");
		institutionUserLinkRequestDTO.setUserLoginId("id");
		institutionUserLinkRequestDTO.setUserPacsPassword("pass");
		institutionUserLinkRequestDTO.setUserPacsUserId("useris");
		institutionUserLinkRequestDTO.setUserPwd("userpass");
		mapper = new ObjectMapper();
		institutionUserLink = mapper.convertValue(institutionUserLinkRequestDTO, InstitutionUserLink.class);
	}

	@Test
	public void testSaveInstitutionUserLinks() throws Exception {
		System.out.println("test1");
		when(objectMapper.convertValue(institutionUserLinkRequestDTO, InstitutionUserLink.class))
				.thenReturn(institutionUserLink);
		when(institutionUserLinkRepository.save(institutionUserLink)).thenReturn(institutionUserLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = institutionUserLinkService
				.saveInstitutionUserLinks(institutionUserLinkRequestDTO);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionUserLinkService.findByUserId("1");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}

	@Test
	public void testGetByIdResourceNotFoundException1() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionUserLinkService.findByInstitutionId("1");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testUpdateResourceNotFoundException1() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionUserLinkService.updateInstitutionUserLink(institutionUserLinkRequestDTO, "abc", "ccc");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testFetchByAllResourceNotFoundException1() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionUserLinkService.fetchInstitutionUserLinkByAll("abc", "ccc");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testGetByOrResourceNotFoundException1() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionUserLinkService.getByInstitutionIdORUserId("ccc");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testDeleteResourceNotFoundException1() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionUserLinkService.deleteInstitutionUserLink("abc","ccc");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testFindAllResourceNotFoundException1() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionUserLinkService.findAllInstitutionUserLink();
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testfindAllInstitutionUserLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionUserLink institutionUserLink1 = mapper.convertValue(institutionUserLinkRequestDTO,
				InstitutionUserLink.class);
		List<InstitutionUserLink> institutionUserLink = new ArrayList<InstitutionUserLink>();
		institutionUserLink.add(institutionUserLink1);
		when(institutionUserLinkRepository.findById("abc")).thenReturn(Optional.of(institutionUserLink1));
		when(institutionUserLinkRepository.findAll()).thenReturn(institutionUserLink);
		CommonResponseDTO commonResponse = institutionUserLinkService.findAllInstitutionUserLink();
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testfindByUserId(String userId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLink> existingInstitutionUserLink = institutionUserLinkRepository.findByUserId(userId);
		if(!(Collections.EMPTY_LIST != null)) {
			throw new ResourceNotFoundException("not found");
		}
		List<InstitutionUserLinkResponseDTO> institutionUserLinkResponseDTO  = new ArrayList<InstitutionUserLinkResponseDTO>();
		existingInstitutionUserLink.stream().forEach(institutionUserLinkItem -> institutionUserLinkResponseDTO
				.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class)));

		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Fetched successfully");

	}

	@Test
	public void testFindByInstitutionId(String institutionId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLink> existingInstitutionUserLink = institutionUserLinkRepository
				.findByInstitutionId(institutionId);
		List<InstitutionUserLinkResponseDTO> institutionUserLinkResponseDTO  = new ArrayList<InstitutionUserLinkResponseDTO>();
		if(CollectionUtils.isEmpty(existingInstitutionUserLink)) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		}
		existingInstitutionUserLink.stream().forEach(institutionUserLinkItem -> institutionUserLinkResponseDTO
				.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class)));

		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Fetched successfully");
	}

	@Test
	public void testFetchInstitutionUserLinkByAll() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionUserLink institutionUserLink1 = mapper.convertValue(institutionUserLinkRequestDTO,
				InstitutionUserLink.class);
		List<InstitutionUserLink> institutionUserLink = new ArrayList<InstitutionUserLink>();
		institutionUserLink.add(institutionUserLink1);
		when(institutionUserLinkRepository.findByInstitutionIdANDUserId("abc", "abcd"))
				.thenReturn(Optional.of(institutionUserLink1));
		CommonResponseDTO commonResponse = institutionUserLinkService.fetchInstitutionUserLinkByAll("abc", "abcd");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetByInstitutionIdORUserId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionUserLink institutionUserLink1 = mapper.convertValue(institutionUserLinkRequestDTO,
				InstitutionUserLink.class);
		List<InstitutionUserLink> institutionUserLink = new ArrayList<InstitutionUserLink>();
		institutionUserLink.add(institutionUserLink1);
		when(institutionUserLinkRepository.findByInstitutionIdORUserId("abc")).thenReturn(institutionUserLink);
		// when(institutionUserLinkRepository.findAll()).thenReturn(institutionUserLink);
		CommonResponseDTO commonResponse = institutionUserLinkService.getByInstitutionIdORUserId("abc");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testupdateInstitutionUserLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionUserLink institutionUserLink1 = mapper.convertValue(institutionUserLinkRequestDTO,
				InstitutionUserLink.class);
		institutionUserLink1.setUserId("cab");
		when(institutionUserLinkRepository.findByInstitutionIdANDUserId("cab", "abc"))
				.thenReturn(Optional.of(institutionUserLink1));
		when(objectMapper.convertValue(institutionUserLinkRequestDTO, InstitutionUserLink.class))
				.thenReturn(institutionUserLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionUserLinkRepository.save(institutionUserLink)).thenReturn(institutionUserLink);

		CommonResponseDTO commonResponse = institutionUserLinkService
				.updateInstitutionUserLink(institutionUserLinkRequestDTO, "cab", "abc");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testDeleteInstitutionUserLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionUserLink institutionUserLink1 = mapper.convertValue(institutionUserLinkRequestDTO,
				InstitutionUserLink.class);
		when(institutionUserLinkRepository.findByInstitutionIdANDUserId("abc", "bac"))
				.thenReturn(Optional.of(institutionUserLink1));

		CommonResponseDTO commonResponse = institutionUserLinkService.deleteInstitutionUserLink("abc", "bac");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}
}
