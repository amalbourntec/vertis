package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionsRegRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
/*
 * Author : Dhanesh
 * */
public interface InstitutionsRegService {

	public CommonResponseDTO addInstitutionsReg(InstitutionsRegRequestDTO institutionReg) throws Exception;

	public CommonResponseDTO getAllInstitutionsReg() throws Exception;

	public CommonResponseDTO getInstitutionsRegById(String id) throws Exception;

	public CommonResponseDTO updateInstitutionsReg(InstitutionsRegRequestDTO institutionReg, String id)
			throws Exception;

	public CommonResponseDTO deleteInstitutionsReg(String id) throws Exception;
}
