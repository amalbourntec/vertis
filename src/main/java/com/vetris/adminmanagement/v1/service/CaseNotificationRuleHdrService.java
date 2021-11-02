package com.vetris.adminmanagement.v1.service;

import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRuleHdrRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for CaseNotificationRuleHdr
 * 
 * @author Aldrin
 *
 */
public interface CaseNotificationRuleHdrService {

	public CommonResponseDTO getCaseNotificationRuleHdrById(Integer id) throws Exception;

	public CommonResponseDTO getAllCaseNotificationRuleHdr() throws Exception;

	public CommonResponseDTO addCaseNotificationRuleHdr(CaseNotificationRuleHdrRequestDTO ruleHdrRequest) throws Exception;

	public CommonResponseDTO updateCaseNotificationRuleHdr(CaseNotificationRuleHdrRequestDTO ruleHdrRequest, Integer id)
			throws Exception;

	public CommonResponseDTO deleteCaseNotificationRuleHdr(Integer id) throws Exception;
}
