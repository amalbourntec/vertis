package com.vetris.apimanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.Physicians;

/**
 * @author anandu
 *
 */
@Repository
public interface PhysicianRepository extends JpaRepository<Physicians, String>{

}
