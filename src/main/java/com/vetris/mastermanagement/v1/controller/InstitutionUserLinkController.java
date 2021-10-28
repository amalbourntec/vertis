package com.vetris.mastermanagement.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetris.mastermanagement.v1.dto.request.InstitutionUserLinkRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.mastermanagement.v1.service.InstitutionUserLinkService;

@RestController(value="InstitutionUserLinkController")
@RequestMapping("/mastermanagement/v1/institutionUserLink")
@CrossOrigin(origins = "*")
public class InstitutionUserLinkController {
	
	@Autowired
	InstitutionUserLinkService institutionUserLinkService;

	@GetMapping()
	ResponseEntity<CommonResponseDTO> findAllInstitutionUserLink()throws Exception{
		CommonResponseDTO response=institutionUserLinkService.findAllInstitutionUserLink();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/byuserid/{userId}")
	public ResponseEntity<CommonResponseDTO> findInstitutionUserLinkByUserId(@PathVariable("userId")  String userId) throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService.findByUserId(userId);
		return ResponseEntity.ok(resultDto);	
	}
	
	@GetMapping("/byinstitutionid/{institutionId}")
	public ResponseEntity<CommonResponseDTO> findInstitutionUserLinkByInstitutionId(@PathVariable("institutionId")  String institutionId) throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService.findByInstitutionId(institutionId);
		return ResponseEntity.ok(resultDto);
	}
	
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitutionUserLink(
			@RequestBody InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO) throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService
				.saveInstitutionUserLinks(institutionUserLinkRequestDTO);
		return ResponseEntity.ok(resultDto);
	}
	
	@PutMapping("/institutionid/{institutionId}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionUserLinkByInstitutionId(
			@RequestBody InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO,
			@PathVariable("institutionId") String institutionId) throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService
				.updateInstitutionUserLinkByInstitutionId(institutionUserLinkRequestDTO, institutionId);
		return ResponseEntity.ok(resultDto);
	}
	
	@PutMapping("/userid/{userId}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionUserLinkByUserId(
			@RequestBody InstitutionUserLinkRequestDTO institutionUserLinkRequestDTO,
			@PathVariable("userId") String userId) throws Exception {
		CommonResponseDTO resultDto = institutionUserLinkService
				.updateInstitutionUserLinkByUserId(institutionUserLinkRequestDTO, userId);
		return ResponseEntity.ok(resultDto);
	}
}
