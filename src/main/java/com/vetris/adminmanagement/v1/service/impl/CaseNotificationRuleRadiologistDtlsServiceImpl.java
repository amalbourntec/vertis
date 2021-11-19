package com.vetris.adminmanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRuleRadiologistDtlsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CaseNotificationRuleRadiologistDtlsResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.CaseNotificationRuleRadiologistDtlsRepository;
import com.vetris.adminmanagement.v1.service.CaseNotificationRuleRadiologistDtlsService;
import com.vetris.entity.CaseNotificationRuleRadiologistDtls;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;

/**
 * Service Implementation for CaseNotificationRuleRadiologistDtls
 * 
 * @author Jini
 *
 */

@Service
public class CaseNotificationRuleRadiologistDtlsServiceImpl implements CaseNotificationRuleRadiologistDtlsService {

	Logger logger = LoggerFactory.getLogger(CaseNotificationRuleRadiologistDtlsServiceImpl.class);
	
	@Autowired
	CaseNotificationRuleRadiologistDtlsRepository caseNotificationRuleRadiologistDtlsRepository;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Get all Case Notification Rule Radiologist Dtls..
	 */
	@Override
	public CommonResponseDTO getAllCaseNotificationRuleRadiologistDtls() throws Exception {
		List<CaseNotificationRuleRadiologistDtls> caseNotificationRuleRadiologistDtlsList = caseNotificationRuleRadiologistDtlsRepository
				.findAll();
		List<CaseNotificationRuleRadiologistDtlsResponseDTO> caseNotificationRuleRadiologistDtlsRespDTO = new ArrayList<>();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		if (caseNotificationRuleRadiologistDtlsList.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			logger.info("Fetched All Case Notification Rule Radiologist Dtls successfully");
			caseNotificationRuleRadiologistDtlsList.stream()
					.forEach(ruleRadiologistDtls -> caseNotificationRuleRadiologistDtlsRespDTO.add(objectMapper
							.convertValue(ruleRadiologistDtls, CaseNotificationRuleRadiologistDtlsResponseDTO.class)));
		}

		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleRadiologistDtlsRespDTO);
		resultDto.setMessage("Fetched Case Notification Rule Radiologist Dtls Successfully");

		return resultDto;
	}

	/**
	 * Getting Case Notification Rule Radiologist Dtls by ruleNo
	 */
	@Override
	public CommonResponseDTO getCaseNotificationRuleRadiologistDtlsById(Integer ruleNo) throws Exception {

		CaseNotificationRuleRadiologistDtlsResponseDTO caseNotificationRuleRadiologistDtlsRespDTO = new CaseNotificationRuleRadiologistDtlsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		CaseNotificationRuleRadiologistDtls caseNotificationRuleRadiologistDtlsList = caseNotificationRuleRadiologistDtlsRepository
				.findById(ruleNo).orElseThrow(() -> new ResourceNotFoundException(
						"Case Notification Rule Radiologist Dtls " + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		BeanUtils.copyProperties(caseNotificationRuleRadiologistDtlsList, caseNotificationRuleRadiologistDtlsRespDTO);
		logger.info("Fetched Case Notification Rule Radiologist Dtls successfully by ruleNo");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleRadiologistDtlsRespDTO);
		resultDto.setMessage("Fetched Case Notification Rule Radiologist Dtls Successfully");

		return resultDto;
	}

	/**
	 * Adding a Case Notification Rule Radiologist Dtls using request DTO
	 */
	@Override
	public CommonResponseDTO addCaseNotificationRuleRadiologistDtls(
			CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO)
			throws Exception {

		CaseNotificationRuleRadiologistDtlsResponseDTO caseNotificationRuleRadiologistDtlsRespDTO = new CaseNotificationRuleRadiologistDtlsResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRuleRadiologistDtls resultCaseNotificationRuleRadiologistDtls = objectMapper
				.convertValue(caseNotificationRuleRadiologistDtlsRequestDTO, CaseNotificationRuleRadiologistDtls.class);

		resultCaseNotificationRuleRadiologistDtls = caseNotificationRuleRadiologistDtlsRepository
				.save(resultCaseNotificationRuleRadiologistDtls);

		BeanUtils.copyProperties(resultCaseNotificationRuleRadiologistDtls, caseNotificationRuleRadiologistDtlsRespDTO);
		logger.info("Saved Case Notification Rule Radiologist Dtls successfully");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleRadiologistDtlsRespDTO);
		resultDto.setMessage("Saved Case Notification Rule Radiologist Dtls Successfully");

		return resultDto;
	}

	/**
	 * updating the existing Case Notification Rule Radiologist Dtls by ruleNo
	 */
	@Override
	public CommonResponseDTO updateCaseNotificationRuleRadiologistDtls(
			CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO, Integer ruleNo)
			throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRuleRadiologistDtls caseNotificationRuleRadiologistDtlsLink = caseNotificationRuleRadiologistDtlsRepository
				.findById(ruleNo).orElseThrow(() -> new ResourceNotFoundException(
						"Case Notification Rule Radiologist Dtls " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		BeanUtils.copyProperties(caseNotificationRuleRadiologistDtlsRequestDTO,
				caseNotificationRuleRadiologistDtlsLink);
		caseNotificationRuleRadiologistDtlsLink = caseNotificationRuleRadiologistDtlsRepository
				.save(caseNotificationRuleRadiologistDtlsLink);
		CaseNotificationRuleRadiologistDtlsResponseDTO caseNotificationRuleRadiologistDtlsRespDTO = objectMapper
				.convertValue(caseNotificationRuleRadiologistDtlsLink,
						CaseNotificationRuleRadiologistDtlsResponseDTO.class);

		logger.info("Updated Case Notification Rule Radiologist Dtls successfully by ruleNo");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(caseNotificationRuleRadiologistDtlsRespDTO);
		resultDto.setMessage("Updated Case Notification Rule Radiologist successfully");

		return resultDto;
	}

	/**
	 * Deleting the Case Notification Rule Radiologist Dtls by ruleNo
	 */
	@Override
	public CommonResponseDTO deleteCaseNotificationRuleRadiologistDtls(Integer ruleNo) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		caseNotificationRuleRadiologistDtlsRepository.findById(ruleNo).orElseThrow(() -> new ResourceNotFoundException(
				"Case Notification Rule Radiologist Dtls " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		caseNotificationRuleRadiologistDtlsRepository.deleteById(ruleNo);
		logger.info("Deleted Case Notification Rule Radiologist Dtls successfully by ruleNo");
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted Case Notification Rule Radiologist Dtls Successfully");

		return resultDto;
	}
}
