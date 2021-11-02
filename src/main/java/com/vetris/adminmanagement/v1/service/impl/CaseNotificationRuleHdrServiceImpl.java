package com.vetris.adminmanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRuleHdrRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CaseNotificationRuleHdrResponseDTO;
import com.vetris.adminmanagement.v1.repository.CaseNotificationRuleHdrRepository;
import com.vetris.adminmanagement.v1.service.CaseNotificationRuleHdrService;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.entity.CaseNotificationRuleHdr;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for CaseNotificationRuleHdr
 * 
 * @author Aldrin
 *
 */
@Service
public class CaseNotificationRuleHdrServiceImpl implements CaseNotificationRuleHdrService {

	@Autowired
	private CaseNotificationRuleHdrRepository caseNotificationRuleHdrRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Getting CaseNotificationRuleHdr by id
	 */
	@Override
	public CommonResponseDTO getCaseNotificationRuleHdrById(Integer id) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRuleHdr existingCaseNotificationRuleHdr = caseNotificationRuleHdrRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		CaseNotificationRuleHdrResponseDTO caseNotificationRuleHdrRespDTO = objectMapper
				.convertValue(existingCaseNotificationRuleHdr, CaseNotificationRuleHdrResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleHdrRespDTO);
		resultDto.setMessage("Fetched CaseNotificationRuleHdr successfully");

		return resultDto;
	}

	/**
	 * Getting all CaseNotificationRuleHdr
	 */
	@Override
	public CommonResponseDTO getAllCaseNotificationRuleHdr() throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<CaseNotificationRuleHdr> caseNotificationRuleHdrList = caseNotificationRuleHdrRepository.findAll();
		List<CaseNotificationRuleHdrResponseDTO> caseNotificationRuleHdrRespDTO = new ArrayList<>();
		if (caseNotificationRuleHdrList.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setMessage("No CaseNotificationRuleHdr found");
		} else {
			caseNotificationRuleHdrList.stream().forEach(caseNotificationRuleHdr -> {
				caseNotificationRuleHdrRespDTO.add(
						objectMapper.convertValue(caseNotificationRuleHdr, CaseNotificationRuleHdrResponseDTO.class));
			});
		}
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleHdrRespDTO);
		resultDto.setMessage("Fetched list of CaseNotificationRuleHdr successfully");
		return resultDto;
	}

	/**
	 * Adding a CaseNotificationRuleHdr using request DTO
	 */
	@Override
	public CommonResponseDTO addCaseNotificationRuleHdr(CaseNotificationRuleHdrRequestDTO ruleHdrRequest)
			throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRuleHdr caseNotificationRuleHdr = objectMapper.convertValue(ruleHdrRequest,
				CaseNotificationRuleHdr.class);
		caseNotificationRuleHdr.setCreatedBy(jwtSecurityContextUtil.getId());
		caseNotificationRuleHdr = caseNotificationRuleHdrRepository.save(caseNotificationRuleHdr);

		CaseNotificationRuleHdrResponseDTO caseNotificationRuleHdrRespDTO = objectMapper
				.convertValue(caseNotificationRuleHdr, CaseNotificationRuleHdrResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleHdrRespDTO);
		resultDto.setMessage("CaseNotificationRuleHdr added successfully");
		return resultDto;
	}

	/**
	 * updating the existing CaseNotificationRuleHdr
	 */
	@Override
	public CommonResponseDTO updateCaseNotificationRuleHdr(CaseNotificationRuleHdrRequestDTO ruleHdrRequest, Integer id)
			throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRuleHdr caseNotificationRuleHdr = caseNotificationRuleHdrRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		BeanUtils.copyProperties(ruleHdrRequest, caseNotificationRuleHdr);
		caseNotificationRuleHdr.setUpdateBy(jwtSecurityContextUtil.getId());
		caseNotificationRuleHdr = caseNotificationRuleHdrRepository.save(caseNotificationRuleHdr);

		CaseNotificationRuleHdrResponseDTO caseNotificationRuleHdrRespDTO = objectMapper
				.convertValue(caseNotificationRuleHdr, CaseNotificationRuleHdrResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleHdrRespDTO);
		resultDto.setMessage("CaseNotificationRuleHdr updated successfully");

		return resultDto;
	}

	/**
	 * Deleting the CaseNotificationRuleHdr by rule id
	 */
	@Override
	public CommonResponseDTO deleteCaseNotificationRuleHdr(Integer id) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		caseNotificationRuleHdrRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		caseNotificationRuleHdrRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("CaseNotificationRuleHdr deleated successfully");

		return resultDto;
	}

}
