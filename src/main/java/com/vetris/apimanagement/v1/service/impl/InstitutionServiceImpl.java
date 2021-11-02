package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionRepository;
import com.vetris.apimanagement.v1.service.InstitutionService;
import com.vetris.entity.Institution;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * institution serviceImpl
 * 
 * @author ShekarReddySamreddy
 * 
 */

@Service
public class InstitutionServiceImpl implements InstitutionService {

	@Autowired
	InstitutionRepository institutionRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	/*
	 * Adding Institution by requestDto
	 */
	@Override
	public CommonResponseDTO addInstitution(InstitutionRequestDTO institution) throws Exception {
		InstitutionResponseDTO institutionRespDTO = new InstitutionResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		UUID uuid = UUID.randomUUID();
		Institution resultInstitution = objectMapper.convertValue(institution, Institution.class);
		resultInstitution.setId(uuid.toString());
		resultInstitution.setCreatedBy(jwtSecurityContextUtil.getId());
		resultInstitution = institutionRepository.save(resultInstitution);
		BeanUtils.copyProperties(resultInstitution, institutionRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionRespDTO);
		resultDto.setMessage("Saved institution successfully");

		return resultDto;
	}

	/*
	 * Get all institutions
	 */
	@Override
	public CommonResponseDTO getAllInstitutions() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<Institution> institutions = institutionRepository.findAll();
		List<InstitutionResponseDTO> resultresponseDto = new ArrayList<>();
		if (institutions.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No institution found");
		} else {
			institutions.stream().forEach(existingInstitution -> {
				resultresponseDto.add(objectMapper.convertValue(existingInstitution, InstitutionResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched institution successfully");
		}

		return resultDto;
	}

	/*
	 * Get institution by id
	 */ @Override
	public CommonResponseDTO getInstitutionById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Institution existingInstitution = institutionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Institution" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionResponseDTO institutionRespDTO = objectMapper.convertValue(existingInstitution,
				InstitutionResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionRespDTO);
		resultDto.setMessage("Fetched institution successfully");

		return resultDto;
	}

	/*
	 * Updating the institution
	 */
	@Override
	public CommonResponseDTO updateInstitution(InstitutionRequestDTO institutionDto, String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Institution" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		try {
			Institution resultInstitution = objectMapper.convertValue(institutionDto, Institution.class);
			resultInstitution.setUpdateBy(jwtSecurityContextUtil.getId());
			resultInstitution.setId(id);
			resultInstitution = institutionRepository.save(resultInstitution);
			InstitutionResponseDTO institutionRespDTO = objectMapper.convertValue(resultInstitution,
					InstitutionResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionRespDTO);
			resultDto.setMessage("Fetched user successfully");
		} catch (Exception e) {
			throw new Exception(e);
		}
		return resultDto;
	}

	/*
	 * Deleting the institution
	 */
	@Override
	public CommonResponseDTO deleteInstitution(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Institution" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		institutionRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted institution successfully");
		return resultDto;
	}

}
