package com.vetris.mastermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.request.InstitutionRegPhysicianLinkRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service class for physician
 * 
 * @author Jose Eldhose
 *
 */
public interface InstitutionRegPhysicianLinkService {

	public CommonResponseDTO getPhysicianById(String id) throws Exception;

	public CommonResponseDTO getAllPhysicians() throws Exception;

	public CommonResponseDTO addPhysician(InstitutionRegPhysicianLinkRequestDTO physicianRequest) throws Exception;

	public CommonResponseDTO updatePhysician(InstitutionRegPhysicianLinkRequestDTO physician, String id)
			throws Exception;

	public CommonResponseDTO deletePhysician(String id) throws Exception;

}
