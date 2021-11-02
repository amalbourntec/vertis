package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionRegPhysicianLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionRegPhysicianLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionRegPhysicianLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionRegPhysicianLinkService;
import com.vetris.entity.InstitutionRegPhysicianLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for physician
 * 
 * @author Jose Eldhose
 *
 */
@Service
public class InstitutionRegPhysicianLinkServiceImpl implements InstitutionRegPhysicianLinkService {

	@Autowired
	InstitutionRegPhysicianLinkRepository institutionRegPhysicianLinkRepo;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	/**
	 * Getting physician by id
	 */
	@Override
	public CommonResponseDTO getPhysicianById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionRegPhysicianLink existingPhysician = institutionRegPhysicianLinkRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physician" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionRegPhysicianLinkResponseDTO physicianRespDTO = objectMapper.convertValue(existingPhysician,
				InstitutionRegPhysicianLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(physicianRespDTO);
		resultDto.setMessage("Fetched physician successfully");

		return resultDto;
	}

	/**
	 * Get all physicians
	 */

	@Override
	public CommonResponseDTO getAllPhysicians() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionRegPhysicianLink> physicians = institutionRegPhysicianLinkRepo.findAll();
		List<InstitutionRegPhysicianLinkResponseDTO> resultResponseDto = new ArrayList<>();
		if (physicians.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No user found");
		} else {
			physicians.stream().forEach(existingPhysician -> {
				resultResponseDto.add(
						objectMapper.convertValue(existingPhysician, InstitutionRegPhysicianLinkResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultResponseDto);
			resultDto.setMessage("Fetched physician successfully");
		}

		return resultDto;
	}

	/**
	 * Adding the existing physician
	 */
	@Override
	public CommonResponseDTO addPhysician(InstitutionRegPhysicianLinkRequestDTO physicianRequest) throws Exception {
		InstitutionRegPhysicianLinkResponseDTO physicianRespDTO = new InstitutionRegPhysicianLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		UUID uuid = UUID.randomUUID();
		InstitutionRegPhysicianLink resultPhysician = objectMapper.convertValue(physicianRequest,
				InstitutionRegPhysicianLink.class);
		resultPhysician.setPhysicianId(uuid.toString());
		resultPhysician.setCreatedBy(jwtSecurityContextUtil.getId());
		resultPhysician = institutionRegPhysicianLinkRepo.save(resultPhysician);
		BeanUtils.copyProperties(resultPhysician, physicianRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(physicianRespDTO);
		resultDto.setMessage("Saved physician successfully");

		return resultDto;
	}

	/**
	 * Updating a physician using request DTO
	 */
	@Override
	public CommonResponseDTO updatePhysician(InstitutionRegPhysicianLinkRequestDTO physicianDto, String id)
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionRegPhysicianLink resultPhysician = institutionRegPhysicianLinkRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physician" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
			BeanUtils.copyProperties(physicianDto, resultPhysician);
			resultPhysician.setUpdateBy(jwtSecurityContextUtil.getId());
			resultPhysician = institutionRegPhysicianLinkRepo.save(resultPhysician);
			InstitutionRegPhysicianLinkResponseDTO physicianRespDTO = objectMapper.convertValue(resultPhysician, InstitutionRegPhysicianLinkResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(physicianRespDTO);
			resultDto.setMessage("Fetched physician successfully");

		return resultDto;
	}

	/**
	 * Deleting the physician by id
	 */

	@Override
	public CommonResponseDTO deletePhysician(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionRegPhysicianLinkRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physician" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		institutionRegPhysicianLinkRepo.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted physician successfully");
		return resultDto;
	}

}
