package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionRegModalityLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionRegModalityLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionRegModalityLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionRegModalityLinkService;
import com.vetris.entity.InstitutionRegModalityLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionRegModalityLink
 * 
 * @author Jose Eldhose
 *
 */
@Service
public class InstitutionRegModalityLinkServiceImpl implements InstitutionRegModalityLinkService {

	@Autowired
	InstitutionRegModalityLinkRepository institutionRegModalityLinkRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Adding new registration modality.
	 */
	@Override
	public CommonResponseDTO addRegModality(InstitutionRegModalityLinkRequestDTO data) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UUID uuid = UUID.randomUUID();
		InstitutionRegModalityLink regModality = objectMapper.convertValue(data, InstitutionRegModalityLink.class);
		regModality.setModalityId(uuid.toString());
		regModality.setCreatedBy(jwtSecurityContextUtil.getId());
		regModality = institutionRegModalityLinkRepository.save(regModality);
		InstitutionRegModalityLinkResponseDTO regModalityRespDTO = objectMapper.convertValue(regModality,
				InstitutionRegModalityLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(regModalityRespDTO);
		resultDto.setMessage("Registration modality added successfully");
		return resultDto;
	}

	/**
	 * Getting all registration modality.
	 */
	@Override
	public CommonResponseDTO getAllRegModality() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionRegModalityLink> institutionRegModalityList = institutionRegModalityLinkRepository.findAll();
		List<InstitutionRegModalityLinkResponseDTO> regModalityRespDTO = new ArrayList<>();
		if (institutionRegModalityList.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setMessage("Institution reg modality not found");
		} else {
			institutionRegModalityList.stream().forEach(regModality -> {
				regModalityRespDTO
						.add(objectMapper.convertValue(regModality, InstitutionRegModalityLinkResponseDTO.class));
			});
		}
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(regModalityRespDTO);
		resultDto.setMessage("Fetched list of institution reg modality successfully");
		return resultDto;
	}

	/**
	 * Getting registration modality by Id.
	 */
	@Override
	public CommonResponseDTO getRegModalityById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionRegModalityLink existingRegModality = institutionRegModalityLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionRegModalityLinkResponseDTO regModalityRespDTO = objectMapper.convertValue(existingRegModality,
				InstitutionRegModalityLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(regModalityRespDTO);
		resultDto.setMessage("Fetched institution reg modality successfully");

		return resultDto;
	}

	/**
	 * modifying registration modality.
	 */
	@Override
	public CommonResponseDTO updateInstitutionRegModality(InstitutionRegModalityLinkRequestDTO regModalityRequest,
			String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionRegModalityLink resultModality = institutionRegModalityLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Modality" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
			BeanUtils.copyProperties(regModalityRequest, resultModality);
			resultModality.setUpdateBy(jwtSecurityContextUtil.getId());
			resultModality = institutionRegModalityLinkRepository.save(resultModality);
			InstitutionRegModalityLinkResponseDTO modalityRespDTO = objectMapper.convertValue(resultModality,
					InstitutionRegModalityLinkResponseDTO.class);
//			InstitutionRegModalityLinkResponseDTO modalityRespDTO =new InstitutionRegModalityLinkResponseDTO();
//			BeanUtils.copyProperties(resultModality, modalityRespDTO);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(modalityRespDTO);
			resultDto.setMessage("Fetched modality successfully");

		return resultDto;
	}

	/**
	 * Deleting registration modality by Id.
	 */
	@Override
	public CommonResponseDTO deleteInstitutionRegModality(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionRegModalityLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		institutionRegModalityLinkRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Institution reg modality deleated successfully");

		return resultDto;
	}
}
