package com.vetris.mastermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.request.InstitutionDisputeDicomTagsRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

public interface InstitutionDisputeDicomTagsService {
	public CommonResponseDTO addInstitutionDisputeDicomTags(InstitutionDisputeDicomTagsRequestDTO institution)
			throws Exception;

	public CommonResponseDTO getAllInstitutionDisputeDicomTags() throws Exception;

	public CommonResponseDTO getInstitutionDisputeDicomTagsByInstitutionId(String institutionId) throws Exception;

	public CommonResponseDTO getInstitutionDisputeDicomTagsByGroupId(String groupId) throws Exception;

	public CommonResponseDTO getInstitutionDisputeDicomTagsByElementId(String elementId) throws Exception;

	public CommonResponseDTO getByInstitutionIdORGroupIdORElementId(String id) throws Exception;

	public CommonResponseDTO updateInstitutionDisputeDicomTags(
			InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest, String institutionId,
			String groupId, String elementId) throws Exception;

	public CommonResponseDTO fetchInstitutionDisputeDicomTagsByAll(String institutionId, String groupId,
			String elementId)throws Exception;

	public CommonResponseDTO deleteInstitutionDisputeDicomTags(String institutionId, String groupId, String elementId) throws Exception;

}
