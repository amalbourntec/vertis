package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * institution service
 * 
 * @author ShekarReddySamreddy
 * 
 */

public interface InstitutionService {

	public CommonResponseDTO addInstitution(InstitutionRequestDTO institution) throws Exception;

	public CommonResponseDTO getAllInstitutions() throws Exception;

	public CommonResponseDTO getInstitutionById(String id) throws Exception;

	public CommonResponseDTO updateInstitution(InstitutionRequestDTO institution, String id) throws Exception;

	public CommonResponseDTO deleteInstitution(String id) throws Exception;

}
