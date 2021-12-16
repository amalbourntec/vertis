package com.vetris.apimanagement.v1.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionUserLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionUserLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionUserLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionUserLinkService;
import com.vetris.entity.InstitutionUserLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionUserLink
 * 
 * @author Dhanesh
 *
 */
@Service
public class InstitutionUserLinkServiceImpl implements InstitutionUserLinkService {

	@Autowired
	InstitutionUserLinkRepository institutionUserLinkRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	JWTSecurityContextUtil jwtSecurityContextUtil;

	@Override
	public CommonResponseDTO findAllInstitutionUserLink() throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLink> institutionUserLink = institutionUserLinkRepository.findAll();
		List<InstitutionUserLinkResponseDTO> responseDTO = new ArrayList<>();
		if (institutionUserLink.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			institutionUserLink.stream().forEach(institutionUserLinkItem -> responseDTO
					.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class)));

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(responseDTO);
			resultDto.setMessage("Fetched successfully");
		}
		return resultDto;
	}

	@Override
	public CommonResponseDTO findByUserId(String userId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLink> existingInstitutionUserLink = institutionUserLinkRepository.findByUserId(userId);
		if(!(Collections.EMPTY_LIST != null)) {
			throw new ResourceNotFoundException("not found");
		}
		List<InstitutionUserLinkResponseDTO> institutionUserLinkResponseDTO  = new ArrayList<InstitutionUserLinkResponseDTO>();
		existingInstitutionUserLink.stream().forEach(institutionUserLinkItem -> institutionUserLinkResponseDTO
				.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class)));

//		 = objectMapper
//				.convertValue(existingInstitutionUserLink, InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Fetched successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO findByInstitutionId(String institutionId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLink> existingInstitutionUserLink = institutionUserLinkRepository
				.findByInstitutionId(institutionId);
		List<InstitutionUserLinkResponseDTO> institutionUserLinkResponseDTO  = new ArrayList<InstitutionUserLinkResponseDTO>();
		if(CollectionUtils.isEmpty(existingInstitutionUserLink)) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		}
		existingInstitutionUserLink.stream().forEach(institutionUserLinkItem -> institutionUserLinkResponseDTO
				.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class)));

		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Fetched successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO fetchInstitutionUserLinkByAll(String institutionId, String userId) throws Exception {
		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = new InstitutionUserLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink resultInstitutionUserLinkCheck = institutionUserLinkRepository
				.findByInstitutionIdANDUserId(institutionId, userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLinkCheck,
				InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Fetched successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO saveInstitutionUserLinks(InstitutionUserLinkRequestDTO requestDto) throws Exception {

		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = new InstitutionUserLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();

		InstitutionUserLink resultInstitutionUserLink = objectMapper.convertValue(requestDto,
				InstitutionUserLink.class);
		if(!(resultInstitutionUserLink.getGrantedRightsPacs().equalsIgnoreCase("y")|| resultInstitutionUserLink.getGrantedRightsPacs().equalsIgnoreCase("n"))) {
			throw new DataIntegrityViolationException("grantedrightPacs must be Y or N");
		}
		if(!(resultInstitutionUserLink.getUpdatedInPacs().equalsIgnoreCase("y")|| resultInstitutionUserLink.getUpdatedInPacs().equalsIgnoreCase("n"))) {
			throw new DataIntegrityViolationException("updatedPacs must be Y or N");
		}
		resultInstitutionUserLink.setCreatedBy(jwtSecurityContextUtil.getId());
		resultInstitutionUserLink.setUserPwd(encodePassword(requestDto.getUserPwd()));
		resultInstitutionUserLink.setUserPacsPassword(encodePassword(requestDto.getUserPacsPassword()));
		resultInstitutionUserLink = institutionUserLinkRepository.save(resultInstitutionUserLink);
		institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLink,
				InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Saved successfully");

		return resultDto;

	}

	@Override
	public CommonResponseDTO deleteInstitutionUserLink(String institutionId, String userId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink existingInstitutionUserLink = institutionUserLinkRepository
				.findByInstitutionIdANDUserId(institutionId, userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionUserLinkRepository.delete(existingInstitutionUserLink);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setMessage("Deleted successfully");
		return resultDto;
	}

	@Override
	public CommonResponseDTO updateInstitutionUserLink(InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO,
			String institutionId, String userId) throws Exception {
		InstitutionUserLinkResponseDTO institutionUserLinkResponseDTO = new InstitutionUserLinkResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionUserLink resultInstitutionUserLink = institutionUserLinkRepository.findByInstitutionIdANDUserId(institutionId, userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		 resultInstitutionUserLink = objectMapper.convertValue(institutionUserLinkRequestDTO,
				InstitutionUserLink.class);
		resultInstitutionUserLink.setUpdateBy(jwtSecurityContextUtil.getId());
		resultInstitutionUserLink.setUserPwd(encodePassword(institutionUserLinkRequestDTO.getUserPwd()));
		resultInstitutionUserLink.setUserPacsPassword(encodePassword(institutionUserLinkRequestDTO.getUserPacsPassword()));
		resultInstitutionUserLink = institutionUserLinkRepository.save(resultInstitutionUserLink);

		institutionUserLinkResponseDTO = objectMapper.convertValue(resultInstitutionUserLink,
				InstitutionUserLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.toString());
		resultDto.setPayload(institutionUserLinkResponseDTO);
		resultDto.setMessage("Saved successfully");

		return resultDto;
	}

	@Override
	public CommonResponseDTO getByInstitutionIdORUserId(String id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionUserLinkResponseDTO> institutionUserLinkResponseDTO = new ArrayList<InstitutionUserLinkResponseDTO>();
		List<InstitutionUserLink> existingInstitutionUserLink = institutionUserLinkRepository
				.findByInstitutionIdORUserId(id);

		if (existingInstitutionUserLink.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			existingInstitutionUserLink.stream().forEach(institutionUserLinkItem -> {
				institutionUserLinkResponseDTO
						.add(objectMapper.convertValue(institutionUserLinkItem, InstitutionUserLinkResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(institutionUserLinkResponseDTO);
			resultDto.setMessage("Fetched successfully");
		}

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
