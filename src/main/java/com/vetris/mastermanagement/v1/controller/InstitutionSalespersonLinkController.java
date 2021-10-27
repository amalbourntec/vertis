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

import com.vetris.mastermanagement.v1.dto.request.InstitutionSalespersonLinkRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.mastermanagement.v1.service.InstitutionSalespersonLinkService;

/**
 * Controller for InstitutionSalespersonLink
 * 
 * @author Aldrin
 *
 */
@RestController(value = "InstitutionSalespersonLinkController")
@RequestMapping("/v1/salesperson")
@CrossOrigin(origins = "*")
public class InstitutionSalespersonLinkController {

	@Autowired
	InstitutionSalespersonLinkService institutionSalespersonLinkService;

	/**
	 * @param id
	 * @return institution Salesperson
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchSalespersonById(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO salespersonRespDTO = institutionSalespersonLinkService.getSalespersonById(id);
		return ResponseEntity.ok(salespersonRespDTO);
	}

	/**
	 * @return all institution Salesperson
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllSalesperson() throws Exception {
		return institutionSalespersonLinkService.getAllSalesperson();
	}

	/**
	 * @param salespersonRequest
	 * @return institution Salesperson
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createSalesperson(
			@RequestBody InstitutionSalespersonLinkRequestDTO salespersonRequest) throws Exception {
		CommonResponseDTO salespersonRespDTO = institutionSalespersonLinkService.addSalesperson(salespersonRequest);
		return ResponseEntity.ok(salespersonRespDTO);
	}

	/**
	 * @param salespersonRequest
	 * @param id
	 * @return institution Salesperson
	 * @throws Exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> updateSalesperson(
			@RequestBody InstitutionSalespersonLinkRequestDTO salespersonRequest, @PathVariable("id") String id)
			throws Exception {
		CommonResponseDTO salespersonRespDTO = institutionSalespersonLinkService.updateSalesperson(salespersonRequest,
				id);
		return ResponseEntity.ok(salespersonRespDTO);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteSalesperson(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO salespersonRespDTO = institutionSalespersonLinkService.deleteSalesperson(id);
		return ResponseEntity.ok(salespersonRespDTO);
	}

}
