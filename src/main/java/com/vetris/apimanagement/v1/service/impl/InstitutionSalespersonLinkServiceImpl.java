package com.vetris.apimanagement.v1.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionSalespersonLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionSalespersonLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionSalespersonLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionSalespersonLinkService;
import com.vetris.entity.InstitutionSalespersonLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionSalespersonLink
 * 
 * @author Aldrin
 *
 */
@Service
public class InstitutionSalespersonLinkServiceImpl implements InstitutionSalespersonLinkService{

	@Autowired
	private InstitutionSalespersonLinkRepository institutionSalespersonLinkRepository;
	
	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public CommonResponseDTO getSalespersonById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionSalespersonLink existingSalesperson = institutionSalespersonLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionSalespersonLinkResponseDTO salespersonRespDTO = objectMapper.convertValue(existingSalesperson,
				InstitutionSalespersonLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(salespersonRespDTO);
		resultDto.setMessage("Fetched salesperson successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO getAllSalesperson() throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionSalespersonLink> salespersonList = institutionSalespersonLinkRepository.findAll();
		List<InstitutionSalespersonLinkResponseDTO> salespersonRespDTO = new ArrayList<>();
		if (salespersonList.isEmpty()) {
			 throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			salespersonList.stream().forEach(salesperson -> {
				salespersonRespDTO.add(objectMapper.convertValue(salesperson, InstitutionSalespersonLinkResponseDTO.class));
			});
		}
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(salespersonRespDTO);
		resultDto.setMessage("Fetched list of Salesperson successfully");
		return resultDto;
	}

	@Override
	public CommonResponseDTO addSalesperson(InstitutionSalespersonLinkRequestDTO salespersonRequest) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionSalespersonLink salesperson = objectMapper.convertValue(salespersonRequest, InstitutionSalespersonLink.class);
		salesperson.setSalespersonPacsPassword(encodePassword(salespersonRequest.getSalespersonPacsPassword()));
		salesperson.setCreatedBy(jwtSecurityContextUtil.getId());
		salesperson = institutionSalespersonLinkRepository.save(salesperson);

		InstitutionSalespersonLinkResponseDTO salespersonRespDTO = objectMapper.convertValue(salesperson,
				InstitutionSalespersonLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(salespersonRespDTO);
		resultDto.setMessage("Salesperson added successfully");
		return resultDto;
	}

	@Override
	public CommonResponseDTO updateSalesperson(InstitutionSalespersonLinkRequestDTO salespersonRequest, String id) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionSalespersonLink salesperson = institutionSalespersonLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		BeanUtils.copyProperties(salespersonRequest, salesperson);
		salesperson.setSalespersonPacsPassword(salespersonRequest.getSalespersonPacsPassword());
		salesperson.setUpdateBy(jwtSecurityContextUtil.getId());
		salesperson = institutionSalespersonLinkRepository.save(salesperson);

		InstitutionSalespersonLinkResponseDTO salespersonRespDTO = objectMapper.convertValue(salesperson,
				InstitutionSalespersonLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(salespersonRespDTO);
		resultDto.setMessage("Salesperson updated successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO deleteSalesperson(String id) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionSalespersonLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionSalespersonLinkRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Salesperson deleated successfully");

		return resultDto;
	}
	
	/**
	 * This method used to encode password TODO needs to be changed according real
	 * application
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String encodePassword(String password) throws NoSuchAlgorithmException {
		String encryptedpassword = null;

		MessageDigest m = MessageDigest.getInstance("MD5");

		m.update(password.getBytes());

		byte[] bytes = m.digest();

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		encryptedpassword = s.toString();

		return encryptedpassword;
	}

}
