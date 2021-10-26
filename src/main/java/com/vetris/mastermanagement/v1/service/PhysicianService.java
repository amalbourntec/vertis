package com.vetris.mastermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.request.PhysicianRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

/**
 * @author anandu
 *
 */
public interface PhysicianService {
	
	public CommonResponseDTO addPhysician(PhysicianRequestDTO physician) throws Exception;

	public CommonResponseDTO getAllPhysician() throws Exception;

	public CommonResponseDTO getPhysicianById(String id) throws Exception;

	public CommonResponseDTO updatePhysician(PhysicianRequestDTO physician, String id) throws Exception;

	public CommonResponseDTO deletePhysician(String id) throws Exception;

}
