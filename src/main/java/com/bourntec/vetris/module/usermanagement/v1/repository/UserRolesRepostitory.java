package com.bourntec.vetris.module.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bourntec.vetris.entity.UserRoles;

/**
 * Repository for UserRoles
 * @author Jose Eldhose
 *
 */
@Repository
public interface UserRolesRepostitory  extends JpaRepository<UserRoles ,Integer> {

}
