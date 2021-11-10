package com.vetris.apimanagement.v1.service.impl;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionCategoryLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionCategoryLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionCategoryLinkService;
import com.vetris.entity.InstitutionCategoryLink;
import com.vetris.utils.JWTSecurityContextUtil;

import lombok.NoArgsConstructor;

/**
 * Test class for InstitutionCategoryLinkServiceImpl
 * 
 * @author Jini
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InstitutionCategoryLinkService.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
@NoArgsConstructor
public class InstitutionCategoryLinkServiceImplTest {

	@Value("${server.port}")
	int port;

	@InjectMocks
	InstitutionCategoryLinkServiceImpl institutionCategoryLinkServiceImpl;

	@Mock
	InstitutionCategoryLinkRepository institutionCategoryLinkRepository;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static InstitutionCategoryLinkRequestDTO institutionCategoryLinkReqDTO;

	static InstitutionCategoryLink institutionCategoryLink;

	@BeforeClass
	public static void setUp() {
		System.out.println("Test Institution Category Link");
		institutionCategoryLinkReqDTO = new InstitutionCategoryLinkRequestDTO();
		institutionCategoryLinkReqDTO.setInstitutionId("inid32");
		mapper = new ObjectMapper();
		institutionCategoryLink = mapper.convertValue(institutionCategoryLinkReqDTO, InstitutionCategoryLink.class);
	}

	@Test
	public void testAddInstitutionCategoryLink() throws Exception {
		when(objectMapper.convertValue(institutionCategoryLinkReqDTO, InstitutionCategoryLink.class))
				.thenReturn(institutionCategoryLink);
		when(institutionCategoryLinkRepository.save(institutionCategoryLink)).thenReturn(institutionCategoryLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = institutionCategoryLinkServiceImpl
				.addInstitutionCategoryLink(institutionCategoryLinkReqDTO);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetAllInstitutionCategoryLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionCategoryLink institutionCategoryLink1 = mapper.convertValue(institutionCategoryLinkReqDTO,
				InstitutionCategoryLink.class);
		List<InstitutionCategoryLink> institutionCategoryLink = new ArrayList<InstitutionCategoryLink>();
		institutionCategoryLink.add(institutionCategoryLink1);
		when(institutionCategoryLinkRepository.findById(21)).thenReturn(Optional.of(institutionCategoryLink1));
		when(institutionCategoryLinkRepository.findAll()).thenReturn(institutionCategoryLink);
		CommonResponseDTO commonResponse = institutionCategoryLinkServiceImpl.getAllInstitutionCategoryLink();
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetInstitutionCategoryLinkById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionCategoryLink institutionCategoryLink1 = mapper.convertValue(institutionCategoryLinkReqDTO,
				InstitutionCategoryLink.class);
		when(institutionCategoryLinkRepository.findById(32)).thenReturn(Optional.of(institutionCategoryLink1));
		CommonResponseDTO commonResponse = institutionCategoryLinkServiceImpl.getInstitutionCategoryLinkById(32);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			institutionCategoryLinkServiceImpl.getInstitutionCategoryLinkById(32);
		});
		assertFalse(exception.getMessage().equalsIgnoreCase("InstitutionCategoryLink not found"));
	}

	@Test
	public void testUpdateInstitutionCategoryLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionCategoryLink institutionCategoryLink2 = mapper.convertValue(institutionCategoryLinkReqDTO,
				InstitutionCategoryLink.class);
		when(institutionCategoryLinkRepository.findById(52)).thenReturn(Optional.of(institutionCategoryLink2));
		when(objectMapper.convertValue(institutionCategoryLinkReqDTO, InstitutionCategoryLink.class))
				.thenReturn(institutionCategoryLink);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(institutionCategoryLinkRepository.save(institutionCategoryLink)).thenReturn(institutionCategoryLink);

		CommonResponseDTO commonResponse = institutionCategoryLinkServiceImpl
				.updateInstitutionCategoryLink(institutionCategoryLinkReqDTO, 52);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testDeleteInstitutionCategoryLink() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InstitutionCategoryLink institutionCategoryLink3 = mapper.convertValue(institutionCategoryLinkReqDTO,
				InstitutionCategoryLink.class);
		when(institutionCategoryLinkRepository.findById(62)).thenReturn(Optional.of(institutionCategoryLink3));
		CommonResponseDTO commonResponse = institutionCategoryLinkServiceImpl.deleteInstitutionCategoryLink(62);
		assertEquals("Success", commonResponse.getStatus());
	}

}
