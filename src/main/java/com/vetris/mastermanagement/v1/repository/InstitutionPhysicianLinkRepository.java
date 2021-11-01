package com.vetris.mastermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionPhysicianLink;

/**
 * Repository for InstitutionPhysicianLink
 * 
 * @author Jini
 *
 */

@Repository
public interface InstitutionPhysicianLinkRepository extends JpaRepository<InstitutionPhysicianLink, String> {

}
