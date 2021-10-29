package com.vetris.usermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.request.CaseNotificationRuleHdrRequestDTO;

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
