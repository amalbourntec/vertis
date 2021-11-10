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
import com.vetris.apimanagement.v1.dto.request.InstitutionDisputeDicomTagsRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionDisputeDicomTagsRepository;
import com.vetris.apimanagement.v1.service.InstitutionDisputeDicomTagsService;
import com.vetris.entity.InstitutionDisputeDicomTags;
import com.vetris.utils.JWTSecurityContextUtil;

/*
 * @author Dhanesh C P
 * Test class for InstitutionDisputeDicomTagsServiceImpl
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionDisputeDicomTagsService.class })
class InstitutionDisputeDicomTagsServiceImplTest {

	@InjectMocks
	InstitutionDisputeDicomTagsServiceImpl institutionDisputeDicomTagsService;

	@Mock
	InstitutionDisputeDicomTagsRepository institutionDisputeDicomTagsRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequestDTO;

	static InstitutionDisputeDicomTags institutionDisputeDicomTags;

	@BeforeAll
	public static void setUp() throws Exception {

		System.out.println("test");
		institutionDisputeDicomTagsRequestDTO = new InstitutionDisputeDicomTagsRequestDTO();
		institutionDisputeDicomTagsRequestDTO.setDefaultValue("abc");
		institutionDisputeDicomTagsRequestDTO.setElementId("one");
		institutionDisputeDicomTagsRequestDTO.setGroupId("one");
		institutionDisputeDicomTagsRequestDTO.setInstitutionId("one");
		institutionDisputeDicomTagsRequestDTO.setJunkCharacters("bbb");
		mapper = new ObjectMapper();
		institutionDisputeDicomTags = mapper.convertValue(institutionDisputeDicomTagsRequestDTO,
				InstitutionDisputeDicomTags.class);
	}

	@Test
	public void testaddInstitutionDisputeDicomTags() throws Exception {
		System.out.println("test1");
		when(objectMapper.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class))
				.thenReturn(institutionDisputeDicomTags);
		when(institutionDisputeDicomTagsRepository.save(institutionDisputeDicomTags))
				.thenReturn(institutionDisputeDicomTags);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService
				.addInstitutionDisputeDicomTags(institutionDisputeDicomTagsRequestDTO);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.getInstitutionDisputeDicomTagsByElementId("one");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}

	@Test
	public void testGetByIdResourceNotFoundException1() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.getInstitutionDisputeDicomTagsByGroupId("one");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}

	@Test
	public void testGetByIdResourceNotFoundException2() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.getInstitutionDisputeDicomTagsByInstitutionId("one");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}

	@Test
	public void testGetAllResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.getAllInstitutionDisputeDicomTags();
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testGetByIdOrResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.getByInstitutionIdORGroupIdORElementId("one");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testUpdateResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.updateInstitutionDisputeDicomTags(institutionDisputeDicomTagsRequestDTO, "abc","ccc","one");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testFetchAlleResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.fetchInstitutionDisputeDicomTagsByAll("abc","ccc","one");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testDeleteResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionDisputeDicomTagsService.deleteInstitutionDisputeDicomTags("abc","ccc","one");
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("not found"));
	}
	
	@Test
	public void testgetAllInstitutionDisputeDicomTags() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = new ArrayList<InstitutionDisputeDicomTags>();
		institutionDisputeDicomTags.add(institutionDisputeDicomTag1);
		when(institutionDisputeDicomTagsRepository.findById("abc"))
				.thenReturn(Optional.of(institutionDisputeDicomTag1));
		when(institutionDisputeDicomTagsRepository.findAll()).thenReturn(institutionDisputeDicomTags);
		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService.getAllInstitutionDisputeDicomTags();
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetInstitutionDisputeDicomTagsByGroupId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = new ArrayList<InstitutionDisputeDicomTags>();
		institutionDisputeDicomTags.add(institutionDisputeDicomTag1);
		when(institutionDisputeDicomTagsRepository.findAllGroupId("one")).thenReturn(institutionDisputeDicomTags);

		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService
				.getInstitutionDisputeDicomTagsByGroupId("one");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetInstitutionDisputeDicomTagsByInstitutionId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = new ArrayList<InstitutionDisputeDicomTags>();
		institutionDisputeDicomTags.add(institutionDisputeDicomTag1);
		when(institutionDisputeDicomTagsRepository.findAllInstitutionId("1")).thenReturn(institutionDisputeDicomTags);

		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService
				.getInstitutionDisputeDicomTagsByInstitutionId("1");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetInstitutionDisputeDicomTagsByElementId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = new ArrayList<InstitutionDisputeDicomTags>();
		institutionDisputeDicomTags.add(institutionDisputeDicomTag1);
		when(institutionDisputeDicomTagsRepository.findAllElementId("one")).thenReturn(institutionDisputeDicomTags);

		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService
				.getInstitutionDisputeDicomTagsByElementId("one");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetByInstitutionIdORGroupIdORElementId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = new ArrayList<InstitutionDisputeDicomTags>();
		institutionDisputeDicomTags.add(institutionDisputeDicomTag1);
		when(institutionDisputeDicomTagsRepository.findByInstitutionIdORGroupIdORElementId("abc"))
				.thenReturn(institutionDisputeDicomTags);
		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService
				.getByInstitutionIdORGroupIdORElementId("abc");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testFetchInstitutionDisputeDicomTagsByAll() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = new ArrayList<InstitutionDisputeDicomTags>();
		institutionDisputeDicomTags.add(institutionDisputeDicomTag1);
		when(institutionDisputeDicomTagsRepository.findByInstitutionIdANDGroupIdANDElementId("abc", "acc", "abcd"))
				.thenReturn(Optional.of(institutionDisputeDicomTag1));
		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService
				.fetchInstitutionDisputeDicomTagsByAll("abc", "acc", "abcd");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testUpdateInstitutionDisputeDicomTags() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		institutionDisputeDicomTag1.setInstitutionId("cab");
		when(institutionDisputeDicomTagsRepository.findByInstitutionIdANDGroupIdANDElementId("cab", "abc", "ccc"))
				.thenReturn(Optional.of(institutionDisputeDicomTag1));
		when(objectMapper.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class))
				.thenReturn(institutionDisputeDicomTags);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionDisputeDicomTagsRepository.save(institutionDisputeDicomTags))
				.thenReturn(institutionDisputeDicomTags);

		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService
				.updateInstitutionDisputeDicomTags(institutionDisputeDicomTagsRequestDTO, "cab", "abc", "ccc");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testDeleteInstitutionDisputeDicomTags() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionDisputeDicomTags institutionDisputeDicomTag1 = mapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		when(institutionDisputeDicomTagsRepository.findByInstitutionIdANDGroupIdANDElementId("abc", "bac", "ccc"))
				.thenReturn(Optional.of(institutionDisputeDicomTag1));

		CommonResponseDTO commonResponse = institutionDisputeDicomTagsService.deleteInstitutionDisputeDicomTags("abc",
				"bac", "ccc");
		assertEquals("SUCCESS", commonResponse.getStatus());
	}
}
