package com.vetris.mastermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.request.InstitutionsRegRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

/*
 * @author Dhanesh C P
 * Service for InstitutionsReg
 * */
public interface InstitutionsRegService {

	public CommonResponseDTO addInstitutionsReg(InstitutionsRegRequestDTO institutionReg) throws Exception;

	public CommonResponseDTO getAllInstitutionsReg() throws Exception;

	public CommonResponseDTO getInstitutionsRegById(String id) throws Exception;

	public CommonResponseDTO updateInstitutionsReg(InstitutionsRegRequestDTO institutionReg, String id)
			throws Exception;

	public CommonResponseDTO deleteInstitutionsReg(String id) throws Exception;
}
