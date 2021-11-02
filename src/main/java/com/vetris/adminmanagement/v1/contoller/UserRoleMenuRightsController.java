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

import com.vetris.adminmanagement.v1.dto.request.UserRoleMenuRightsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.service.UserRoleMenuRightsService;

@RestController(value="UserRoleMenuRightsController")
@RequestMapping("/v1/user_role_menu_rights")
@CrossOrigin(origins="*")
public class UserRoleMenuRightsController {
	
	@Autowired
	UserRoleMenuRightsService userRoleMenuRightsService;
	
	
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createUserRoleMenuRights(@RequestBody UserRoleMenuRightsRequestDTO userRoleMenuRequest) throws Exception {
		CommonResponseDTO userMenuRespDto=userRoleMenuRightsService.addUserRoleMenuRights(userRoleMenuRequest);
		return ResponseEntity.ok(userMenuRespDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchUserRoleMenuRightsById(@PathVariable(value="id") Integer id)throws Exception {	
		CommonResponseDTO userMenuRespDto=userRoleMenuRightsService.getUserRoleMenuRightsByUserId(id);
		return ResponseEntity.ok(userMenuRespDto); 
	}
	
	@GetMapping("")
	public CommonResponseDTO fetchAllUserRoleMenuRights() throws Exception {
		return userRoleMenuRightsService.getAllUserRoleMenuRights();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updateUserRoleMenuRights(@RequestBody UserRoleMenuRightsRequestDTO roleMenuReqDto, @PathVariable(value="id") Integer id) throws Exception {
		CommonResponseDTO userMenuRespDto=userRoleMenuRightsService.updateUserRoleMenuRights(roleMenuReqDto,id);
		return ResponseEntity.ok(userMenuRespDto);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<CommonResponseDTO> deleteUserRoleMenuRights(@PathVariable(value="id") Integer id) throws Exception {
		CommonResponseDTO userMenuRespDto=userRoleMenuRightsService.deleteUserRoleMenuRights(id);
		return ResponseEntity.ok(userMenuRespDto);
	}

}
