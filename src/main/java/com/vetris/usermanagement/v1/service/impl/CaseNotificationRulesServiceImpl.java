package com.vetris.usermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.CaseNotificationRules;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.CaseNotificationRulesRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CaseNotificationRulesResponseDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.usermanagement.v1.repository.CaseNotificationRulesRepository;
import com.vetris.usermanagement.v1.service.CaseNotificationRulesService;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for Case Notification Rules
 * 
 * @author Jose Eldhose
 *
 */
@Service
public class CaseNotificationRulesServiceImpl implements CaseNotificationRulesService {

	@Autowired
	CaseNotificationRulesRepository caseNotificationRulesRepo;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	/**
	 * Getting Case notification rule by id
	 */
	@Override
	public CommonResponseDTO getCaseNotificationRulesById(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRules existingNotificationRule = caseNotificationRulesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Case notiication rules " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		CaseNotificationRulesResponseDTO notificationRuleRespDTO = objectMapper.convertValue(existingNotificationRule,
				CaseNotificationRulesResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(notificationRuleRespDTO);
		resultDto.setMessage("Fetched case notification rules successfully");

		return resultDto;
	}

	/**
	 * Get all Case notification rules
	 */

	// @Override
	public CommonResponseDTO getAllCaseNotificationRules() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<CaseNotificationRules> notificationRules = caseNotificationRulesRepo.findAll();
		List<CaseNotificationRulesResponseDTO> resultResponseDto = new ArrayList<>();
		if (notificationRules.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No notification rule found");
		} else {
			notificationRules.stream().forEach(existingNotificationRule -> {
				resultResponseDto.add(
						objectMapper.convertValue(existingNotificationRule, CaseNotificationRulesResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultResponseDto);
			resultDto.setMessage("Fetched case notification rules successfully");
		}

		return resultDto;
	}

	/**
	 * Adding the existing Case notification rule
	 */
	@Override
	public CommonResponseDTO addCaseNotificationRules(CaseNotificationRulesRequestDTO notificationRuleRequest)
			throws Exception {
		CaseNotificationRulesResponseDTO notificationRuleRespDTO = new CaseNotificationRulesResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRules resultNotificationRule = objectMapper.convertValue(notificationRuleRequest,
				CaseNotificationRules.class);
		resultNotificationRule.setCreatedBy(jwtSecurityContextUtil.getId());
		resultNotificationRule = caseNotificationRulesRepo.save(resultNotificationRule);
		BeanUtils.copyProperties(resultNotificationRule, notificationRuleRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(notificationRuleRespDTO);
		resultDto.setMessage("Saved case notification rule successfully");

		return resultDto;
	}

	/**
	 * Updating a Case notification rule using request DTO
	 */
	@Override
	public CommonResponseDTO updateCaseNotificationRules(CaseNotificationRulesRequestDTO notificationRuleDto,
			Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRules resultNotificationRule = caseNotificationRulesRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Case notiication rules" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		try {
			BeanUtils.copyProperties(notificationRuleDto, resultNotificationRule);
			resultNotificationRule.setUpdateBy(jwtSecurityContextUtil.getId());
			resultNotificationRule = caseNotificationRulesRepo.save(resultNotificationRule);
			CaseNotificationRulesResponseDTO notificationRuleRespDTO = objectMapper.convertValue(resultNotificationRule,
					CaseNotificationRulesResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(notificationRuleRespDTO);
			resultDto.setMessage("Fetched case notification rules successfully");
		} catch (Exception e) {

			throw new Exception(e);
		}

		return resultDto;
	}

	/**
	 * Deleting the Case notification rule by id
	 */

	@Override
	public CommonResponseDTO deleteCaseNotificationRules(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		caseNotificationRulesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"Case notification rules" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		caseNotificationRulesRepo.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted case notiication rule successfully");
		return resultDto;
	}
}
