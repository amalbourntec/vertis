package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for CaseNotificationRuleHdr
 * 
 * @author Aldrin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "case_notification_rule_hdr")
public class CaseNotificationRuleHdr extends AuditEntityModel{
	
	@Id
	@Column(name = "rule_no", length = 200)
	private Integer ruleNo;
	
	@Column(name = "rule_desc", length = 500, nullable = false)
	private String ruleDesc;
	
	@Column(name = "pacs_status_id", length = 200, nullable = false)
	private Integer pacsStatusId;
	
	@Column(name = "priority_id", length = 200, nullable = false)
	private Integer priorityId;
	
	@Column(name = "time_ellapsed_mins", length = 20, nullable = true)
	private Integer timeEllapsedMins;
	
	@Column(name = "is_active", length = 1, nullable = true)
	private String isActive;
	
	@Column(name = "for_sms_verification", length = 1, nullable = true)
	private String forSmsVerification;
	
	@Column(name = "time_left_mins", length = 20, nullable = true)
	private Integer timeLeftMins;
	
	@Column(name = "notify_by_time", length = 1, nullable = true)
	private String notifyByTime;
	

}
