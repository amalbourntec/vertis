package com.vetris.usermanagement.v1.contoller;

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

import com.vetris.usermanagement.v1.dto.request.InstitutionDisputeDicomTagsRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.service.InstitutionDisputeDicomTagsService;

@RestController(value = "InstitutionDisputeDicomTagsController")
@RequestMapping("/v1/institutionDisputeDicomTags")
@CrossOrigin(origins = "*")
public class InstitutionDisputeDicomTagsController {

	@Autowired
	InstitutionDisputeDicomTagsService institutionDisputeDicomTagsService;

	@GetMapping("/institutionid/{institutionId}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionDisputeDicomTagsByInstitutionId(
			@PathVariable("institutionId") String institutionId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.getInstitutionDisputeDicomTagsByInstitutionId(institutionId);
		return ResponseEntity.ok(resultDto);
	}

	@GetMapping("/groupid/{groupId}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionDisputeDicomTagsByGroupIdId(
			@PathVariable("groupId") String groupId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.getInstitutionDisputeDicomTagsByGroupId(groupId);
		return ResponseEntity.ok(resultDto);
	}
	
	@GetMapping("/elementid/{elementId}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionDisputeDicomTagsByElementIdId(
			@PathVariable("elementId") String elementId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.getInstitutionDisputeDicomTagsByElementId(elementId);
		return ResponseEntity.ok(resultDto);
	}
	
	
	/*Getting values based on three PK values
	 * @GetMapping(path = "/{institutionOrGroupOrElementId}") public
	 * ResponseEntity<List<CommonResponseDTO>>
	 * fetchInstitutionDisputeDicomTagsByInstitutionIdOrGroupIdOrElementId(@
	 * PathVariable("institutionOrGroupOrElementId") String
	 * institutionOrGroupOrElementId)throws Exception { List<CommonResponseDTO>
	 * resultDto = institutionDisputeDicomTagsService.
	 * getInstitutionDisputeDicomTagsByInstitutionIdOrGroupIdOrElementId(
	 * institutionOrGroupOrElementId); return ResponseEntity.ok(resultDto); }
	 */
	 

	@GetMapping("")
	public CommonResponseDTO fetchAllInstitutionDisputeDicomTags() throws Exception {
		return this.institutionDisputeDicomTagsService.getAllInstitutionDisputeDicomTags();
	}

	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitutionDisputeDicomTags(
			@RequestBody InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.addInstitutionDisputeDicomTags(institutionDisputeDicomTagsRequest);
		return ResponseEntity.ok(resultDto);
	}

	@PutMapping("/institutionid/{institutionId}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionDisputeDicomTagsByInstitutionId(
			@RequestBody InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest,
			@PathVariable("institutionId") String institutionId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.updateInstitutionDisputeDicomTagsByInstitutionId(institutionDisputeDicomTagsRequest, institutionId);
		return ResponseEntity.ok(resultDto);
	}

	@PutMapping("/groupid/{groupId}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionDisputeDicomTagsByGroupId(
			@RequestBody InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest,
			@PathVariable("groupId") String groupId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.updateInstitutionDisputeDicomTagsByGroupId(institutionDisputeDicomTagsRequest, groupId);
		return ResponseEntity.ok(resultDto);
	}
	@PutMapping("/elementid/{elementId}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionDisputeDicomTagsByElementId(
			@RequestBody InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest,
			@PathVariable("elementId") String elementId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.updateInstitutionDisputeDicomTagsByElementId(institutionDisputeDicomTagsRequest, elementId);
		return ResponseEntity.ok(resultDto);
	}
	/*
	 * @DeleteMapping("/institutionid/{institutionId}") public
	 * ResponseEntity<CommonResponseDTO>
	 * deleteInstitutionDisputeDicomTagsByInstitutionId(@PathVariable(
	 * "institutionId") String institutionId) throws Exception { CommonResponseDTO
	 * resultDto = institutionDisputeDicomTagsService.
	 * deleteInstitutionDisputeDicomTagsByInstitutionId(institutionId); return
	 * ResponseEntity.ok(resultDto); }
	 */

}
