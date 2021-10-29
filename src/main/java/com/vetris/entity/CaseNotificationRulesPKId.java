package com.vetris.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter	
@Setter
@Embeddable
public class CaseNotificationRulesPKId implements Serializable {

	private static final long serialVersionUID = 8780915865249074958L;

	private Integer ruleNo;

	private Integer pacsStatusId;

	private Integer priorityId;

	public CaseNotificationRulesPKId() {

	}

	public CaseNotificationRulesPKId(Integer ruleNo, Integer pacsStatusId, Integer priorityId) {
		this.ruleNo = ruleNo;
		this.pacsStatusId = pacsStatusId;
		this.priorityId = priorityId;
	}
}
