package com.vetris.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.Institution;

/** Repository for institution
 * @author ShekarReddySamreddy
 * 
 */

@Repository
public interface InstitutionRepository  extends JpaRepository<Institution, String> {

}
