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

import com.vetris.adminmanagement.v1.dto.request.UserMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.service.UserMenuRightsService;

/**
 * @author anandu
 * User menu rights controller
 */
@RestController(value="UserMenuRightsController")
@RequestMapping("/v1/user_menu_rights")
@CrossOrigin(origins="*")
public class UserMenuRightsController {
	
	@Autowired
	UserMenuRightsService userMenuRightsService;
	
	
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createUserMenuRights(@RequestBody UserMenuRightsRequestDTO userMenuRequest) throws Exception {
		CommonResponseDTO userMenuRespDto=userMenuRightsService.addUserMenuRights(userMenuRequest);
		return ResponseEntity.ok(userMenuRespDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchUserMenuRightsById(@PathVariable(value="id") String id)throws Exception {	
		CommonResponseDTO userMenuRespDto=userMenuRightsService.getUserMenuRightsByUserId(id);
		return ResponseEntity.ok(userMenuRespDto); 
	}
	
	@GetMapping("")
	public CommonResponseDTO fetchAllUserMenuRights() throws Exception {
		return userMenuRightsService.getAllUserMenuRights();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updateUserMenuRights(@RequestBody UserMenuRightsRequestDTO roleMenuReqDto, @PathVariable(value="id") String id) throws Exception {
		CommonResponseDTO userMenuRespDto=userMenuRightsService.updateUserMenuRights(roleMenuReqDto,id);
		return ResponseEntity.ok(userMenuRespDto);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<CommonResponseDTO> deleteUserMenuRights(@PathVariable(value="id") String id) throws Exception {
		CommonResponseDTO userMenuRespDto=userMenuRightsService.deleteUserMenuRights(id);
		return ResponseEntity.ok(userMenuRespDto);
	}
	
	
}
