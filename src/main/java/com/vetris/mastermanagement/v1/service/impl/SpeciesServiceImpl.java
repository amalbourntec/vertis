package com.vetris.mastermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.Species;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.mastermanagement.v1.dto.request.SpeciesRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.mastermanagement.v1.dto.response.SpeciesResponseDTO;
import com.vetris.mastermanagement.v1.repository.SpeciesRepository;
import com.vetris.mastermanagement.v1.service.SpeciesService;
import com.vetris.mastermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.utils.JWTSecurityContextUtil;

@Service
public class SpeciesServiceImpl implements SpeciesService {

	@Autowired
	SpeciesRepository speciesRepository;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	JWTSecurityContextUtil jwtSecurityContextUtil;

	@Override
	public CommonResponseDTO findAllSpecies() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<Species> species = speciesRepository.findAll();
		List<SpeciesResponseDTO> resultresponseDto = new ArrayList<>();
		if (species.isEmpty()) {
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload("");
			resultDto.setMessage("No Species found");
		} else {
			species.stream().forEach(speciesItem -> {
				resultresponseDto.add(objectMapper.convertValue(speciesItem, SpeciesResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched species successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getSpeciesById(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Species existingSpecies =speciesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Species " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		SpeciesResponseDTO speciesResponseDTO = objectMapper.convertValue(existingSpecies, SpeciesResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(speciesResponseDTO);
		resultDto.setMessage("Fetched species successfully");

		return resultDto;
	}
	
	@Override
	public CommonResponseDTO saveSpecies(SpeciesRequestDTO requestDto) throws Exception {
		SpeciesResponseDTO speciesRespDTO = new SpeciesResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		
		Species resultSpecies = objectMapper.convertValue(requestDto, Species.class);
		resultSpecies.setCreatedBy(jwtSecurityContextUtil.getId());
		resultSpecies = speciesRepository.save(resultSpecies);
		BeanUtils.copyProperties(resultSpecies, speciesRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(speciesRespDTO);
		resultDto.setMessage("Saved species successfully");
		
		return resultDto;
	}

	@Override
	public CommonResponseDTO editSpecies(SpeciesRequestDTO requestDto, Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Species species =  speciesRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Species" +ErrorCodes.DATA_NOT_FOUND.getMessage()));

		try {
			BeanUtils.copyProperties(requestDto, species);
			species.setUpdateBy(jwtSecurityContextUtil.getId());
			species = speciesRepository.save(species);
			SpeciesResponseDTO speciesResponseDTO= objectMapper.convertValue(species, SpeciesResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(speciesResponseDTO);
			resultDto.setMessage("Fetched species successfully");
		} catch (Exception e) {

			throw new Exception(e);
		}
		return resultDto;
		
	}	

	@Override
	public CommonResponseDTO deleteSpecies(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		speciesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Species" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		speciesRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setMessage("Deleted Species successfully");
		return resultDto;
	}

}
