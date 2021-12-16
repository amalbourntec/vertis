package com.vetris.apimanagement.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetris.apimanagement.v1.dto.request.InstitutionUserLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionUserLinkService;

/**
 * Controller for InstitutionUserLink
 * 
 * @author Dhanesh
 *
 */

@RestController(value = "InstitutionUserLinkController")
@RequestMapping("/apimanagement/v1/institutionUserLink")
@CrossOrigin(origins = "*")
public class InstitutionUserLinkController {

	@Autowired
	InstitutionUserLinkService institutionUserLinkService;

	/**
	 * @return all institutionUserLink
	 * @throws Exception
	 */
	@GetMapping()
	ResponseEntity<CommonResponseDTO> findAllInstitutionUserLink() throws Exception {
		CommonResponseDTO response = institutionUserLinkService.findAllInstitutionUserLink();
		return ResponseEntity.ok(response);
	}

	/**
	 * @param id
	 * @return institutionUserLink
	 * @throws Exception
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<CommonResponseDTO> fetchByInstitutionIdORUserID(@PathVariable("id") String id)
			throws Exception {

		CommonResponseDTO resultDto = institutionUserLinkService.getByInstitutionIdORUserId(id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param userId
	 * @return institutionUserLink
	 * @throws Exception
	 */
	@GetMapping("/byuserid/{userId}")
	public ResponseEntity<CommonResponseDTO> findInstitutionUserLinkByUserId(@PathVariable("userId") String userId)
			throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService.findByUserId(userId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param institutionId
	 * @return institutionUserLink
	 * @throws Exception
	 */
	@GetMapping("/byinstitutionid/{institutionId}")
	public ResponseEntity<CommonResponseDTO> findInstitutionUserLinkByInstitutionId(
			@PathVariable("institutionId") String institutionId) throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService.findByInstitutionId(institutionId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param institutionId
	 * @param userId
	 * @return institutionUserLink
	 * @throws Exception
	 */
	@GetMapping("/getbyall/{institutionId}/{userId}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionDisputeDicomTagsByAll(
			@PathVariable("institutionId") String institutionId, @PathVariable("userId") String userId)
			throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService.fetchInstitutionUserLinkByAll(institutionId, userId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param institutionUserLinkRequest
	 * @return institutionUserLink
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitutionUserLink(
			@Valid @RequestBody InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO) throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService
				.saveInstitutionUserLinks(institutionUserLinkRequestDTO);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param institutionUserLinkRequest
	 * @param institutionId
	 * @param userId
	 * @return institutionUserLink
	 * @throws Exception
	 */
	@PutMapping("/update/{institutionId}/{userId}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionUserLink(
			@Valid @RequestBody InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO,
			@PathVariable("institutionId") String institutionId, @PathVariable("userId") String userId)
			throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService
				.updateInstitutionUserLink(institutionUserLinkRequestDTO, institutionId, userId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param institutionId
	 * @param userId
	 * @return institutionUserLink
	 * @throws Exception
	 */
	@DeleteMapping("/delete/{institutionId}/{userId}")
	public ResponseEntity<CommonResponseDTO> deleteInstitutionUserLink(
			@PathVariable("institutionId") String institutionId, @PathVariable("userId") String userId)
			throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService.deleteInstitutionUserLink(institutionId, userId);
		return ResponseEntity.ok(resultDto);
	}
}
