package com.vetris.adminmanagement.v1.service;

import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRulesRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;

/**
 * service for Case Notification Rules
 * 
 * @author Jose Eldhose
 *
 */
public interface CaseNotificationRulesService {

	public CommonResponseDTO getAllCaseNotificationRules(Integer ruleNo, Integer pacsStatusId,Integer priorityId) throws Throwable;

	public CommonResponseDTO addCaseNotificationRules(CaseNotificationRulesRequestDTO caseNotificationRulesRequest)
			throws Throwable;

	public CommonResponseDTO updateCaseNotificationRules(CaseNotificationRulesRequestDTO caseNotificationRules,
			Integer ruleNo,Integer pacsStatusId,Integer priorityId) throws Throwable;

	public CommonResponseDTO deleteCaseNotificationRules(Integer ruleNo,Integer pacsStatusId,Integer priorityId) throws Throwable;

	public CommonResponseDTO getCaseNotificationRulesByRuleNo(Integer id) throws Throwable;

	public CommonResponseDTO getCaseNotificationRulesByPacsStatusId(Integer id) throws Throwable;

	public CommonResponseDTO getCaseNotificationRulesByPriorityId(Integer id) throws Throwable;

}
