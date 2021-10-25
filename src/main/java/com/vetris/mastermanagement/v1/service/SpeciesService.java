package com.vetris.mastermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.request.SpeciesRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

public interface SpeciesService {

	public CommonResponseDTO findAllSpecies()throws Exception;
	
	public CommonResponseDTO saveSpecies(SpeciesRequestDTO requestDto)throws Exception;
	
	public CommonResponseDTO editSpecies(SpeciesRequestDTO requestDto,Integer id)throws Exception;
	
	public CommonResponseDTO deleteSpecies(Integer id)throws Exception;

	public CommonResponseDTO getSpeciesById(Integer id) throws Exception;
	
}
