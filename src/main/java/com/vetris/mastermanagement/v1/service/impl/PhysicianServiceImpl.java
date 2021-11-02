package com.vetris.mastermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.Physicians;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.mastermanagement.v1.dto.request.PhysicianRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.mastermanagement.v1.dto.response.PhysicianResponseDTO;
import com.vetris.mastermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.mastermanagement.v1.repository.PhysicianRepository;
import com.vetris.mastermanagement.v1.service.PhysicianService;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * @author anandu
 *
 */
@Service
public class PhysicianServiceImpl implements PhysicianService {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	PhysicianRepository physicianRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	/**
	 * method to add new physician in table
	 */
	@Override
	public CommonResponseDTO addPhysician(PhysicianRequestDTO physicianDto) throws Exception {

		PhysicianResponseDTO physicianResponseDTO = new PhysicianResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		UUID uuid = UUID.randomUUID();
		Physicians resultPhysician = objectMapper.convertValue(physicianDto, Physicians.class);
		resultPhysician.setId(uuid.toString());
		resultPhysician.setCreatedBy(jwtSecurityContextUtil.getId());
		resultPhysician = physicianRepository.save(resultPhysician);
		BeanUtils.copyProperties(resultPhysician, physicianResponseDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(physicianResponseDTO);
		resultDto.setMessage("Saved physician successfully");

		return resultDto;
	}

	/**
	 * method to fetch all physicians from table
	 */
	@Override
	public CommonResponseDTO getAllPhysician() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<Physicians> physicians = physicianRepository.findAll();
		List<PhysicianResponseDTO> resultresponseDto = new ArrayList<>();
		if (physicians.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No Physician found");
		} else {
			physicians.stream().forEach(existingPhysician -> {
				resultresponseDto.add(objectMapper.convertValue(existingPhysician, PhysicianResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched physicians successfully");
		}

		return resultDto;
	}

	/**
	 * method to fetch all physicians from table
	 */
	@Override
	public CommonResponseDTO getPhysicianById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Physicians existingPhysician = physicianRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physician" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		PhysicianResponseDTO physicianRespDTO = objectMapper.convertValue(existingPhysician,
				PhysicianResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(physicianRespDTO);
		resultDto.setMessage("Fetched physician successfully");
		return resultDto;
	}

	/**
	 * method to update a physicians from table
	 */
	@Override
	public CommonResponseDTO updatePhysician(PhysicianRequestDTO physicianDto, String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Physicians resultPhysician = physicianRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physician" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		BeanUtils.copyProperties(physicianDto, resultPhysician);
		resultPhysician.setUpdateBy(jwtSecurityContextUtil.getId());
		resultPhysician = physicianRepository.save(resultPhysician);
		PhysicianResponseDTO physicianRespDTO = objectMapper.convertValue(resultPhysician, PhysicianResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(physicianRespDTO);
		resultDto.setMessage("Updated Physician successfully");

		return resultDto;
	}

	/**
	 * method to delete a physicians from table
	 */
	@Override
	public CommonResponseDTO deletePhysician(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		physicianRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physician" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		physicianRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted physician successfully");
		return resultDto;
	}

}
