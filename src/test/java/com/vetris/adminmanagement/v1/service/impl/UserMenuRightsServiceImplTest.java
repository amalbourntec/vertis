package com.vetris.adminmanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
import com.vetris.adminmanagement.v1.dto.request.UserMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.SpeciesResponseDTO;
import com.vetris.adminmanagement.v1.repository.UserMenuRightsRepository;
import com.vetris.adminmanagement.v1.service.UserMenuRightsService;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.entity.Species;
import com.vetris.utils.JWTSecurityContextUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UserMenuRightsService.class })
public class UserMenuRightsServiceImplTest {
	@InjectMocks
	UserMenuRightsServiceImpl userMenuRightsService;

	@MockBean
	UserMenuRightsRepository userMenuRightsRepo;

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@MockBean
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static UserMenuRightsRequestDTO userMenuRightsDto;

	static Species species;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		userMenuRightsDto = new UserMenuRightsRequestDTO();
		userMenuRightsDto.setCode("123");
		userMenuRightsDto.setIsActive("Y");
		userMenuRightsDto.setName("Manu");
		mapper = new ObjectMapper();
		species = mapper.convertValue(userMenuRightsDto, Species.class);
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSpeciesPostTest() throws Exception {
		when(objectMapper.convertValue(userMenuRightsDto, Species.class)).thenReturn(species);
		when(userMenuRightsRepo.save(species)).thenReturn(species);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = userMenuRightsService.saveSpecies(userMenuRightsDto);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetSpeciesById() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Species species1 = mapper.convertValue(userMenuRightsDto, Species.class);
		SpeciesResponseDTO speciesRespDto = mapper.convertValue(species1, SpeciesResponseDTO.class);
		when(userMenuRightsRepo.findById(1)).thenReturn(Optional.of(species1));
		when(objectMapper.convertValue(species1, SpeciesResponseDTO.class)).thenReturn(speciesRespDto);
		CommonResponseDTO commonResponse = userMenuRightsService.getSpeciesById(1);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetAllSpeciesTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Species Species1 = mapper.convertValue(userMenuRightsDto, Species.class);
		List<Species> Species = new ArrayList<Species>();
		Species.add(Species1);
		when(userMenuRightsRepo.findAll()).thenReturn(Species);
		CommonResponseDTO commonResponse = userMenuRightsService.findAllSpecies();
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			userMenuRightsService.getSpeciesById(1);
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("Species  not found"));
	}

	@Test
	public void testdeleteSpecies() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Species Species = mapper.convertValue(userMenuRightsDto, Species.class);
		when(userMenuRightsRepo.findById(1)).thenReturn(Optional.of(Species));
		CommonResponseDTO commonResponse = userMenuRightsService.deleteSpecies(1);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testupdateSpecies() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Species species3 = mapper.convertValue(userMenuRightsDto, Species.class);
		when(userMenuRightsRepo.findById(1)).thenReturn(Optional.of(species3));
		when(objectMapper.convertValue(userMenuRightsDto, Species.class)).thenReturn(species3);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(userMenuRightsRepo.save(species3)).thenReturn(species);
		CommonResponseDTO commonResponse = userMenuRightsService.editSpecies(userMenuRightsDto, 1);
		assertEquals("Success", commonResponse.getStatus());
	}
}

