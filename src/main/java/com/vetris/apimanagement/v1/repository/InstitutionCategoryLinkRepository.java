package com.vetris.apimanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionCategoryLink;

/**
 * Repository for InstitutionCategoryLink
 * 
 * @author Jini
 *
 */

@Repository
public interface InstitutionCategoryLinkRepository extends JpaRepository<InstitutionCategoryLink, Integer> {

}
