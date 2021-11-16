package com.vetris.apimanagement.v1.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.vetris.apimanagement.v1.dto.request.InstitutionCategoryLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionCategoryLinkService;

/**
 * Controller for Institution Category Link
 * 
 * @author Jini
 *
 */

@RestController(value = "InstitutionCategoryLinkController")
@RequestMapping("/v1/institution_category_link")
@CrossOrigin(origins = "*")
public class InstitutionCategoryLinkController {

	Logger logger = LoggerFactory.getLogger(InstitutionCategoryLinkController.class);

	@Autowired
	InstitutionCategoryLinkService institutionCategoryLinkService;

	/**
	 * @param Institution
	 *            Category Request
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitutionCategoryLink(
			@Valid @RequestBody InstitutionCategoryLinkRequestDTO institutionCategoryLinkRequest) throws Exception {
		logger.info("[createInstitutionCategoryLink] InstitutionCategoryLinkController called");
		CommonResponseDTO resultDto = institutionCategoryLinkService
				.addInstitutionCategoryLink(institutionCategoryLinkRequest);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param categoryId
	 * @return list of Institution Category
	 * @throws Exception
	 */
	@GetMapping("/{category_id}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionCategoryLinkById(
			@PathVariable(value = "category_id") Integer categoryId) throws Exception {
		logger.info("[fetchInstitutionCategoryLinkById] InstitutionCategoryLinkController called By categoryId");
		CommonResponseDTO institutionCategoryLinkRespDto = institutionCategoryLinkService
				.getInstitutionCategoryLinkById(categoryId);
		return ResponseEntity.ok(institutionCategoryLinkRespDto);
	}

	/**
	 * 
	 * @return all Institution Category
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllInstitutionCategoryLink() throws Exception {
		logger.info("[fetchAllInstitutionCategoryLink] InstitutionCategoryLinkController called");
		return institutionCategoryLinkService.getAllInstitutionCategoryLink();
	}

	/**
	 * @param Institution
	 *            Category
	 * @param categoryId
	 * @return Institution Category
	 * @throws Exception
	 */
	@PutMapping("/{category_id}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionCategoryLink(
			@Valid @RequestBody InstitutionCategoryLinkRequestDTO institutionCategoryLinkReqDto,
			@PathVariable(value = "category_id") Integer categoryId) throws Exception {
		logger.info("[updateInstitutionCategoryLink] InstitutionCategoryLinkController called By categoryId");
		CommonResponseDTO resultDto = institutionCategoryLinkService
				.updateInstitutionCategoryLink(institutionCategoryLinkReqDto, categoryId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param categoryId
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{category_id}")
	public ResponseEntity<CommonResponseDTO> deleteInstitutionCategoryLink(
			@PathVariable(value = "category_id") Integer categoryId) throws Exception {
		logger.info("[deleteInstitutionCategoryLink] InstitutionCategoryLinkController called By categoryId");
		CommonResponseDTO resultDto = institutionCategoryLinkService.deleteInstitutionCategoryLink(categoryId);
		return ResponseEntity.ok(resultDto);
	}
}
