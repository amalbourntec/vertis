package com.vetris.adminmanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.CaseNotificationRuleRadiologistDtls;

/**
 * Repository for CaseNotificationRuleRadiologistDtls
 * 
 * @author Jini
 *
 */

@Repository
public interface CaseNotificationRuleRadiologistDtlsRepository
		extends JpaRepository<CaseNotificationRuleRadiologistDtls, Integer> {

}
