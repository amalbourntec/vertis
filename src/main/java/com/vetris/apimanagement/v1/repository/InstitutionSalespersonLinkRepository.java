package com.vetris.apimanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionSalespersonLink;

/**
 * Repository for InstitutionSalespersonLink
 * 
 * @author Aldrin
 *
 */
@Repository
public interface InstitutionSalespersonLinkRepository extends JpaRepository<InstitutionSalespersonLink, String> {

}
