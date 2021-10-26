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

import com.vetris.mastermanagement.v1.service.PhysicianService;
import com.vetris.mastermanagement.v1.dto.request.PhysicianRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

/**
 * @author anandu
 *
 */
@RestController(value="PhysicianController")
@RequestMapping("/mastermanagement/v1/physician")
@CrossOrigin(origins = "*")
public class PhysicianController {
	
	@Autowired
	PhysicianService physicianService;
	
	
	/**
	 * @param id
	 * @return physician with id
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<CommonResponseDTO> fetchPhysicianById(@PathVariable("id")  String id) throws Exception {
		CommonResponseDTO resultDto= physicianService.getPhysicianById(id);
		return ResponseEntity.ok(resultDto);	
	}
	
	
	/**
	 * @return all physicians data
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllPhysician() throws Exception {
		return this.physicianService.getAllPhysician();
	}
	
	
	/**
	 * @param request
	 * @return saved new physician
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createPhysician(@RequestBody PhysicianRequestDTO request)throws Exception{
		CommonResponseDTO resultDto= physicianService.addPhysician(request);
		return ResponseEntity.ok(resultDto);
	}
	
	
	
	/**
	 * @param physician
	 * @param id
	 * @return updated physician
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updatePhysician(@RequestBody PhysicianRequestDTO physician , @PathVariable("id")String id)throws Exception{
		CommonResponseDTO resultDto = physicianService.updatePhysician(physician, id);
		return ResponseEntity.ok(resultDto);	
	}
	
	
	/**
	 * @param id
	 * @return message 
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deletePhysician(@PathVariable("id") String id)throws Exception{
		CommonResponseDTO resultDto = physicianService.deletePhysician(id);
		return ResponseEntity.ok(resultDto);	
	}
	
	

}
