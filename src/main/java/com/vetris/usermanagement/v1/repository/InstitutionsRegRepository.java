package com.vetris.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionsReg;

/**
 * Repository for InstitutionDisputeDicomTags
 * @author Dhanesh
 *
 */
@Repository
public interface InstitutionsRegRepository extends JpaRepository<InstitutionsReg, String> {

}
