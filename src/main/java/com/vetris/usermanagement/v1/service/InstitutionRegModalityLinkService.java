package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionRegModalityLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionRegModalityLink
 * 
 * @author Jose Eldhose
 *
 */
public interface InstitutionRegModalityLinkService {

	public CommonResponseDTO addRegModality(InstitutionRegModalityLinkRequestDTO regModalityRequest) throws Exception;

	public CommonResponseDTO getAllRegModality() throws Exception;

	public CommonResponseDTO getRegModalityById(Integer id) throws Exception;

	public CommonResponseDTO updateInstitutionRegModality(InstitutionRegModalityLinkRequestDTO regModalityRequest,
			Integer id) throws Exception;

	public CommonResponseDTO deleteInstitutionRegModality(Integer id) throws Exception;

}
