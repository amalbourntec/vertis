package com.vetris.mastermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.request.InstitutionUserLinkRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

public interface InstitutionUserLinkService {

	public CommonResponseDTO findAllInstitutionUserLink() throws Exception;
	
	public CommonResponseDTO findByUserId(String userId) throws Exception;
	
	public CommonResponseDTO findByInstitutionId(String institutionId) throws Exception;

	public CommonResponseDTO saveInstitutionUserLinks(InstitutionUserLinkRequestDTO requestDto) throws Exception;

	public CommonResponseDTO updateInstitutionUserLinkByUserId(InstitutionUserLinkRequestDTO requestDto, String userId) throws Exception;
	
	public CommonResponseDTO updateInstitutionUserLinkByInstitutionId(InstitutionUserLinkRequestDTO requestDto, String institutionId) throws Exception;

	public CommonResponseDTO deleteInstitutionUserLinkByUserId(String userId) throws Exception;
}
