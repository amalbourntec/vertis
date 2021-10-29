package com.vetris.usermanagement.v1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vetris.entity.CaseNotificationRules;

/**
 * Repository for Case Notification Rules
 * 
 * @author Jose Eldhose
 *
 */
public interface CaseNotificationRulesRepository extends JpaRepository<CaseNotificationRules, Integer> {

	@Query(value = "select * from case_notification_rules where rule_no=?1 and pacs_status_id=?2 and priority_id=?3 ", nativeQuery = true)
	Optional<CaseNotificationRules> findByRuleNoANDPacsStatusIdANDPriorityId(Integer ruleNo, Integer pacsStatusId,
			Integer priorityId);

	@Query(value = "select * from case_notification_rules where rule_no=?1", nativeQuery = true)
	List<CaseNotificationRules> findByRuleNo(Integer ruleNo);

	@Query(value = "select * from case_notification_rules where pacs_status_id=?1", nativeQuery = true)
	List<CaseNotificationRules> findByPacsStatusId(Integer pacsStatusId);

	@Query(value = "select * from case_notification_rules where priority_id=?1", nativeQuery = true)
	List<CaseNotificationRules> findByPriorityId(Integer priorityId);

}
