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

import com.vetris.adminmanagement.v1.dto.request.UserRolesRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.service.UserRolesService;

/**
 * Controller for UserRoles
 * @author Jose Eldhose
 *
 */

@RestController("UserRolesController")
@RequestMapping("/v1/user_roles")
@CrossOrigin(origins="*")
public class UserRolesController {
	
	@Autowired
	private UserRolesService userRolesService;
	
	/**
	 * @param id
	 * @return List of user roles
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchUserRolesById(@PathVariable(value="id") int id)throws Throwable {	
		CommonResponseDTO userRolesRespDto=userRolesService.getUserRolesById(id);
		return ResponseEntity.ok(userRolesRespDto); 
	}
	
	/**
	 * @return All user roles
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllUserRoles() throws Throwable {
		return userRolesService.getAllUserRoles();
	}
	
	/**
	 * @param userRoleRequest
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createUserRole(@RequestBody UserRolesRequestDTO userRoleRequest) throws Throwable {
		CommonResponseDTO userRolesRespDto=userRolesService.addUserRoles(userRoleRequest);
		return ResponseEntity.ok(userRolesRespDto);
	}
	
	/**
	 * @param data
	 * @param id
	 * @return user role
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updateUserRoles(@RequestBody UserRolesRequestDTO roleReqDto, @PathVariable(value="id") int id) throws Throwable {
		CommonResponseDTO userRolesRespDto=userRolesService.updateUserRoles(roleReqDto,id);
		return ResponseEntity.ok(userRolesRespDto);
	}
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<CommonResponseDTO> deleteUserRoles(@PathVariable(value="id") int id) throws Throwable {
		CommonResponseDTO userRolesRespDto=userRolesService.deleteUserRoles(id);
		return ResponseEntity.ok(userRolesRespDto);
	}

}
