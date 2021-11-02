package com.vetris.apimanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionAltNameLink;

/**
 * Repository for InstitutionAltNameLink
 * 
 * @author Aldrin
 *
 */
@Repository
public interface InstitutionAltNameLinkRepository extends JpaRepository<InstitutionAltNameLink, String> {

}
