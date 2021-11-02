package com.vetris.apimanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vetris.entity.SalesPerson;

/**
 * Repository for salesPerson
 * 
 * @author ShekarReddySamreddy
 * 
 */
@Repository
public interface SalesPersonRepository extends JpaRepository<SalesPerson, String> {

}
