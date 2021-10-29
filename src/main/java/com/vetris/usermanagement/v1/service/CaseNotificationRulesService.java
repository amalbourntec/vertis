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

	public CommonResponseDTO getAllCaseNotificationRules(Integer ruleNo, Integer pacsStatusId,Integer priorityId) throws Exception;

	public CommonResponseDTO addCaseNotificationRules(CaseNotificationRulesRequestDTO caseNotificationRulesRequest)
			throws Exception;

	public CommonResponseDTO updateCaseNotificationRules(CaseNotificationRulesRequestDTO caseNotificationRules,
			Integer ruleNo,Integer pacsStatusId,Integer priorityId) throws Exception;

	public CommonResponseDTO deleteCaseNotificationRules(Integer ruleNo,Integer pacsStatusId,Integer priorityId) throws Exception;

	public CommonResponseDTO getCaseNotificationRulesByRuleNo(Integer id) throws Exception;

	public CommonResponseDTO getCaseNotificationRulesByPacsStatusId(Integer id) throws Exception;

	public CommonResponseDTO getCaseNotificationRulesByPriorityId(Integer id) throws Exception;

}
