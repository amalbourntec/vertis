package com.vetris.apimanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionDeviceLink;

/**
 * Repository for InstitutionDeviceLink
 * 
 * @author Aldrin
 *
 */
@Repository
public interface InstitutionDeviceLinkRepository extends JpaRepository<InstitutionDeviceLink, String> {

}
