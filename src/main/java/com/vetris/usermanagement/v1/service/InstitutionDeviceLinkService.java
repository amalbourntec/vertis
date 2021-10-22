package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionDeviceLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionDeviceLink
 * 
 * @author Aldrin
 *
 */

public interface InstitutionDeviceLinkService {

	public CommonResponseDTO getDeviceById(String id) throws Exception;

	public CommonResponseDTO getAllDevice() throws Exception;

	public CommonResponseDTO addDevice(InstitutionDeviceLinkRequestDTO data) throws Exception;

	public CommonResponseDTO updateDevice(InstitutionDeviceLinkRequestDTO data, String id) throws Exception;

	public CommonResponseDTO deleteDevice(String id) throws Exception;

}
