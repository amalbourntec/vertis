package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.CaseNotificationRulesRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * service for Case Notification Rules
 * 
 * @author Jose Eldhose
 *
 */
public interface CaseNotificationRulesService {

	public CommonResponseDTO getCaseNotificationRulesById(Integer id) throws Exception;

	public CommonResponseDTO getAllCaseNotificationRules() throws Exception;

	public CommonResponseDTO addCaseNotificationRules(CaseNotificationRulesRequestDTO caseNotificationRulesRequest)
			throws Exception;

	public CommonResponseDTO updateCaseNotificationRules(CaseNotificationRulesRequestDTO caseNotificationRules,
			Integer id) throws Exception;

	public CommonResponseDTO deleteCaseNotificationRules(Integer id) throws Exception;

}
