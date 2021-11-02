package com.vetris.apimanagement.v1.service;

import com.vetris.apimanagement.v1.dto.request.InstitutionRegPhysicianLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service class for physician
 * 
 * @author Jose Eldhose
 *
 */
public interface InstitutionRegPhysicianLinkService {

	public CommonResponseDTO getPhysicianById(String id) throws Throwable;

	public CommonResponseDTO getAllPhysicians() throws Throwable;

	public CommonResponseDTO addPhysician(InstitutionRegPhysicianLinkRequestDTO physicianRequest) throws Throwable;

	public CommonResponseDTO updatePhysician(InstitutionRegPhysicianLinkRequestDTO physician, String id)
			throws Throwable;

	public CommonResponseDTO deletePhysician(String id) throws Throwable;

}
