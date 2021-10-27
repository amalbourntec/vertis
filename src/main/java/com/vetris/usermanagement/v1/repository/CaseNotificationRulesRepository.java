package com.vetris.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetris.entity.CaseNotificationRules;

/**
 * Repository for Case Notification Rules
 * 
 * @author Jose Eldhose
 *
 */
public interface CaseNotificationRulesRepository extends JpaRepository<CaseNotificationRules, Integer> {

}
