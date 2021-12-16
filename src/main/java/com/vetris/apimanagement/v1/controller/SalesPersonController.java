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

import com.vetris.apimanagement.v1.dto.request.SalesPersonRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.SalesPersonService;


/**
 * controller for salesPerson
 * 
 * @author ShekarReddySamreddy
 * 
 */

@RestController(value = "SalesPersonController")
@RequestMapping("/mastermanagement/v1/salesperson")
@CrossOrigin(origins = "*")
public class SalesPersonController {

	@Autowired
	SalesPersonService service;

	@GetMapping("")
	public ResponseEntity<CommonResponseDTO> getAllSalesPerson() throws Exception {
		CommonResponseDTO response = service.getAllSalesPerson();
		return ResponseEntity.ok(response);
	}

	/**
	 * @param SalesPersonID
	 * @return list of SalesPerson
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<CommonResponseDTO> fetchSalesPersonById(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO resultDto = service.getSalesPersonById(id);
		return ResponseEntity.ok(resultDto);
	}

	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> addNewSalesPerson(@Valid @RequestBody SalesPersonRequestDTO requestDto)
			throws Exception {
		CommonResponseDTO response = service.saveSalesPerson(requestDto);
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> editSalesPerson(@Valid @RequestBody SalesPersonRequestDTO requestDto,
			@PathVariable String id) throws Exception {
		CommonResponseDTO response = service.updateSalesPerson(requestDto, id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CommonResponseDTO> deleteSalesPerson(@PathVariable String id) throws Exception {
		CommonResponseDTO response = service.deleteSalesPerson(id);
		return ResponseEntity.ok(response);
	}

}
