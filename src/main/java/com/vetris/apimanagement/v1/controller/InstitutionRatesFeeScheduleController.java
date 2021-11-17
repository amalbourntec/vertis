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

import com.vetris.apimanagement.v1.dto.request.InstitutionRatesFeeScheduleRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionRatesFeeScheduleService;

/**
 * Controller for InstitutionRatesFeeSchedule
 * 
 * @author Aldrin
 *
 */

@RestController(value = "InstitutionRatesFeeScheduleController")
@RequestMapping("/v1/ratefee")
@CrossOrigin(origins = "*")
public class InstitutionRatesFeeScheduleController {

	@Autowired
	InstitutionRatesFeeScheduleService institutionRatesFeeScheduleService;

	/**
	 * @param id
	 * @return InstitutionRatesFeeSchedule
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchRateFeeById(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO rateFeeRespDTO = institutionRatesFeeScheduleService.getRateFeeById(id);
		return ResponseEntity.ok(rateFeeRespDTO);
	}

	/**
	 * @return all InstitutionRatesFeeSchedule
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllRateFee() throws Exception {
		return institutionRatesFeeScheduleService.getAllRateFee();
	}

	/**
	 * @param rateFeeRequest
	 * @return String InstitutionRatesFeeSchedule
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createRateFee(@Valid
			@RequestBody InstitutionRatesFeeScheduleRequestDTO rateFeeRequest)
			throws Exception {
		CommonResponseDTO rateFeeRespDTO = institutionRatesFeeScheduleService.addRateFee(rateFeeRequest);
		return ResponseEntity.ok(rateFeeRespDTO);
	}

	/**
	 * @param rateFeeRequest
	 * @param id
	 * @return InstitutionRatesFeeSchedule
	 * @throws Exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> updateRateFee(@Valid
			@RequestBody InstitutionRatesFeeScheduleRequestDTO rateFeeRequest,
			@PathVariable("id") String id) throws Exception {
		CommonResponseDTO rateFeeRespDTO = institutionRatesFeeScheduleService.updateRateFee(rateFeeRequest, id);
		return ResponseEntity.ok(rateFeeRespDTO);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteRateFee(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO rateFeeRespDTO = institutionRatesFeeScheduleService.deleteRateFee(id);
		return ResponseEntity.ok(rateFeeRespDTO);
	}

}
