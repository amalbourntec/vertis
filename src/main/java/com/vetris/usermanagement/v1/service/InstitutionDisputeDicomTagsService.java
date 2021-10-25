package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionDisputeDicomTagsRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

public interface InstitutionDisputeDicomTagsService {
	public CommonResponseDTO addInstitutionDisputeDicomTags(InstitutionDisputeDicomTagsRequestDTO institution)
			throws Exception;

	public CommonResponseDTO getAllInstitutionDisputeDicomTags() throws Exception;

	public CommonResponseDTO getInstitutionDisputeDicomTagsByInstitutionId(String institutionId) throws Exception;
	
	public CommonResponseDTO getInstitutionDisputeDicomTagsByGroupId(String groupId)throws Exception;
	
	public CommonResponseDTO getInstitutionDisputeDicomTagsByElementId(String elementId)throws Exception;
	
	/*general get method for three PK values
	 * public List<CommonResponseDTO>
	 * getInstitutionDisputeDicomTagsByInstitutionIdOrGroupIdOrElementId(String
	 * institutionOrGroupOrElementId) throws Exception;
	 */
	public CommonResponseDTO updateInstitutionDisputeDicomTagsByInstitutionId(InstitutionDisputeDicomTagsRequestDTO institution,
			String institutionId) throws Exception;

	/*
	 * public CommonResponseDTO deleteInstitutionDisputeDicomTags(String id) throws
	 * Exception;
	 */

	public CommonResponseDTO updateInstitutionDisputeDicomTagsByGroupId(InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest, String groupId)throws Exception;

	public CommonResponseDTO updateInstitutionDisputeDicomTagsByElementId(InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest, String elementId)throws Exception;

	/*
	 * public CommonResponseDTO
	 * deleteInstitutionDisputeDicomTagsByInstitutionId(String institutionId)throws
	 * Exception;
	 */
}
