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

import com.vetris.apimanagement.v1.dto.request.InstitutionRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionService;

/**
 * Controller for institution
 * 
 * @author ShekarReddySamreddy
 * 
 */

@RestController(value = "InstitutionController")
@RequestMapping("v1/institution")
@CrossOrigin(origins = "*")

public class InstitutionController {

	@Autowired
	InstitutionService institutionService;

	/**
	 * @param id
	 * @return list of institutions
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionById(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO resultDto = institutionService.getInstitutionById(id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * 
	 * @return all institution
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllInstitutions() throws Exception {
		return this.institutionService.getAllInstitutions();
	}

	/**
	 * @param institutionRequest
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitution(@RequestBody InstitutionRequestDTO institutionRequest)
			throws Exception {
		CommonResponseDTO resultDto = institutionService.addInstitution(institutionRequest);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param Institution
	 * @param id
	 * @return institution
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updateInstitution(@RequestBody InstitutionRequestDTO institution,
			@PathVariable("id") String id) throws Exception {
		CommonResponseDTO resultDto = institutionService.updateInstitution(institution, id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteInstitution(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO resultDto = institutionService.deleteInstitution(id);
		return ResponseEntity.ok(resultDto);
	}

}
