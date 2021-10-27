package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.CaseNotificationRuleRadiologistDtlsRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for CaseNotificationRuleRadiologistDtls
 * @author Jini
 *
 */

public interface CaseNotificationRuleRadiologistDtlsService {

	public CommonResponseDTO getAllCaseNotificationRuleRadiologistDtls() throws Exception;

	public CommonResponseDTO getCaseNotificationRuleRadiologistDtlsById(Integer ruleNo) throws Exception;

	public CommonResponseDTO addCaseNotificationRuleRadiologistDtls(
			CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO) throws Exception;

	public CommonResponseDTO updateCaseNotificationRuleRadiologistDtls(
			CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO,
			Integer ruleNo) throws Exception;

	public CommonResponseDTO deleteCaseNotificationRuleRadiologistDtls(Integer ruleNo)throws Exception;

}
