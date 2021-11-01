package com.vetris.mastermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.InstitutionUserLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.mastermanagement.v1.dto.request.InstitutionUserLinkRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.mastermanagement.v1.dto.response.InstitutionUserLinkResponseDTO;
import com.vetris.mastermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.mastermanagement.v1.repository.InstitutionUserLinkRepository;
import com.vetris.mastermanagement.v1.service.InstitutionUserLinkService;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionUserLink
 * 
 * @author Dhanesh
 *
 */
@Service
public class InstitutionUserLinkServiceImpl implements InstitutionUserLinkService {

	@Autowired
	InstitutionUserLinkRepository institutionUserLinkRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	JWTSecurityContextUtil jwtSecurityContextUtil;

	@Override
	public CommonResponseDTO findAllInstitutionUserLink() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLink> institutionUserLink = institutionUserLinkRepository.findAll();
		List<InstitutionUserLinkResponseDTO> responseDTO = new ArrayList<>();
		if (institutionUserLink.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {
			institutionUserLink.stream().forEach(institutionUserLinkItem -> responseDTO
					.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class)));

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(responseDTO);
			resultDto.setMessage("Fetched successfully");
		}
		return resultDto;
	}

	@Override
	public CommonResponseDTO findByUserId(String userId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink existingInstitutionUserLink = institutionUserLinkRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = objectMapper
				.convertValue(existingInstitutionUserLink, InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Fetched successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO findByInstitutionId(String institutionId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = new InstitutionUserLinkResponseDTO();
		InstitutionUserLink existingInstitutionUserLink = institutionUserLinkRepository
				.findByInstitutionId(institutionId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionUserLinkResponseDTO = objectMapper.convertValue(existingInstitutionUserLink,
				InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Fetched successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO saveInstitutionUserLinks(InstitutionUserLinkRequestDTO requestDto) throws Exception {

		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = new InstitutionUserLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		InstitutionUserLink resultInstitutionUserLink = objectMapper.convertValue(requestDto,
				InstitutionUserLink.class);
		resultInstitutionUserLink.setCreatedBy(jwtSecurityContextUtil.getId());
		resultInstitutionUserLink = institutionUserLinkRepository.save(resultInstitutionUserLink);
		institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLink,
				InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Saved successfully");

		return resultDto;

	}

	@Override
	public CommonResponseDTO deleteInstitutionUserLink(String institutionId, String userId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink existingInstitutionUserLink = institutionUserLinkRepository
				.findByInstitutionIdANDUserId(institutionId, userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionUserLinkRepository.delete(existingInstitutionUserLink);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setMessage("Deleted successfully");
		return resultDto;
	}

	@Override
	public CommonResponseDTO updateInstitutionUserLink(InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO,
			String institutionId, String userId) throws Exception {
		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = new InstitutionUserLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink resultInstitutionUserLinkCheck = institutionUserLinkRepository
				.findByInstitutionIdANDUserId(institutionId, userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		if (resultInstitutionUserLinkCheck == null) {
			resultDto.setStatus(StatusType.FAILURE.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {

			InstitutionUserLink resultInstitutionUserLink = objectMapper.convertValue(institutionUserLinkRequestDTO,
					InstitutionUserLink.class);
			resultInstitutionUserLink.setUpdateBy(jwtSecurityContextUtil.getId());

			resultInstitutionUserLink = institutionUserLinkRepository.save(resultInstitutionUserLink);

			institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLink,
					InstitutionUserLinkResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionUserLinkResponseDTO);
			resultDto.setMessage("Saved successfully");
		}
		return resultDto;
	}

	@Override
	public CommonResponseDTO fetchInstitutionUserLinkByAll(String institutionId, String userId) throws Exception {
		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = new InstitutionUserLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink resultInstitutionUserLinkCheck = institutionUserLinkRepository
				.findByInstitutionIdANDUserId(institutionId, userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		if (resultInstitutionUserLinkCheck == null) {
			resultDto.setStatus(StatusType.FAILURE.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {
			institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLinkCheck,
					InstitutionUserLinkResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionUserLinkResponseDTO);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getByInstitutionIdORUserId(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLinkResponseDTO> institutionUserLinkResponseDTO = new ArrayList<InstitutionUserLinkResponseDTO>();
		List<InstitutionUserLink> existingInstitutionUserLink = institutionUserLinkRepository
				.findByInstitutionIdORUserId(id);

		if (existingInstitutionUserLink.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {
			existingInstitutionUserLink.stream().forEach(institutionUserLinkItem -> {
				institutionUserLinkResponseDTO
						.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionUserLinkResponseDTO);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

}
