package com.vetris.mastermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetris.entity.InstitutionRegPhysicianLink;

/**
 * Repository for physician
 * @author Jose Eldhose
 *
 */
public interface InstitutionRegPhysicianLinkRepository extends JpaRepository<InstitutionRegPhysicianLink, String> {

}
