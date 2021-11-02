package com.vetris.adminmanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO for CaseNotificationRuleHdr
 * 
 * @author Aldrin
 */
@Getter
@Setter
public class CaseNotificationRuleHdrResponseDTO {

	private Integer ruleNo;

	private String ruleDesc;

	private Integer pacsStatusId;

	private Integer priorityId;

	private Integer timeEllapsedMins;

	private String isActive;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;

	private String forSmsVerification;

	private Integer timeLeftMins;

	private String notifyByTime;

}
