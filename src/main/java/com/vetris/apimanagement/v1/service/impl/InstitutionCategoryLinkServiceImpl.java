package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionCategoryLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionCategoryLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionCategoryLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionCategoryLinkService;
import com.vetris.entity.InstitutionCategoryLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for Institution Category Link
 * 
 * @author Jini
 *
 */

@Service
public class InstitutionCategoryLinkServiceImpl implements InstitutionCategoryLinkService {

	Logger logger = LoggerFactory.getLogger(InstitutionCategoryLinkServiceImpl.class);

	@Autowired
	InstitutionCategoryLinkRepository institutionCategoryLinkRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	/**
	 * Adding a institution Category link using request DTO
	 */
	@Override
	public CommonResponseDTO addInstitutionCategoryLink(InstitutionCategoryLinkRequestDTO institutionCategoryLinkDTO)
			throws Exception {

		InstitutionCategoryLinkResponseDTO institutionCategoryLinkResponseDTO = new InstitutionCategoryLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		InstitutionCategoryLink resultInstitutionCategoryLink = objectMapper.convertValue(institutionCategoryLinkDTO,
				InstitutionCategoryLink.class);
		resultInstitutionCategoryLink.setCreatedBy(jwtSecurityContextUtil.getId());

		resultInstitutionCategoryLink = institutionCategoryLinkRepository.save(resultInstitutionCategoryLink);

		BeanUtils.copyProperties(resultInstitutionCategoryLink, institutionCategoryLinkResponseDTO);
		logger.info("Saved institution Category Link successfully");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionCategoryLinkResponseDTO);
		resultDto.setMessage("Saved institution Category Link successfully");

		return resultDto;
	}

	/**
	 * Getting institution Category Link by categoryId
	 */

	@Override
	public CommonResponseDTO getInstitutionCategoryLinkById(Integer categoryId) throws Exception {

		InstitutionCategoryLinkResponseDTO institutionCategoryLinkResponseDTO = new InstitutionCategoryLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		InstitutionCategoryLink institutionCategoryLink = institutionCategoryLinkRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Institution Category Link " + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		BeanUtils.copyProperties(institutionCategoryLink, institutionCategoryLinkResponseDTO);
		logger.info("Fetched institution Category Link successfully by categoryId");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionCategoryLinkResponseDTO);
		resultDto.setMessage("Fetched institution Category Link successfully");

		return resultDto;
	}

	/**
	 * Get all institution Category Link
	 */
	@Override
	public CommonResponseDTO getAllInstitutionCategoryLink() throws Exception {

		List<InstitutionCategoryLink> institutionCategoryLinkList = institutionCategoryLinkRepository.findAll();
		List<InstitutionCategoryLinkResponseDTO> institutionCategoryLinkRespDTO = new ArrayList<>();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		if (institutionCategoryLinkList.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			logger.info("Fetched All institution Category Link successfully");
			institutionCategoryLinkList.stream().forEach(category -> institutionCategoryLinkRespDTO
					.add(objectMapper.convertValue(category, InstitutionCategoryLinkResponseDTO.class)));
		}
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionCategoryLinkRespDTO);
		resultDto.setMessage("Fetched institution Category Link successfully");

		return resultDto;
	}

	/**
	 * updating the existing institution Category link by categoryId
	 */
	@Override
	public CommonResponseDTO updateInstitutionCategoryLink(
			InstitutionCategoryLinkRequestDTO institutionCategoryLinkReqDTO, Integer categoryId) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionCategoryLink institutionCategoryLink = institutionCategoryLinkRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Institution Category Link " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		BeanUtils.copyProperties(institutionCategoryLinkReqDTO, institutionCategoryLink);
		institutionCategoryLink.setUpdateBy(jwtSecurityContextUtil.getId());
		institutionCategoryLink = institutionCategoryLinkRepository.save(institutionCategoryLink);
		InstitutionCategoryLinkResponseDTO institutionCategoryLinkResponseDTO = objectMapper
				.convertValue(institutionCategoryLink, InstitutionCategoryLinkResponseDTO.class);
		logger.info("Updated institution Category Link successfully by categoryId");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionCategoryLinkResponseDTO);
		resultDto.setMessage("Fetched user successfully");

		return resultDto;

	}

	/**
	 * Deleting the institution Category link by categoryId
	 */

	@Override
	public CommonResponseDTO deleteInstitutionCategoryLink(Integer categoryId) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionCategoryLinkRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionCategoryLinkRepository.deleteById(categoryId);
		logger.info("Deleted institution Category Link successfully by categoryId");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted Institution Category Link successfully");

		return resultDto;

	}

}
