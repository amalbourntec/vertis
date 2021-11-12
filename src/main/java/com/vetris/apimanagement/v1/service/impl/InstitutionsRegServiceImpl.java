package com.vetris.apimanagement.v1.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionsRegRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionsRegResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionsRegRepository;
import com.vetris.apimanagement.v1.service.InstitutionsRegService;
import com.vetris.entity.InstitutionsReg;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionsReg
 * 
 * @author Dhanesh
 *
 */
@Service
public class InstitutionsRegServiceImpl implements InstitutionsRegService {

	@Autowired
	private InstitutionsRegRepository institutionsRegRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public CommonResponseDTO addInstitutionsReg(InstitutionsRegRequestDTO institutionRegRequest) throws Exception {
		InstitutionsRegResponseDTO institutionsRegResponseDTO = new InstitutionsRegResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UUID uuid = UUID.randomUUID();

		InstitutionsReg institutionsReg = objectMapper.convertValue(institutionRegRequest, InstitutionsReg.class);
		institutionsReg.setId(uuid.toString());
		institutionsReg.setLoginPassword(encodePassword(institutionRegRequest.getLoginPassword()));
		institutionsReg = institutionsRegRepository.save(institutionsReg);
		BeanUtils.copyProperties(institutionsReg, institutionsRegResponseDTO);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionsRegResponseDTO);
		resultDto.setMessage("Saved successfully");
		return resultDto;
	}

	@Override
	public CommonResponseDTO getAllInstitutionsReg() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionsReg> institutionsReg = institutionsRegRepository.findAll();
		List<InstitutionsRegResponseDTO> resultresponseDto = new ArrayList<>();
		if (institutionsReg.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			institutionsReg.stream().forEach(institutionsRegItem -> resultresponseDto
					.add(objectMapper.convertValue(institutionsRegItem, InstitutionsRegResponseDTO.class)));

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(resultresponseDto);
			resultDto.setMessage("Fetched successfully");
		}

		return resultDto;
	}

	@Override
	public CommonResponseDTO getInstitutionsRegById(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionsRegResponseDTO institutionsRegResponseDTO = new InstitutionsRegResponseDTO();
		InstitutionsReg existingInstitutionsReg = institutionsRegRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionsRegResponseDTO = objectMapper.convertValue(existingInstitutionsReg,
				InstitutionsRegResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionsRegResponseDTO);
		resultDto.setMessage("Fetched successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO updateInstitutionsReg(InstitutionsRegRequestDTO institutionReg, String id)
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionsRegRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		
			InstitutionsReg resultInstitutionsReg = objectMapper.convertValue(institutionReg, InstitutionsReg.class);
			resultInstitutionsReg.setId(id);
			resultInstitutionsReg.setUpdateBy(jwtSecurityContextUtil.getId());
			institutionsRegRepository.save(resultInstitutionsReg);
			InstitutionsRegResponseDTO institutionsRegResponseDTO = objectMapper.convertValue(resultInstitutionsReg,
					InstitutionsRegResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionsRegResponseDTO);
			resultDto.setMessage("Fetched successfully");
	

		return resultDto;
	}

	@Override
	public CommonResponseDTO deleteInstitutionsReg(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		Optional<InstitutionsReg> existingInstitutionsReg = institutionsRegRepository.findById(id);
		if (existingInstitutionsReg.isPresent()) {
			institutionsRegRepository.deleteById(id);
			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setMessage("Deleted successfully");
		} else {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		}
		return resultDto;
	}

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
