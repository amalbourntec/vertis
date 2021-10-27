package com.vetris.usermanagement.v1.dto.response;



import lombok.Getter;
import lombok.Setter;

/**
 * @author Jini
 * CaseNotificationRuleRadiologistDtls Response DTO class
 */

@Getter
@Setter
public class CaseNotificationRuleRadiologistDtlsResponseDTO {
	
	private Integer RuleNo;
	private String RadiologistId;
	private String UserId;
	private String NotifyIfScheduled;
	private String NotifyAlways;
	
}
