package com.vetris.adminmanagement.v1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO for CaseNotificationRuleHdr
 * 
 * @author Aldrin
 */

@Getter
@Setter
@Validated
public class CaseNotificationRuleHdrRequestDTO {
	
	@NotNull
	private Integer ruleNo;

	@Size(max = 500, message = "ruleDesc  must be atmost 500 characters")
	@NotBlank(message="RuleDesc must not be empty")
	private String ruleDesc;

	@NotNull
	private Integer pacsStatusId;

	@NotNull
	private Integer priorityId;

	private Integer timeEllapsedMins;

	@Size(max = 1, message = "isActive must be atmost 1 character")
	private String isActive;

	@Size(max = 1, message = "forSmsVerification must be atmost 1 character")
	private String forSmsVerification;

	private Integer timeLeftMins;

	@Size(max = 1, message = "notifyByTime must be atmost 1 character")
	private String notifyByTime;
}
