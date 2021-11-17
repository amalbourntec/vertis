package com.vetris.adminmanagement.v1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Case Notification Rules Request DTO class
 * 
 * @author Jose Eldhose
 * 
 */
@Getter
@Setter
@Validated
public class CaseNotificationRulesRequestDTO {

	@NotNull
	private Integer ruleNo;

	@NotBlank(message = "rule description must not be empty")
	@Size(max = 500, message = "rule description must be atmost 500 characters")
	private String ruleDesc;

	@NotNull
	private Integer pacsStatusId;

	@NotNull
	private Integer priorityId;

	private Integer timeEllapsedMins;

	@NotBlank(message = "Default User Role must not be empty")
	@Size(max = 10, message = "default user role  must be atmost 10 characters")
	private String defaultUserRole;

	private String secondLevelAlertReceipientId;

	@Size(max = 1, message = "is active  must be atmost 1 character")
	private String isActive;
}
