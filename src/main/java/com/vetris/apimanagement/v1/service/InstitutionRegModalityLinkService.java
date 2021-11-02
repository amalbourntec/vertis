package com.vetris.apimanagement.v1.service;

import com.vetris.apimanagement.v1.dto.request.InstitutionRegModalityLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionRegModalityLink
 * 
 * @author Jose Eldhose
 *
 */
public interface InstitutionRegModalityLinkService {

	public CommonResponseDTO addRegModality(InstitutionRegModalityLinkRequestDTO regModalityRequest) throws Throwable;

	public CommonResponseDTO getAllRegModality() throws Throwable;

	public CommonResponseDTO getRegModalityById(String id) throws Throwable;

	public CommonResponseDTO updateInstitutionRegModality(InstitutionRegModalityLinkRequestDTO regModalityRequest,
			String id) throws Throwable;

	public CommonResponseDTO deleteInstitutionRegModality(String id) throws Throwable;

}
