package com.vetris.apimanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionRatesFeeSchedule;

/**
 * Repository for InstitutionRatesFeeSchedule
 * 
 * @author Aldrin
 *
 */
@Repository
public interface InstitutionRatesFeeScheduleRepository extends JpaRepository<InstitutionRatesFeeSchedule, String> {

}
