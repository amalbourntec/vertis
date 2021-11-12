package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionDisputeDicomTagsRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionDisputeDicomTagsResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionDisputeDicomTagsRepository;
import com.vetris.apimanagement.v1.service.InstitutionDisputeDicomTagsService;
import com.vetris.entity.InstitutionDisputeDicomTags;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionDisputeDicomTags
 * 
 * @author Dhanesh
 *
 */
@Service
public class InstitutionDisputeDicomTagsServiceImpl implements InstitutionDisputeDicomTagsService {

	@Autowired
	InstitutionDisputeDicomTagsRepository institutionDisputeDicomTagsRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public CommonResponseDTO addInstitutionDisputeDicomTags(
			InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequestDTO) throws Exception {
		InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO = new InstitutionDisputeDicomTagsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		InstitutionDisputeDicomTags resultInstitutionDisputeDicomTags = objectMapper
				.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		resultInstitutionDisputeDicomTags.setCreatedBy(jwtSecurityContextUtil.getId());

		resultInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository
				.save(resultInstitutionDisputeDicomTags);
		institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(resultInstitutionDisputeDicomTags,
				InstitutionDisputeDicomTagsResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
		resultDto.setMessage("Saved successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO getAllInstitutionDisputeDicomTags() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = institutionDisputeDicomTagsRepository.findAll();
		List<InstitutionDisputeDicomTagsResponseDTO> resultResponseDto = new ArrayList<>();
		if (institutionDisputeDicomTags.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			institutionDisputeDicomTags.stream()
					.forEach(institutionDisputeDicomTagsItem -> resultResponseDto.add(objectMapper.convertValue(
							institutionDisputeDicomTagsItem, InstitutionDisputeDicomTagsResponseDTO.class)));

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(resultResponseDto);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getInstitutionDisputeDicomTagsByInstitutionId(String institutionId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDisputeDicomTagsResponseDTO> institutionDisputeDicomTagsResponseDTO = new ArrayList<InstitutionDisputeDicomTagsResponseDTO>();
		List<InstitutionDisputeDicomTags> existingInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository
				.findAllInstitutionId(institutionId);

		if (existingInstitutionDisputeDicomTags.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			existingInstitutionDisputeDicomTags.stream().forEach(institutionDisputeDicomTagsItem -> {
				institutionDisputeDicomTagsResponseDTO.add(objectMapper.convertValue(institutionDisputeDicomTagsItem,
						InstitutionDisputeDicomTagsResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getInstitutionDisputeDicomTagsByGroupId(String groupId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDisputeDicomTagsResponseDTO> institutionDisputeDicomTagsResponseDTO = new ArrayList<InstitutionDisputeDicomTagsResponseDTO>();
		List<InstitutionDisputeDicomTags> existingInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository
				.findAllGroupId(groupId);

		if (existingInstitutionDisputeDicomTags.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			existingInstitutionDisputeDicomTags.stream().forEach(institutionDisputeDicomTagsItem -> {
				institutionDisputeDicomTagsResponseDTO.add(objectMapper.convertValue(institutionDisputeDicomTagsItem,
						InstitutionDisputeDicomTagsResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getInstitutionDisputeDicomTagsByElementId(String elementId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDisputeDicomTagsResponseDTO> institutionDisputeDicomTagsResponseDTO = new ArrayList<InstitutionDisputeDicomTagsResponseDTO>();
		List<InstitutionDisputeDicomTags> existingInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository
				.findAllElementId(elementId);

		if (existingInstitutionDisputeDicomTags.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			existingInstitutionDisputeDicomTags.stream().forEach(institutionDisputeDicomTagsItem -> {
				institutionDisputeDicomTagsResponseDTO.add(objectMapper.convertValue(institutionDisputeDicomTagsItem,
						InstitutionDisputeDicomTagsResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getByInstitutionIdORGroupIdORElementId(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDisputeDicomTagsResponseDTO> institutionDisputeDicomTagsResponseDTO = new ArrayList<InstitutionDisputeDicomTagsResponseDTO>();
		List<InstitutionDisputeDicomTags> existingInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository
				.findByInstitutionIdORGroupIdORElementId(id);

		if (existingInstitutionDisputeDicomTags.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			existingInstitutionDisputeDicomTags.stream().forEach(institutionDisputeDicomTagsItem -> {
				institutionDisputeDicomTagsResponseDTO.add(objectMapper.convertValue(institutionDisputeDicomTagsItem,
						InstitutionDisputeDicomTagsResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO updateInstitutionDisputeDicomTags(
			InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest, String institutionId,
			String groupId, String elementId) throws Exception {
		InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO = new InstitutionDisputeDicomTagsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionDisputeDicomTagsRepository
				.findByInstitutionIdANDGroupIdANDElementId(institutionId, groupId, elementId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionDisputeDicomTags resultInstitutionDisputeDicomTags = objectMapper
				.convertValue(institutionDisputeDicomTagsRequest, InstitutionDisputeDicomTags.class);
		resultInstitutionDisputeDicomTags.setUpdateBy(jwtSecurityContextUtil.getId());

		resultInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository
				.save(resultInstitutionDisputeDicomTags);

		institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(resultInstitutionDisputeDicomTags,
				InstitutionDisputeDicomTagsResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
		resultDto.setMessage("Saved successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO fetchInstitutionDisputeDicomTagsByAll(String institutionId, String groupId,
			String elementId) throws Exception {
		InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO = new InstitutionDisputeDicomTagsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTags resultInstitutionDisputeDicomTagsCheck = institutionDisputeDicomTagsRepository
				.findByInstitutionIdANDGroupIdANDElementId(institutionId, groupId, elementId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
			institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(resultInstitutionDisputeDicomTagsCheck,
					InstitutionDisputeDicomTagsResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		

		return resultDto;
	}

	@Override
	public CommonResponseDTO deleteInstitutionDisputeDicomTags(String institutionId, String groupId, String elementId)
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTags existingInstitutionsReg = institutionDisputeDicomTagsRepository
				.findByInstitutionIdANDGroupIdANDElementId(institutionId, groupId, elementId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionDisputeDicomTagsRepository.delete(existingInstitutionsReg);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setMessage("Deleted successfully");
		return resultDto;
	}

}
