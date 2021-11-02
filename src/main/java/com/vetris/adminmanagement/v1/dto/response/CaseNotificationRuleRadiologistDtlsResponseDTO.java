package com.vetris.adminmanagement.v1.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jini CaseNotificationRuleRadiologistDtls Response DTO class
 */

@Getter
@Setter
public class CaseNotificationRuleRadiologistDtlsResponseDTO {

	private Integer ruleNo;
	private String radiologistId;
	private String userId;
	private String notifyIfScheduled;
	private String notifyAlways;

}
