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

import com.vetris.adminmanagement.v1.dto.request.SpeciesRequestDTO;
import com.vetris.adminmanagement.v1.service.SpeciesService;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

@RestController(value = "adminmanagementController")
@RequestMapping("/adminmanagement/v1/species")
@CrossOrigin(origins = "*")
public class SpeciesController {
	
	@Autowired
	SpeciesService service;

	@GetMapping
	public ResponseEntity<CommonResponseDTO> findAllSpecies() throws Exception {
		CommonResponseDTO response = service.findAllSpecies(); 
		return ResponseEntity.ok(response);
	}

	/**
	 * @param speciesID
	 * @return list of  species
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionPhysicianLinkById(@PathVariable("id")  Integer id) throws Exception {
		CommonResponseDTO resultDto = service.getSpeciesById(id);
		return ResponseEntity.ok(resultDto);	
	}
	@PostMapping
	public ResponseEntity<CommonResponseDTO> addNewSpecies(@RequestBody SpeciesRequestDTO requestDto) throws Exception {
		CommonResponseDTO response = service.saveSpecies(requestDto);
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> editSpecies(@RequestBody SpeciesRequestDTO requestDto,@PathVariable Integer id) throws Exception {
		CommonResponseDTO response = service.editSpecies(requestDto,id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<CommonResponseDTO> deleteSpecies(@PathVariable Integer id) throws Exception {
		CommonResponseDTO response = service.deleteSpecies(id);
		return ResponseEntity.ok(response);
	}

}
