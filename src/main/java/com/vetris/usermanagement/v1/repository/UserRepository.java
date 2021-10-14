package com.vetris.usermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.User;


/**
 * Repository for UserManagement
 * @author Anandu
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
