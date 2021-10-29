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

import com.vetris.mastermanagement.v1.dto.request.InstitutionDisputeDicomTagsRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.mastermanagement.v1.service.InstitutionDisputeDicomTagsService;

@RestController(value = "InstitutionDisputeDicomTagsController")
@RequestMapping("/v1/institutionDisputeDicomTags")
@CrossOrigin(origins = "*")
public class InstitutionDisputeDicomTagsController {

	@Autowired
	InstitutionDisputeDicomTagsService institutionDisputeDicomTagsService;

	@GetMapping("")
	public CommonResponseDTO fetchAllInstitutionDisputeDicomTags() throws Exception {
		return this.institutionDisputeDicomTagsService.getAllInstitutionDisputeDicomTags();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<CommonResponseDTO> fetchByInstitutionIdORGroupIdORElementId(@PathVariable("id") String id)
			throws Exception {

		CommonResponseDTO resultDto = institutionDisputeDicomTagsService.getByInstitutionIdORGroupIdORElementId(id);
		return ResponseEntity.ok(resultDto);
	}

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

	@GetMapping("/getbyall/{institutionId}/{groupId}/{elementId}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionDisputeDicomTagsByAll(
			@PathVariable("institutionId") String institutionId, @PathVariable("groupId") String groupId,
			@PathVariable("elementId") String elementId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.fetchInstitutionDisputeDicomTagsByAll(institutionId, groupId, elementId);
		return ResponseEntity.ok(resultDto);
	}

	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitutionDisputeDicomTags(
			@RequestBody InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.addInstitutionDisputeDicomTags(institutionDisputeDicomTagsRequest);
		return ResponseEntity.ok(resultDto);
	}

	@PutMapping("/update/{institutionId}/{groupId}/{elementId}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionDisputeDicomTags(
			@RequestBody InstitutionDisputeDicomTagsRequestDTO institutionDisputeDicomTagsRequest,
			@PathVariable("institutionId") String institutionId, @PathVariable("groupId") String groupId,
			@PathVariable("elementId") String elementId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService.updateInstitutionDisputeDicomTags(
				institutionDisputeDicomTagsRequest, institutionId, groupId, elementId);
		return ResponseEntity.ok(resultDto);
	}

	@DeleteMapping("/delete/{institutionId}/{groupId}/{elementId}")
	public ResponseEntity<CommonResponseDTO> deleteInstitutionDisputeDicomTags(
			@PathVariable("institutionId") String institutionId, @PathVariable("groupId") String groupId,
			@PathVariable("elementId") String elementId) throws Exception {
		CommonResponseDTO resultDto = institutionDisputeDicomTagsService
				.deleteInstitutionDisputeDicomTags(institutionId, groupId, elementId);
		return ResponseEntity.ok(resultDto);
	}

}
