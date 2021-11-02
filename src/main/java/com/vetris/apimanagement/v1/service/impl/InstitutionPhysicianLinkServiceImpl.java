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
import com.vetris.apimanagement.v1.dto.request.InstitutionPhysicianLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionPhysicianLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionPhysicianLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionPhysicianLinkService;
import com.vetris.entity.InstitutionPhysicianLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for Institution Physician Link
 * 
 * @author Jini
 *
 */

@Service
public class InstitutionPhysicianLinkServiceImpl implements InstitutionPhysicianLinkService {

	@Autowired
	InstitutionPhysicianLinkRepository institutionPhysicianLinkRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	/**
	 * Get all institution Physician Link
	 */
	@Override
	public CommonResponseDTO getAllInstitutionPhysicianLink() throws Exception {
		List<InstitutionPhysicianLink> institutionPhysicianLinkList = institutionPhysicianLinkRepository.findAll();
		List<InstitutionPhysicianLinkResponseDTO> institutionPhysicianLinkRespDTO = new ArrayList<>();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		if (institutionPhysicianLinkList.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setMessage("No Institution Physician Link found");
		} else {
			institutionPhysicianLinkList.stream().forEach(physician -> institutionPhysicianLinkRespDTO
					.add(objectMapper.convertValue(physician, InstitutionPhysicianLinkResponseDTO.class)));
		}
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionPhysicianLinkRespDTO);
		resultDto.setMessage("Fetched list of Institution Physician Link successfully");
		return resultDto;
	}

	/**
	 * Getting institution Physician Link by physicianId
	 */
	@Override
	public CommonResponseDTO getInstitutionPhysicianLinkById(String physicianId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionPhysicianLink existingInstitutionPhysicianLink = institutionPhysicianLinkRepository
				.findById(physicianId).orElseThrow(() -> new ResourceNotFoundException(
						"Institution Physician Link" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionPhysicianLinkResponseDTO institutionPhysicianLinkResponseDTO = objectMapper
				.convertValue(existingInstitutionPhysicianLink, InstitutionPhysicianLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionPhysicianLinkResponseDTO);
		resultDto.setMessage("Fetched institution Physician Link successfully");

		return resultDto;
	}

	/**
	 * Adding a institution Physician link using request DTO
	 */

	@Override
	public CommonResponseDTO addInstitutionPhysicianLink(
			InstitutionPhysicianLinkRequestDTO institutionPhysicianLinkRequest) throws Exception {

		InstitutionPhysicianLinkResponseDTO institutionPhysicianLinkResponseDTO = new InstitutionPhysicianLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		UUID uuid = UUID.randomUUID();
		InstitutionPhysicianLink institutionPhysician = objectMapper.convertValue(institutionPhysicianLinkRequest,
				InstitutionPhysicianLink.class);
		institutionPhysician.setPhysicianId(uuid.toString());

		institutionPhysician
				.setPhysicianPacsPassword(encodePassword(institutionPhysicianLinkRequest.getPhysicianPacsPassword()));
		institutionPhysician.setCreatedBy(jwtSecurityContextUtil.getId());
		institutionPhysician = institutionPhysicianLinkRepository.save(institutionPhysician);

		BeanUtils.copyProperties(institutionPhysician, institutionPhysicianLinkResponseDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionPhysicianLinkResponseDTO);
		resultDto.setMessage("Saved institution Physician Link successfully");

		return resultDto;
	}

	/**
	 * updating the existing institution Physician link by physicianId
	 */

	@Override
	public CommonResponseDTO updateInstitutionPhysicianLink(
			InstitutionPhysicianLinkRequestDTO institutionPhysicianLinkReqDto, String physicianId) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionPhysicianLink institutionPhysician = institutionPhysicianLinkRepository.findById(physicianId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"InstitutionPhysician" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		try {
			BeanUtils.copyProperties(institutionPhysicianLinkReqDto, institutionPhysician);
			institutionPhysician.setUpdateBy(jwtSecurityContextUtil.getId());
			institutionPhysician.setPhysicianPacsPassword(
					encodePassword(institutionPhysicianLinkReqDto.getPhysicianPacsPassword()));
			institutionPhysician = institutionPhysicianLinkRepository.save(institutionPhysician);
			InstitutionPhysicianLinkResponseDTO institutionPhysicianLinkResponseDTO = objectMapper
					.convertValue(institutionPhysician, InstitutionPhysicianLinkResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(institutionPhysicianLinkResponseDTO);
			resultDto.setMessage("Fetched user successfully");
		} catch (Exception e) {

			throw new Exception(e);
		}
		return resultDto;

	}

	/**
	 * Deleting the institution Physician link by physicianId
	 */

	@Override
	public CommonResponseDTO deleteInstitutionPhysicianLink(String physicianId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionPhysicianLinkRepository.findById(physicianId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionPhysicianLinkRepository.deleteById(physicianId);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Institution Physician Link deleted successfully");

		return resultDto;
	}

	/**
	 * This method used to encode password TODO needs to be changed according
	 * real application
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */

	private String encodePassword(String physicianPacsPassword) throws NoSuchAlgorithmException {
		String encryptedpassword = null;

		MessageDigest m = MessageDigest.getInstance("MD5");

		m.update(physicianPacsPassword.getBytes());

		byte[] bytes = m.digest();

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		encryptedpassword = s.toString();

		return encryptedpassword;
	}

}
