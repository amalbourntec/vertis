package com.vetris.apimanagement.v1.service;

import com.vetris.apimanagement.v1.dto.request.InstitutionUserLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

/*
 * @author Dhanesh C P
 * Service for InstitutionUserLink
 * */
public interface InstitutionUserLinkService {

	public CommonResponseDTO findAllInstitutionUserLink() throws Exception;

	public CommonResponseDTO findByUserId(String userId) throws Exception;

	public CommonResponseDTO findByInstitutionId(String institutionId) throws Exception;

	public CommonResponseDTO getByInstitutionIdORUserId(String id) throws Exception;

	public CommonResponseDTO saveInstitutionUserLinks(InstitutionUserLinkRequestDTO requestDto) throws Exception;

	public CommonResponseDTO deleteInstitutionUserLink(String institutionId, String userId) throws Exception;;

	public CommonResponseDTO updateInstitutionUserLink(InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO,
			String institutionId, String userId) throws Exception;

	public CommonResponseDTO fetchInstitutionUserLinkByAll(String institutionId, String userId) throws Exception;
}
