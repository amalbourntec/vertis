package com.vetris.apimanagement.v1.service;

import com.vetris.apimanagement.v1.dto.request.InstitutionSalespersonLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionSalespersonLink
 * 
 * @author Aldrin
 *
 */
public interface InstitutionSalespersonLinkService {

	public CommonResponseDTO getSalespersonById(String id) throws Exception;

	public CommonResponseDTO getAllSalesperson() throws Exception;

	public CommonResponseDTO addSalesperson(InstitutionSalespersonLinkRequestDTO salespersonRequest) throws Exception;

	public CommonResponseDTO updateSalesperson(InstitutionSalespersonLinkRequestDTO salespersonRequest, String id) throws Exception;

	public CommonResponseDTO deleteSalesperson(String id) throws Exception;
}
