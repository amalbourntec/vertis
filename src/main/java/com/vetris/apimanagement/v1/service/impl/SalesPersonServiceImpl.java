package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.SalesPersonRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.SalesPersonResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.SalesPersonRepository;
import com.vetris.apimanagement.v1.service.SalesPersonService;
import com.vetris.entity.SalesPerson;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * ServiceImpl for salesPerson
 * 
 * @author ShekarReddySamreddy
 * 
 */
@Service
public class SalesPersonServiceImpl implements SalesPersonService {
	@Autowired
	SalesPersonRepository salespersonRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public CommonResponseDTO getAllSalesPerson() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<SalesPerson> salesperson = salespersonRepository.findAll();
		List<SalesPersonResponseDTO> resultresponseDto = new ArrayList<>();
		if (salesperson.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No salesperson found");
		} else {
			salesperson.stream().forEach(existingSalesPerson -> {
				resultresponseDto.add(objectMapper.convertValue(existingSalesPerson, SalesPersonResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched salesperson successfully");
		}
		return resultDto;
	}

	@Override
	public CommonResponseDTO saveSalesPerson(SalesPersonRequestDTO requestDto) throws Exception {
		SalesPersonResponseDTO salespersonRespDTO = new SalesPersonResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		
		UUID uuid = UUID.randomUUID();
		SalesPerson resultSalesPerson = objectMapper.convertValue(requestDto, SalesPerson.class);
		if(!(resultSalesPerson.getIsActive().equalsIgnoreCase("y")|| resultSalesPerson.getIsActive().equalsIgnoreCase("n"))) {
			throw new DataIntegrityViolationException("isActive must be Y or N");
		}
		resultSalesPerson.setId(uuid.toString());
		resultSalesPerson.setCreatedBy(jwtSecurityContextUtil.getId());
		System.out.println(resultSalesPerson.getAddress1());
		System.out.println(resultSalesPerson.getFName());
		resultSalesPerson = salespersonRepository.save(resultSalesPerson);
		BeanUtils.copyProperties(resultSalesPerson, salespersonRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(salespersonRespDTO);
		resultDto.setMessage("Saved salesperson successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO updateSalesPerson(SalesPersonRequestDTO requestDto, String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		salespersonRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("SalesPerson " + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		try {
			SalesPerson resultSalesPerson = objectMapper.convertValue(requestDto, SalesPerson.class);
			resultSalesPerson.setId(id);
			resultSalesPerson.setUpdateBy(jwtSecurityContextUtil.getId());
			resultSalesPerson = salespersonRepository.save(resultSalesPerson);
			SalesPersonResponseDTO salespersonRespDTO = objectMapper.convertValue(resultSalesPerson,
					SalesPersonResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(salespersonRespDTO);
			resultDto.setMessage("Fetched SalesPerson successfully");
		} catch (Exception e) {
			throw new Exception(e);
		}
		return resultDto;
	}

	@Override
	public CommonResponseDTO deleteSalesPerson(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		salespersonRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("SalesPerson" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		salespersonRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted salesperson successfully");
		return resultDto;
	}

	@Override
	public CommonResponseDTO getSalesPersonById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		SalesPerson existingSalesPerson = salespersonRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("SalesPerson" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		SalesPersonResponseDTO salespersonRespDTO = objectMapper.convertValue(existingSalesPerson,
				SalesPersonResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(salespersonRespDTO);
		resultDto.setMessage("Fetched SalesPerson successfully");
		return resultDto;
	}
}