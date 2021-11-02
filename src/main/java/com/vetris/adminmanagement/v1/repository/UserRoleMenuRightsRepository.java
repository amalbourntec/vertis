package com.vetris.adminmanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.UserRoleMenuRights;

/**
 * @author anandu
 * user role menu rights repository
 * 
 */
@Repository
public interface UserRoleMenuRightsRepository extends JpaRepository<UserRoleMenuRights, Integer> {

}
