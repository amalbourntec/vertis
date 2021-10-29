package com.vetris.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.CaseNotificationRuleHdr;

/**
 * Repository for CaseNotificationRuleHdr
 * 
 * @author Aldrin
 *
 */
@Repository
public interface CaseNotificationRuleHdrRepository extends JpaRepository<CaseNotificationRuleHdr, Integer>{

}
