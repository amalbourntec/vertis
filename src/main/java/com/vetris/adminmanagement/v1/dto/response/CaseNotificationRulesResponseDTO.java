package com.vetris.adminmanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO for Case Notification Rule
 * 
 * @author Jose Eldhose
 *
 */
@Getter
@Setter
public class CaseNotificationRulesResponseDTO {

	private Integer ruleNo;
	private String ruleDesc;
	private Integer pacsStatusId;
	private Integer priorityId;
	private Integer timeEllapsedMins;
	private String defaultUserRole;
	private Date dateUpdated;
	private String updateBy;
	private String secondLevelAlertReceipientId;
	private String isActive;
}
