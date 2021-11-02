package com.vetris.apimanagement.v1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionUserLink;

/**
 * Repository for InstitutionUserLink
 * 
 * @author Dhanesh
 */
@Repository
public interface InstitutionUserLinkRepository extends JpaRepository<InstitutionUserLink, String> {

	@Query(value = "select * from institution_user_link where institution_id=?1", nativeQuery = true)
	Optional<InstitutionUserLink> findByInstitutionId(String institutionId);

	@Query(value = "select * from institution_user_link where user_id=?1", nativeQuery = true)
	Optional<InstitutionUserLink> findByUserId(String userId);

	@Query(value = "select * from institution_user_link where institution_id=?1", nativeQuery = true)
	List<InstitutionUserLink> findAllInstitutionId(String institutionId);

	@Query(value = "select * from institution_user_link where user_id=?1", nativeQuery = true)
	List<InstitutionUserLink> findAllUserId(String userId);

	/* for fetching values in a general way */
	@Query(value = "select * from institution_user_link where institution_id=?1 or  user_id=?1", nativeQuery = true)
	List<InstitutionUserLink> findByInstitutionIdORUserId(String Id);

	/* for updating values in a general way, used this find method */
	@Query(value = "select * from institution_user_link where institution_id=?1 and  user_id=?2 ", nativeQuery = true)
	Optional<InstitutionUserLink> findByInstitutionIdANDUserId(String institutionId, String userId);
}
