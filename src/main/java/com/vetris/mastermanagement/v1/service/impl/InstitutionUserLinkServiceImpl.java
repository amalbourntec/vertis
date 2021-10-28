package com.vetris.mastermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
			institutionUserLink.stream().forEach(institutionUserLinkItem -> {
				responseDTO
						.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(responseDTO);
			resultDto.setMessage("Fetched successfully");
		}
		return resultDto;
	}

	@Override
	public CommonResponseDTO findByUserId(String userId) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink existingInstitutionUserLink = institutionUserLinkRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

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

		InstitutionUserLink resultInstitutionUserLink = objectMapper.convertValue(requestDto, InstitutionUserLink.class);
		resultInstitutionUserLink.setCreatedBy(jwtSecurityContextUtil.getId());
		resultInstitutionUserLink=institutionUserLinkRepository.save(resultInstitutionUserLink);
		institutionUserLinkResponseDTO =objectMapper.convertValue(resultInstitutionUserLink, InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Saved successfully");

		return resultDto;
		
	}

	@Override
	public CommonResponseDTO updateInstitutionUserLinkByUserId(InstitutionUserLinkRequestDTO requestDto, String userId)
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink resultInstitutionUserLink=institutionUserLinkRepository.findByUserId(userId).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
			try {
				BeanUtils.copyProperties(requestDto,resultInstitutionUserLink);
				resultInstitutionUserLink.setUpdateBy(jwtSecurityContextUtil.getId());
				resultInstitutionUserLink=institutionUserLinkRepository.save(resultInstitutionUserLink);
				InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLink, InstitutionUserLinkResponseDTO.class);
				resultDto.setStatus(StatusType.SUCCESS.toString());
				resultDto.setPayload(institutionUserLinkResponseDTO);
				resultDto.setMessage("Fetched successfully");
			} catch (Exception e) {
				
				throw new Exception(e);
			}

		
		return resultDto;
	}

	@Override
	public CommonResponseDTO updateInstitutionUserLinkByInstitutionId(InstitutionUserLinkRequestDTO requestDto,
			String institutionId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink resultInstitutionUserLink=institutionUserLinkRepository.findByInstitutionId(institutionId).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
			try {
				BeanUtils.copyProperties(requestDto,resultInstitutionUserLink);
				resultInstitutionUserLink.setUpdateBy(jwtSecurityContextUtil.getId());
				resultInstitutionUserLink=institutionUserLinkRepository.save(resultInstitutionUserLink);
				InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLink, InstitutionUserLinkResponseDTO.class);
				resultDto.setStatus(StatusType.SUCCESS.toString());
				resultDto.setPayload(institutionUserLinkResponseDTO);
				resultDto.setMessage("Fetched successfully");
			} catch (Exception e) {
				
				throw new Exception(e);
			}

		
		return resultDto;
	}

	@Override
	public CommonResponseDTO deleteInstitutionUserLinkByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
