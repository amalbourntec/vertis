package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionCategoryLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for Institution Category Link
 * @author Jini
 *
 */

public interface InstitutionCategoryLinkService {

	public CommonResponseDTO addInstitutionCategoryLink(
			InstitutionCategoryLinkRequestDTO institutionCategoryLinkRequest)throws Exception;

	public CommonResponseDTO getInstitutionCategoryLinkById(Integer categoryId) throws Exception;

	public CommonResponseDTO getAllInstitutionCategoryLink() throws Exception;

	public CommonResponseDTO updateInstitutionCategoryLink(
			InstitutionCategoryLinkRequestDTO institutionCategoryLinkReqDto, Integer categoryId) throws Exception;

	public CommonResponseDTO deleteInstitutionCategoryLink(Integer categoryId)throws Exception ;
	

}
