package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for CaseNotificationRuleRadiologistDtls
 * @author Jini
 *
 */
@Getter
@Setter
@Entity
@Table(name = "case_notification_rule_radiologist_dtls")
public class CaseNotificationRuleRadiologistDtls {
	
	@Id
	@Column(name = "rule_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer RuleNo;
	
	@Column(name = "radiologist_id ", nullable = false)
	private String RadiologistId;
	
	@Column(name = "user_id", nullable = true)
	private String UserId;
	
	@Column(name = "notify_if_scheduled", length = 1, nullable = true)
	private String NotifyIfScheduled;
	
	@Column(name = "notify_always", length = 1, nullable = true)
	private String NotifyAlways;

}
