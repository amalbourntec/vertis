package com.vetris.mastermanagement.v1.controller;

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

import com.vetris.mastermanagement.v1.dto.request.InstitutionAltNameLinkRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.mastermanagement.v1.service.InstitutionAltNameLinkService;

/**
 * Controller for InstitutionAltNameLink
 * 
 * @author Aldrin
 *
 */
@RestController(value = "InstitutionAltNameLinkController")
@RequestMapping("/v1/alternate_name")
@CrossOrigin(origins = "*")
public class InstitutionAltNameLinkController {

	@Autowired
	InstitutionAltNameLinkService institutionAltNameLinkService;

	/**
	 * @param id
	 * @return institution alternate name
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchAlternateNameById(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO alternateNameRespDTO = institutionAltNameLinkService.getInstitutionAltNameById(id);
		return ResponseEntity.ok(alternateNameRespDTO);
	}

	/**
	 * @return all institution alternate name
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllAlternateName() throws Exception {
		return institutionAltNameLinkService.getAllInstitutionAltName();
	}

	/**
	 * @param alternateNameRequest
	 * @return String institutionAltName
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createAlternateName(
			@RequestBody InstitutionAltNameLinkRequestDTO alternateNameRequest) throws Exception {
		CommonResponseDTO alternateNameRespDTO = institutionAltNameLinkService
				.addInstitutionAltName(alternateNameRequest);
		return ResponseEntity.ok(alternateNameRespDTO);
	}

	/**
	 * @param alternateNameRequest
	 * @param id
	 * @return institutionAltName
	 * @throws Exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> updateAlternateName(
			@RequestBody InstitutionAltNameLinkRequestDTO alternateNameRequest, @PathVariable("id") String id)
			throws Exception {
		CommonResponseDTO alternateNameRespDTO = institutionAltNameLinkService
				.updateInstitutionAltName(alternateNameRequest, id);
		return ResponseEntity.ok(alternateNameRespDTO);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteAlternateName(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO alternateNameRespDTO = institutionAltNameLinkService.deleteInstitutionAltName(id);
		return ResponseEntity.ok(alternateNameRespDTO);
	}

}
