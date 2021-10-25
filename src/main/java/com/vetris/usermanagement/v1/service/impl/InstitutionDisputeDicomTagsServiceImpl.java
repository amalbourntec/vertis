package com.vetris.usermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.InstitutionDisputeDicomTags;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.InstitutionDisputeDicomTagsRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.InstitutionDisputeDicomTagsResponseDTO;
import com.vetris.usermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.usermanagement.v1.repository.InstitutionDisputeDicomTagsRepository;
import com.vetris.usermanagement.v1.service.InstitutionDisputeDicomTagsService;
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

	/**
	 * Get All InstitutionDisputeDicomTags
	 */
	@Override
	public CommonResponseDTO addInstitutionDisputeDicomTags(InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequestDTO)
			throws Exception {
		// TODO Auto-generated method stub
		InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO = new InstitutionDisputeDicomTagsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		InstitutionDisputeDicomTags resultInstitutionDisputeDicomTags = objectMapper.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
		resultInstitutionDisputeDicomTags.setCreatedBy(jwtSecurityContextUtil.getId());
		
		resultInstitutionDisputeDicomTags=institutionDisputeDicomTagsRepository.save(resultInstitutionDisputeDicomTags);
		//BeanUtils.copyProperties(resultInstitutionDisputeDicomTags, institutionDisputeDicomTagsResponseDTO);
		institutionDisputeDicomTagsResponseDTO =objectMapper.convertValue(resultInstitutionDisputeDicomTags, InstitutionDisputeDicomTagsResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
		resultDto.setMessage("Saved successfully");

		return resultDto;
	}

	/**
	 * Getting InstitutionDisputeDicomTags by id
	 */
	@Override
	public CommonResponseDTO getAllInstitutionDisputeDicomTags() throws Exception {
		// TODO Auto-generated method stub
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDisputeDicomTags> institutionDisputeDicomTags = institutionDisputeDicomTagsRepository.findAll();
		List<InstitutionDisputeDicomTagsResponseDTO> resultResponseDto = new ArrayList<>();
		if (institutionDisputeDicomTags.isEmpty()) {
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {
			institutionDisputeDicomTags.stream().forEach(institutionDisputeDicomTagsItem -> {
				resultResponseDto.add(objectMapper.convertValue(institutionDisputeDicomTagsItem,
						InstitutionDisputeDicomTagsResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(resultResponseDto);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}
	//general get method for three PK values
	/*
	 * @Override public List<CommonResponseDTO>
	 * getInstitutionDisputeDicomTagsByInstitutionIdOrGroupIdOrElementId( String
	 * institutionOrGroupOrElementId) throws Exception { // TODO Auto-generated
	 * method stub CommonResponseDTO resultDto=new CommonResponseDTO();
	 * List<InstitutionDisputeDicomTags>
	 * institutionDisputeDicomTags=institutionDisputeDicomTagsRepository.
	 * findByInstitutionIdOrGroupIdOrElementId(institutionOrGroupOrElementId);
	 * List<InstitutionDisputeDicomTagsResponseDTO> resultResponseDto=new
	 * ArrayList<>(); if(institutionDisputeDicomTags.isEmpty()) {
	 * resultDto.setStatus(StatusType.SUCCESS.toString()); resultDto.setPayload("");
	 * resultDto.setMessage("Not found"); }else {
	 * institutionDisputeDicomTags.stream().forEach(
	 * institutionDisputeDicomTagsGeneralItem -> {
	 * resultResponseDto.add(objectMapper.convertValue(
	 * institutionDisputeDicomTagsGeneralItem,
	 * InstitutionDisputeDicomTagsResponseDTO.class)); });
	 * 
	 * resultDto.setStatus(StatusType.SUCCESS.toString());
	 * resultDto.setPayload(resultResponseDto);
	 * resultDto.setMessage("Fetched successfully"); } return null; }
	 */
	@Override
	public CommonResponseDTO getInstitutionDisputeDicomTagsByInstitutionId(String institutionId) throws Exception {
		// TODO Auto-generated method stub
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO= new InstitutionDisputeDicomTagsResponseDTO();
		InstitutionDisputeDicomTags existingInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository.findByInstitutionId(institutionId).orElseThrow(()->new ResourceNotFoundException( ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
		 institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(existingInstitutionDisputeDicomTags, InstitutionDisputeDicomTagsResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		
		return resultDto;
	}
	
	@Override
	public CommonResponseDTO getInstitutionDisputeDicomTagsByGroupId(String groupId) throws Exception {
		// TODO Auto-generated method stub
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO= new InstitutionDisputeDicomTagsResponseDTO();
		InstitutionDisputeDicomTags existingInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository.findByGroupId(groupId).orElseThrow(()->new ResourceNotFoundException( ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
		 institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(existingInstitutionDisputeDicomTags, InstitutionDisputeDicomTagsResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		
		return resultDto;
	}

	@Override
	public CommonResponseDTO getInstitutionDisputeDicomTagsByElementId(String elementId) throws Exception {
		// TODO Auto-generated method stub
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO= new InstitutionDisputeDicomTagsResponseDTO();
		InstitutionDisputeDicomTags existingInstitutionDisputeDicomTags = institutionDisputeDicomTagsRepository.findByElementId(elementId).orElseThrow(()->new ResourceNotFoundException( ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
		 institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(existingInstitutionDisputeDicomTags, InstitutionDisputeDicomTagsResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
			resultDto.setMessage("Fetched successfully");
		
		return resultDto;
	}

	
	

	@Override
	public CommonResponseDTO updateInstitutionDisputeDicomTagsByInstitutionId(InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequestDTO,
			String institutionId) throws Exception {
		// TODO Auto-generated method stub
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTags resultInstitutionDisputeDicomTags=institutionDisputeDicomTagsRepository.findByInstitutionId(institutionId).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
			try {
				BeanUtils.copyProperties(institutionDisputeDicomTagsRequestDTO,resultInstitutionDisputeDicomTags);
				//InstitutionDisputeDicomTags resultInstitutionDisputeDicomTags = objectMapper.convertValue(institutionDisputeDicomTagsRequestDTO, InstitutionDisputeDicomTags.class);
				//resultInstitutionDisputeDicomTags.setId(id);
				resultInstitutionDisputeDicomTags.setUpdateBy(jwtSecurityContextUtil.getId());
				//resultInstitutionDisputeDicomTags.setDateUpdated(jwtSecurityContextUtil.getCurrentDate());
				resultInstitutionDisputeDicomTags=institutionDisputeDicomTagsRepository.save(resultInstitutionDisputeDicomTags);
				InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(resultInstitutionDisputeDicomTags, InstitutionDisputeDicomTagsResponseDTO.class);
				resultDto.setStatus(StatusType.SUCCESS.toString());
				resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
				resultDto.setMessage("Fetched successfully");
			} catch (Exception e) {
				
				throw new Exception(e);
			}

		
		return resultDto;
	}
	@Override
	public CommonResponseDTO updateInstitutionDisputeDicomTagsByGroupId(
			InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequestDTO, String groupId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTags resultInstitutionDisputeDicomTags=institutionDisputeDicomTagsRepository.findByGroupId(groupId).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
			try {
				BeanUtils.copyProperties(institutionDisputeDicomTagsRequestDTO,resultInstitutionDisputeDicomTags);
				resultInstitutionDisputeDicomTags.setUpdateBy(jwtSecurityContextUtil.getId());
				resultInstitutionDisputeDicomTags=institutionDisputeDicomTagsRepository.save(resultInstitutionDisputeDicomTags);
				InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(resultInstitutionDisputeDicomTags, InstitutionDisputeDicomTagsResponseDTO.class);
				resultDto.setStatus(StatusType.SUCCESS.toString());
				resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
				resultDto.setMessage("Fetched successfully");
			} catch (Exception e) {
				
				throw new Exception(e);
			}

		
		return resultDto;
	}
	@Override
	public CommonResponseDTO updateInstitutionDisputeDicomTagsByElementId(
			InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequestDTO, String elementId)
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDisputeDicomTags resultInstitutionDisputeDicomTags=institutionDisputeDicomTagsRepository.findByElementId(elementId).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
			try {
				BeanUtils.copyProperties(institutionDisputeDicomTagsRequestDTO,resultInstitutionDisputeDicomTags);
				resultInstitutionDisputeDicomTags.setUpdateBy(jwtSecurityContextUtil.getId());
				resultInstitutionDisputeDicomTags=institutionDisputeDicomTagsRepository.save(resultInstitutionDisputeDicomTags);
				InstitutionDisputeDicomTagsResponseDTO institutionDisputeDicomTagsResponseDTO = objectMapper.convertValue(resultInstitutionDisputeDicomTags, InstitutionDisputeDicomTagsResponseDTO.class);
				resultDto.setStatus(StatusType.SUCCESS.toString());
				resultDto.setPayload(institutionDisputeDicomTagsResponseDTO);
				resultDto.setMessage("Fetched successfully");
			} catch (Exception e) {
				
				throw new Exception(e);
			}

		
		return resultDto;
	}

	/*
	 * @Override public CommonResponseDTO deleteInstitutionDisputeDicomTags(String
	 * id) throws Exception { // TODO Auto-generated method stub CommonResponseDTO
	 * resultDto = new CommonResponseDTO(); Optional<InstitutionDisputeDicomTags>
	 * existingInstitutionDisputeDicomTags =
	 * institutionDisputeDicomTagsRepository.findById(id); if
	 * (existingInstitutionDisputeDicomTags.isPresent()) {
	 * institutionDisputeDicomTagsRepository.deleteById(id);
	 * resultDto.setStatus(StatusType.SUCCESS.toString());
	 * resultDto.setMessage("Deleted successfully"); } else {
	 * resultDto.setStatus(StatusType.FAILURE.toString());
	 * resultDto.setMessage("Unable to fetch details"); } return resultDto; }
	 */

	/*
	 * @Override public CommonResponseDTO
	 * deleteInstitutionDisputeDicomTagsByInstitutionId(String institutionId) throws
	 * Exception { CommonResponseDTO resultDto = new CommonResponseDTO();
	 * Optional<InstitutionDisputeDicomTags> existingInstitutionDisputeDicomTags =
	 * institutionDisputeDicomTagsRepository.findByInstitutionId(institutionId); if
	 * (existingInstitutionDisputeDicomTags.isPresent()) {
	 * institutionDisputeDicomTagsRepository.deleteByInstitutionId(institutionId);
	 * resultDto.setStatus(StatusType.SUCCESS.toString());
	 * resultDto.setMessage("Deleted successfully"); } else {
	 * resultDto.setStatus(StatusType.FAILURE.toString());
	 * resultDto.setMessage("Unable to fetch details"); } return resultDto; }
	 */

}
