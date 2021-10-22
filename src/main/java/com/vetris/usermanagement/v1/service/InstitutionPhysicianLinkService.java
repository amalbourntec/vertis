package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionPhysicianLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
* Service for Institution Physician Link
* @author Jini
*
*/

public interface InstitutionPhysicianLinkService {

	public CommonResponseDTO getAllInstitutionPhysicianLink()throws Exception;

	public CommonResponseDTO getInstitutionPhysicianLinkById(String physicianId)throws Exception;

	public CommonResponseDTO addInstitutionPhysicianLink(
			InstitutionPhysicianLinkRequestDTO institutionPhysicianLinkRequest) throws Exception;

	public CommonResponseDTO updateInstitutionPhysicianLink(
			InstitutionPhysicianLinkRequestDTO institutionPhysicianLinkReqDto, String physicianId)throws Exception;

	public CommonResponseDTO deleteInstitutionPhysicianLink(String physicianId) throws Exception;

}
