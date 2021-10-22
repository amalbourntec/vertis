package com.vetris.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionRegModalityLink;

/**
 * Repository for InstitutionRegModalityLink
 * 
 * @author Jose Eldhose
 *
 */
@Repository
public interface InstitutionRegModalityLinkRepository extends JpaRepository<InstitutionRegModalityLink, String> {

}
