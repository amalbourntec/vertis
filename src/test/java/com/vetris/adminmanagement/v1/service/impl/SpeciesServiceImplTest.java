package com.vetris.adminmanagement.v1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.SpeciesRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.SpeciesResponseDTO;
import com.vetris.adminmanagement.v1.repository.SpeciesRepository;
import com.vetris.adminmanagement.v1.service.SpeciesService;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.entity.Species;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Test Class for Species Impl
 * 
 * @author Jose Eldhose
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpeciesService.class })
public class SpeciesServiceImplTest {
	@InjectMocks
	SpeciesServiceImpl speciesService;

	@Mock
	SpeciesRepository speciesRepo;

	@Mock
	ObjectMapper objectMapper;

	@Autowired
	static ObjectMapper mapper;

	@Mock
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	static SpeciesRequestDTO speciesDto;

	static Species species;

	@BeforeAll
	static void setUp() {
		System.out.println("test --> beofre");
		speciesDto = new SpeciesRequestDTO();
		speciesDto.setCode("123");
		speciesDto.setIsActive("Y");
		speciesDto.setName("Manu");
		mapper = new ObjectMapper();
		species = mapper.convertValue(speciesDto, Species.class);
	}

	@Test
	public void testSpeciesPostTest() throws Exception {
		when(objectMapper.convertValue(speciesDto, Species.class)).thenReturn(species);
		when(speciesRepo.save(species)).thenReturn(species);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		CommonResponseDTO commonResponse = speciesService.saveSpecies(speciesDto);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetSpeciesById() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Species species1 = mapper.convertValue(speciesDto, Species.class);
		SpeciesResponseDTO speciesRespDto = new SpeciesResponseDTO();
		speciesRespDto.setIsActive("Y");
		speciesRespDto.setCreatedBy("test");
		speciesRespDto.setUpdateBy("test");
		speciesRespDto.setDateCreated(new Date());
		speciesRespDto.setDateUpdated(new Date());
		speciesRespDto.setCode("abc");
		speciesRespDto.setId(1);
		when(speciesRepo.findById(1)).thenReturn(Optional.of(species1));
		when(objectMapper.convertValue(species1, SpeciesResponseDTO.class)).thenReturn(speciesRespDto);
		CommonResponseDTO commonResponse = speciesService.getSpeciesById(1);
		assertEquals("Success", commonResponse.getStatus());
	}

	@Test
	public void testGetAllSpeciesTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Species Species1 = mapper.convertValue(speciesDto, Species.class);
		List<Species> Species = new ArrayList<Species>();
		Species.add(Species1);
		when(speciesRepo.findAll()).thenReturn(Species);
		CommonResponseDTO commonResponse = speciesService.findAllSpecies();
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testGetByIdResourceNotFoundException() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			speciesService.getSpeciesById(1);
		});
		assertTrue(exception.getMessage().equalsIgnoreCase("Species  not found"));
	}

	@Test
	public void testdeleteSpecies() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Species Species = mapper.convertValue(speciesDto, Species.class);
		when(speciesRepo.findById(1)).thenReturn(Optional.of(Species));
		CommonResponseDTO commonResponse = speciesService.deleteSpecies(1);
		assertEquals("SUCCESS", commonResponse.getStatus());
	}

	@Test
	public void testupdateSpecies() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Species species3 = mapper.convertValue(speciesDto, Species.class);
		when(speciesRepo.findById(1)).thenReturn(Optional.of(species3));
		when(objectMapper.convertValue(speciesDto, Species.class)).thenReturn(species3);
		when(jwtSecurityContextUtil.getId()).thenReturn("test");
		when(speciesRepo.save(species3)).thenReturn(species);
		CommonResponseDTO commonResponse = speciesService.editSpecies(speciesDto, 1);
		assertEquals("Success", commonResponse.getStatus());
	}
}
