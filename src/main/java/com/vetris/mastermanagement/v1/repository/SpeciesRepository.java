package com.vetris.mastermanagement.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vetris.entity.Species;

@Repository
public interface SpeciesRepository extends JpaRepository<Species,Integer> {

}
