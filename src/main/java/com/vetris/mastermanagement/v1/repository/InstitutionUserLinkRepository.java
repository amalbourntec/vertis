package com.vetris.mastermanagement.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionDisputeDicomTags;
import com.vetris.entity.InstitutionUserLink;

@Repository
public interface InstitutionUserLinkRepository extends JpaRepository<InstitutionUserLink,String>{

	@Query(value = "select * from institution_user_link where institution_id=?1", nativeQuery=true)
	Optional<InstitutionUserLink> findByInstitutionId(String institutionId);
	
	@Query(value = "select * from institution_user_link where user_id=?1", nativeQuery=true)
	Optional<InstitutionUserLink> findByUserId(String userId);
}
