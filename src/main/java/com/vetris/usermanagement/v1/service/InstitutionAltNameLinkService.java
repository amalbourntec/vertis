package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionAltNameLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionAltNameLink
 * @author Aldrin
 *
 */ 
public interface InstitutionAltNameLinkService {

	public CommonResponseDTO getInstitutionAltNameById(Integer id) throws Exception;

	public CommonResponseDTO getAllInstitutionAltName() throws Exception;

	public CommonResponseDTO addInstitutionAltName(InstitutionAltNameLinkRequestDTO data) throws Exception;

	public CommonResponseDTO updateInstitutionAltName(InstitutionAltNameLinkRequestDTO data, Integer id) throws Exception;

	public CommonResponseDTO deleteInstitutionAltName(Integer id) throws Exception;
}
