package com.vetris.usermanagement.v1.service;

import com.vetris.usermanagement.v1.dto.request.InstitutionRatesFeeScheduleRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionRatesFeeSchedule
 * 
 * @author Aldrin
 *
 */

public interface InstitutionRatesFeeScheduleService {

	public CommonResponseDTO getRateFeeById(String id) throws Exception;

	public CommonResponseDTO getAllRateFee() throws Exception;

	public CommonResponseDTO addRateFee(InstitutionRatesFeeScheduleRequestDTO ratesFeeRequest) throws Exception;

	public CommonResponseDTO updateRateFee(InstitutionRatesFeeScheduleRequestDTO ratesFeeRequest, String id)
			throws Exception;

	public CommonResponseDTO deleteRateFee(String id) throws Exception;
}
