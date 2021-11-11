package com.vetris.adminmanagement.v1.contoller;

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

import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRuleHdrRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.service.CaseNotificationRuleHdrService;


/**
 * Controller for CaseNotificationRuleHdr
 * 
 * @author Aldrin
 *
 */

@RestController(value = "CaseNotificationRuleHdrController")
@RequestMapping("/v1/caseNotificationRuleHdr")
@CrossOrigin(origins = "*")
public class CaseNotificationRuleHdrController {

	@Autowired
	CaseNotificationRuleHdrService caseNotificationRuleHdrService;

	/**
	 * @param id
	 * @return CaseNotificationRuleHdr
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchCaseNotificationRuleHdrById(@PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO caseNotificationRuleHdrRespDTO = caseNotificationRuleHdrService
				.getCaseNotificationRuleHdrById(id);
		return ResponseEntity.ok(caseNotificationRuleHdrRespDTO);
	}

	/**
	 * @return all CaseNotificationRuleHdr
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllCaseNotificationRuleHdr() throws Exception {
		return caseNotificationRuleHdrService.getAllCaseNotificationRuleHdr();
	}

	/**
	 * @param caseNotificationRuleHdrRequest
	 * @return CaseNotificationRuleHdr
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createCaseNotificationRuleHdr(
			@RequestBody CaseNotificationRuleHdrRequestDTO caseNotificationRuleHdrRequest) throws Exception {
		CommonResponseDTO caseNotificationRuleHdrRespDTO = caseNotificationRuleHdrService
				.addCaseNotificationRuleHdr(caseNotificationRuleHdrRequest);
		return ResponseEntity.ok(caseNotificationRuleHdrRespDTO);
	}

	/**
	 * @param caseNotificationRuleHdrRequest
	 * @param id
	 * @return CaseNotificationRuleHdr
	 * @throws Exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> updateCaseNotificationRuleHdr(
			@RequestBody CaseNotificationRuleHdrRequestDTO caseNotificationRuleHdrRequest,
			@PathVariable("id") Integer id) throws Exception {
		CommonResponseDTO caseNotificationRuleHdrRespDTO = caseNotificationRuleHdrService
				.updateCaseNotificationRuleHdr(caseNotificationRuleHdrRequest, id);
		return ResponseEntity.ok(caseNotificationRuleHdrRespDTO);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteCaseNotificationRuleHdr(@PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO caseNotificationRuleHdrRespDTO = caseNotificationRuleHdrService
				.deleteCaseNotificationRuleHdr(id);
		return ResponseEntity.ok(caseNotificationRuleHdrRespDTO);
	}
}
