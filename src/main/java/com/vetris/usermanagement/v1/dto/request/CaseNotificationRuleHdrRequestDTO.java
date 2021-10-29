package com.vetris.usermanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

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
	
	
	@Size(max = 200, message = "ruleNo  must be atmost 200 integer")
	@NotNull
	private Integer ruleNo;

	@Size(max = 500, message = "ruleDesc  must be atmost 500 characters")
	@NotNull
	private String ruleDesc;

	@Size(max = 200, message = "pacsStatusId must be atmost 200 integer")
	@NotNull
	private Integer pacsStatusId;

	@Size(max = 200, message = "priorityId must be atmost 200 integer")
	@NotNull
	private Integer priorityId;

	@Size(max = 20, message = "timeEllapsedMins must be atmost 20 integer")
	private Integer timeEllapsedMins;

	@Size(max = 1, message = "isActive must be atmost 1 character")
	private String isActive;

	@Size(max = 1, message = "forSmsVerification must be atmost 1 character")
	private String forSmsVerification;

	@Size(max = 20, message = "timeLeftMins must be atmost 20 integer")
	private Integer timeLeftMins;

	@Size(max = 1, message = "notifyByTime must be atmost 1 character")
	private String notifyByTime;
}
