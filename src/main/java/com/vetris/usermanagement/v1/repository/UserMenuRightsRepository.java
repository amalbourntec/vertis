package com.vetris.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.UserMenuRights;

/**
 * @author anandu
 *
 */
@Repository
public interface UserMenuRightsRepository extends JpaRepository<UserMenuRights, String>{

}
