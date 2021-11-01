package com.vetris.mastermanagement.v1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionDisputeDicomTags;

/**
 * Repository for InstitutionDisputeDicomTags
 * 
 * @author Dhanesh
 */
@Repository
public interface InstitutionDisputeDicomTagsRepository extends JpaRepository<InstitutionDisputeDicomTags, String> {

	@Query(value = "select * from institution_dispute__dicom_tags where institution_id=?1", nativeQuery = true)
	List<InstitutionDisputeDicomTags> findAllInstitutionId(String institutionId);

	@Query(value = "select * from institution_dispute__dicom_tags where group_id=?1", nativeQuery = true)
	List<InstitutionDisputeDicomTags> findAllGroupId(String groupId);

	@Query(value = "select * from institution_dispute__dicom_tags where element_id=?1", nativeQuery = true)
	List<InstitutionDisputeDicomTags> findAllElementId(String elementId);

	/* for fetching values in a general way */
	@Query(value = "select * from institution_dispute__dicom_tags where institution_id=?1 or  group_id=?1 or element_id=?1", nativeQuery = true)
	List<InstitutionDisputeDicomTags> findByInstitutionIdORGroupIdORElementId(String Id);

	/* for updating values in a general way, used this find method */
	@Query(value = "select * from institution_dispute__dicom_tags where institution_id=?1 and  group_id=?2 and element_id=?3", nativeQuery = true)
	Optional<InstitutionDisputeDicomTags> findByInstitutionIdANDGroupIdANDElementId(String institutionId,
			String groupId, String elementId);

	Optional<InstitutionDisputeDicomTags> findByGroupId(String groupId);

	Optional<InstitutionDisputeDicomTags> findByInstitutionId(String institutionId);

	Optional<InstitutionDisputeDicomTags> findByElementId(String elementId);

}
