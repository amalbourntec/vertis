package com.vetris.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class CaseNotificationRules extends AuditEntityModel{

	@Id
	@Column(name = "rule_no")
	private Integer ruleNo;

	@Column(name = "rule_desc", nullable = false, length = 500)
	private String ruleDesc;
	

	@Column(name = "pacs_status_id", length = 200)
	private Integer pacsStatusId;


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
