package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionRatesFeeScheduleRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionRatesFeeScheduleResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionRatesFeeScheduleRepository;
import com.vetris.apimanagement.v1.service.InstitutionRatesFeeScheduleService;
import com.vetris.entity.InstitutionRatesFeeSchedule;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionRatesFeeSchedule
 * 
 * @author Aldrin
 *
 */
@Service
public class InstitutionRatesFeeScheduleServiceImpl implements InstitutionRatesFeeScheduleService {

	@Autowired
	private InstitutionRatesFeeScheduleRepository institutionRatesFeeScheduleRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Getting InstitutionRatesFeeSchedule by id
	 */
	@Override
	public CommonResponseDTO getRateFeeById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionRatesFeeSchedule existingRateFee = institutionRatesFeeScheduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionRatesFeeScheduleResponseDTO rateFeeRespDTO = objectMapper.convertValue(existingRateFee,
				InstitutionRatesFeeScheduleResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(rateFeeRespDTO);
		resultDto.setMessage("Fetched Institution rate fee successfully");

		return resultDto;
	}

	/**
	 * Getting all InstitutionRatesFeeSchedule
	 */
	@Override
	public CommonResponseDTO getAllRateFee() throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionRatesFeeSchedule> rateFeeList = institutionRatesFeeScheduleRepository.findAll();
		List<InstitutionRatesFeeScheduleResponseDTO> rateFeeRespDTO = new ArrayList<>();
		if (rateFeeList.isEmpty()) {
			 throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			rateFeeList.stream().forEach(rateFee -> {
				rateFeeRespDTO.add(objectMapper.convertValue(rateFee, InstitutionRatesFeeScheduleResponseDTO.class));
			});
		}
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(rateFeeRespDTO);
		resultDto.setMessage("Fetched list of Institution rate fee successfully");
		return resultDto;
	}

	/**
	 * Adding a InstitutionRatesFeeSchedule using request DTO
	 */
	@Override
	public CommonResponseDTO addRateFee(InstitutionRatesFeeScheduleRequestDTO ratesFeeRequest) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		UUID uuid = UUID.randomUUID();
		InstitutionRatesFeeSchedule rateFee = objectMapper.convertValue(ratesFeeRequest,
				InstitutionRatesFeeSchedule.class);
		rateFee.setRateId(uuid.toString());
		rateFee.setCreatedBy(jwtSecurityContextUtil.getId());
		rateFee = institutionRatesFeeScheduleRepository.save(rateFee);

		InstitutionRatesFeeScheduleResponseDTO rateFeeRespDTO = objectMapper.convertValue(rateFee,
				InstitutionRatesFeeScheduleResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(rateFeeRespDTO);
		resultDto.setMessage("InstitutionRatesFeeSchedule added successfully");
		return resultDto;
	}

	/**
	 * updating the existing InstitutionRatesFeeSchedule
	 */
	@Override
	public CommonResponseDTO updateRateFee(InstitutionRatesFeeScheduleRequestDTO ratesFeeRequest, String id)
			throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionRatesFeeSchedule rateFee = institutionRatesFeeScheduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		BeanUtils.copyProperties(ratesFeeRequest, rateFee);
		rateFee.setUpdateBy(jwtSecurityContextUtil.getId());
		rateFee = institutionRatesFeeScheduleRepository.save(rateFee);

		InstitutionRatesFeeScheduleResponseDTO rateFeeRespDTO = objectMapper.convertValue(rateFee,
				InstitutionRatesFeeScheduleResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(rateFeeRespDTO);
		resultDto.setMessage("InstitutionRatesFeeSchedule updated successfully");

		return resultDto;
	}

	/**
	 * Deleting the InstitutionRatesFeeSchedule by rate id
	 */
	@Override
	public CommonResponseDTO deleteRateFee(String id) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionRatesFeeScheduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionRatesFeeScheduleRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("InstitutionRatesFeeSchedule deleted successfully");

		return resultDto;
	}

}
