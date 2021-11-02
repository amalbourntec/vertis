package com.vetris.adminmanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jose Eldhose
 * Case Notification Rules Request DTO class
 */
@Getter
@Setter
@Validated
public class CaseNotificationRulesRequestDTO {

	@NotNull
	private Integer ruleNo;

	@NotNull
	@Size(max = 500, message = "rule description must be atmost 500 characters")
	private String ruleDesc;

	@NotNull
	private Integer pacsStatusId;

	@NotNull
	private Integer priorityId;

	private Integer timeEllapsedMins;

	@NotNull
	@Size(max = 10, message = "default user role  must be atmost 10 characters")
	private String defaultUserRole;

	private String secondLevelAlertReceipientId;

	@Size(max = 1, message = "is active  must be atmost 1 character")
	private String isActive;
}
