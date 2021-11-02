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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRulesRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.service.CaseNotificationRulesService;

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
	 * @param ruleNo
	 * @return list of CaseNotificationRules
	 * @throws Exception
	 */
	@GetMapping("ruleNo/{id}")
	public ResponseEntity<CommonResponseDTO> fetchCaseNotificationRulesByRuleNo(@PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.getCaseNotificationRulesByRuleNo(id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param all composite keys
	 * @return list of CaseNotificationRules
	 * @throws Exception
	 */
	@GetMapping("/get_all")
	public ResponseEntity<CommonResponseDTO> fetchAllCaseNotificationRules(@RequestParam Integer ruleNo,
			@RequestParam Integer pacsStatusId, @RequestParam Integer priorityId) throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.getAllCaseNotificationRules(ruleNo, pacsStatusId,
				priorityId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param pacsStatusId
	 * @return list of CaseNotificationRules
	 * @throws Exception
	 */
	@GetMapping("/pacsStatusId/{id}")
	public ResponseEntity<CommonResponseDTO> fetchCaseNotificationRulesByPacsStatusId(@PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.getCaseNotificationRulesByPacsStatusId(id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param priorityId
	 * @return list of CaseNotificationRules
	 * @throws Exception
	 */
	@GetMapping("priorityId/{id}")
	public ResponseEntity<CommonResponseDTO> fetchCaseNotificationRulesByPriorityId(@PathVariable("id") Integer id)
			throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.getCaseNotificationRulesByPriorityId(id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * 
	 * @return all CaseNotificationRules
	 * @throws Exception
	 */
	/*
	 * @GetMapping("") public CommonResponseDTO fetchAllCaseNotificationRules()
	 * throws Exception { return
	 * this.caseNotificationRulesService.getAllCaseNotificationRules(); }
	 */

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
	@PutMapping("")
	public ResponseEntity<CommonResponseDTO> updateCaseNotificationRules(
			@RequestBody CaseNotificationRulesRequestDTO caseNotificationRules, @RequestParam Integer ruleNo,
			@RequestParam Integer pacsStatusId, @RequestParam Integer priorityId) throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.updateCaseNotificationRules(caseNotificationRules,
				ruleNo, pacsStatusId, priorityId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("")
	public ResponseEntity<CommonResponseDTO> deleteCaseNotificationRules(@RequestParam Integer ruleNo,
			@RequestParam Integer pacsStatusId, @RequestParam Integer priorityId) throws Exception {
		CommonResponseDTO resultDto = caseNotificationRulesService.deleteCaseNotificationRules(ruleNo, pacsStatusId,
				priorityId);
		return ResponseEntity.ok(resultDto);
	}

}
