package com.vetris.apimanagement.v1.service;

import com.vetris.apimanagement.v1.dto.request.InstitutionAltNameLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionAltNameLink
 * 
 * @author Aldrin
 *
 */
public interface InstitutionAltNameLinkService {

	public CommonResponseDTO getInstitutionAltNameById(String id) throws Exception;

	public CommonResponseDTO getAllInstitutionAltName() throws Exception;

	public CommonResponseDTO addInstitutionAltName(InstitutionAltNameLinkRequestDTO data) throws Exception;

	public CommonResponseDTO updateInstitutionAltName(InstitutionAltNameLinkRequestDTO data, String id)
			throws Exception;

	public CommonResponseDTO deleteInstitutionAltName(String id) throws Exception;
}
