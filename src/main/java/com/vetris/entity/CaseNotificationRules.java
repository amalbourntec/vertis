package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for case notification rules
 * 
 * @author Jose Eldhose
 *
 */
@Getter
@Setter
@Entity
@Table(name = "case_notification_rules")
@IdClass(CaseNotificationRulesPKId.class)
public class CaseNotificationRules extends AuditEntityModel{

	@Id
	@Column(name = "rule_no")
	private Integer ruleNo;

	@Column(name = "rule_desc")
	private String ruleDesc;
	
	@Id
	@Column(name = "pacs_status_id")
	private Integer pacsStatusId;

	@Id
	@Column(name = "priority_id")
	private Integer priorityId;

	@Column(name = "time_ellapsed_mins", nullable = true)
	private Integer timeEllapsedMins;

	@Column(name = "default_user_role", length = 10, nullable = false)
	private String defaultUserRole;

	@Column(name = "second_level_alert_receipient_id", nullable = true)
	private String secondLevelAlertReceipientId;

	@Column(name = "is_active", length = 1, nullable = true)
	private String isActive;

}
