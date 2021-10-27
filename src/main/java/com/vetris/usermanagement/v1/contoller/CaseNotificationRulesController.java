package com.vetris.usermanagement.v1.contoller;

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

import com.vetris.usermanagement.v1.dto.request.CaseNotificationRulesRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.service.CaseNotificationRulesService;

@RestController(value = "CaseNotificationRulesController")
@RequestMapping("/v1/case_notification_rules")
@CrossOrigin(origins = "*")
/**
 * Controller for CaseNotificationRules
 * 
 * @author Jose Eldhose
 *
 */
public class CaseNotificationRulesController {
	@Autowired
	CaseNotificationRulesService caseNotificationRulesService;

	/**
	 * @param id
	 * @return list of CaseNotificationRules
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<CommonResponseDTO> fetchCaseNotificationRulesById(@PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.getCaseNotificationRulesById(id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * 
	 * @return all CaseNotificationRules
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllCaseNotificationRules() throws Exception {
		return this.caseNotificationRulesService.getAllCaseNotificationRules();
	}

	/**
	 * @param caseNotificationRulesRequest
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createCaseNotificationRules(
			@RequestBody CaseNotificationRulesRequestDTO caseNotificationRulesRequest) throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService
				.addCaseNotificationRules(caseNotificationRulesRequest);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param CaseNotificationRule
	 * @param id
	 * @return CaseNotificationRules
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updateCaseNotificationRules(
			@RequestBody CaseNotificationRulesRequestDTO caseNotificationRules, @PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.updateCaseNotificationRules(caseNotificationRules,
				id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteCaseNotificationRules(@PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.deleteCaseNotificationRules(id);
		return ResponseEntity.ok(resultDto);
	}

}
