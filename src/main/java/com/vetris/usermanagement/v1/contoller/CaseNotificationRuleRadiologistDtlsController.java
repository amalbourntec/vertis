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

import com.vetris.usermanagement.v1.dto.request.CaseNotificationRuleRadiologistDtlsRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.service.CaseNotificationRuleRadiologistDtlsService;

/**
 * Controller for CaseNotificationRuleRadiologistDtls
 * @author Jini
 *
 */

@RestController(value="CaseNotificationRuleRadiologistDtlsController")
@RequestMapping("/v1/case_notification_rule_radiologist_dtls")
@CrossOrigin(origins="*")
public class CaseNotificationRuleRadiologistDtlsController {
	
	@Autowired
	CaseNotificationRuleRadiologistDtlsService caseNotificationRuleRadiologistDtlsService;
	
	/**
	 * 
	 * @return all Case Notification Rule Radiologist Dtls
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllCaseNotificationRuleRadiologistDtls() throws Exception {
		return caseNotificationRuleRadiologistDtlsService.getAllCaseNotificationRuleRadiologistDtls();
	}
	
	/**
	 * @param rule_no
	 * @return list of Case Notification Rule Radiologist Dtls
	 * @throws Exception
	 */
	@GetMapping("/{rule_no}")
	public ResponseEntity<CommonResponseDTO> fetchCaseNotificationRuleRadiologistDtlsById(@PathVariable(value="rule_no") Integer RuleNo)throws Exception {	
		CommonResponseDTO caseNotificationRuleRadiologistDtlsRespDto = caseNotificationRuleRadiologistDtlsService.getCaseNotificationRuleRadiologistDtlsById(RuleNo);
		return ResponseEntity.ok(caseNotificationRuleRadiologistDtlsRespDto); 
	}
	
	/**
	 * @param Case Notification Rule Radiologist Dtls Request
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createCaseNotificationRuleRadiologistDtls(@RequestBody CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO) throws Exception  {
		CommonResponseDTO resultDto = caseNotificationRuleRadiologistDtlsService.addCaseNotificationRuleRadiologistDtls(caseNotificationRuleRadiologistDtlsRequestDTO);
		return ResponseEntity.ok(resultDto);
	}
	
	/**
	 * @param Case Notification Rule Radiologist Dtls
	 * @param rule_no
	 * @return Case Notification Rule Radiologist Dtls
	 * @throws Exception
	 */
	@PutMapping("/{rule_no}")
	public ResponseEntity<CommonResponseDTO> updateCaseNotificationRuleRadiologistDtls(@RequestBody CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO, @PathVariable(value="rule_no") Integer RuleNo) throws Exception {
		CommonResponseDTO resultDto = caseNotificationRuleRadiologistDtlsService.updateCaseNotificationRuleRadiologistDtls(caseNotificationRuleRadiologistDtlsRequestDTO, RuleNo);
		return ResponseEntity.ok(resultDto);
	}
	
	/**
	 * @param rule_no
	 * @return string 
	 * @throws Exception
	 */
	@DeleteMapping("/{rule_no}")
	public ResponseEntity<CommonResponseDTO> deleteCaseNotificationRuleRadiologistDtls(@PathVariable(value="rule_no") Integer RuleNo) throws Exception {
		CommonResponseDTO resultDto = caseNotificationRuleRadiologistDtlsService.deleteCaseNotificationRuleRadiologistDtls(RuleNo);
		return ResponseEntity.ok(resultDto);
	}
	
}
