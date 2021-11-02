package com.vetris.apimanagement.v1.controller;

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

import com.vetris.apimanagement.v1.dto.request.InstitutionRegModalityLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionRegModalityLinkService;

/**
 * Controller for Institution registration modality.
 * 
 * @author Jose Eldhose
 *
 */
@RestController("InstitutionRegModalityLinkController")
@RequestMapping("/v1/reg_modality")
@CrossOrigin(origins = "*")
public class InstitutionRegModalityLinkController {

	@Autowired
	InstitutionRegModalityLinkService institutionRegModalityLinkService;

	/**
	 * @return List of registration modality
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllRegModality() throws Exception {
		return institutionRegModalityLinkService.getAllRegModality();
	}

	/**
	 * @param id
	 * @return Registration modality
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchRegModalityById(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO alternateNameRespDTO = institutionRegModalityLinkService.getRegModalityById(id);
		return ResponseEntity.ok(alternateNameRespDTO);
	}

	/**
	 * @param regModalityRequest
	 * @return String registration modality
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createRegModality(
			@RequestBody InstitutionRegModalityLinkRequestDTO regModalityRequest) throws Exception {
		CommonResponseDTO regModalityRespDTO = institutionRegModalityLinkService.addRegModality(regModalityRequest);
		return ResponseEntity.ok(regModalityRespDTO);
	}

	/**
	 * @param RegModalityRequest
	 * @param id
	 * @return String registration modality
	 * @throws Exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> updateRegModality(
			@RequestBody InstitutionRegModalityLinkRequestDTO regModalityRequest, @PathVariable("id") String id)
			throws Exception {
		CommonResponseDTO regModalityRespDTO = institutionRegModalityLinkService
				.updateInstitutionRegModality(regModalityRequest, id);
		return ResponseEntity.ok(regModalityRespDTO);
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteRegModality(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO alternateNameRespDTO = institutionRegModalityLinkService.deleteInstitutionRegModality(id);
		return ResponseEntity.ok(alternateNameRespDTO);
	}

}
