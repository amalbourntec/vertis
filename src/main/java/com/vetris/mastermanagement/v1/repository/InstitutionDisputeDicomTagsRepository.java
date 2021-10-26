package com.vetris.mastermanagement.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vetris.entity.InstitutionDisputeDicomTags;
/**
 * Repository for InstitutionDisputeDicomTags
 * @author Dhanesh
 *
 */
@Repository
public interface InstitutionDisputeDicomTagsRepository extends JpaRepository<InstitutionDisputeDicomTags, String>{

	//@Query("SELECT i FROM institution_dispute__dicom_tags i WHERE i.column in('institutionId','groupId','elementId')")
	//@Query("SELECT i FROM institution_dispute__dicom_tags i WHERE i.column='institutionId' or i.column='groupId' or i.column='elementId'")
	
	/*Query for getting values based on three PK values
	 * @Query(value =
	 * "select * from institution_dispute__dicom_tags where institution_id=?1 or  group_id=?1 or element_id=?1"
	 * , nativeQuery=true) List<InstitutionDisputeDicomTags>
	 * findByInstitutionIdOrGroupIdOrElementId(String id);
	 */
	
	//@Query(value = "select * from institution_dispute__dicom_tags where institution_id=?1", nativeQuery=true)
	Optional<InstitutionDisputeDicomTags> findByInstitutionId(String institutionId);
	
	@Query(value = "select * from institution_dispute__dicom_tags where group_id=?1", nativeQuery=true)
	Optional<InstitutionDisputeDicomTags> findByGroupId(String groupId);
	
	@Query(value = "select * from institution_dispute__dicom_tags where element_id=?1", nativeQuery=true)
	Optional<InstitutionDisputeDicomTags> findByElementId(String elementId);
	
	
	/*
	 * Optional<InstitutionDisputeDicomTags> deleteByInstitutionId(String
	 * institutionId);
	 */
	
}
