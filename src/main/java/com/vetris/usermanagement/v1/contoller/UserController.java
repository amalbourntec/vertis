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

import com.vetris.usermanagement.v1.dto.request.UserRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.service.UserService;


/**
 * Controller for UserManagement
 * @author Amal
 *
 */

@RestController(value="UserManagementController")
@RequestMapping("/usermanagement/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * @param id
	 * @return list of users
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<CommonResponseDTO> fetchUserById(@PathVariable("id")  String id) throws Exception {
		CommonResponseDTO resultDto= userService.getUserById(id);
		return ResponseEntity.ok(resultDto);	
	}
	
	/**
	 * 
	 * @return all users 
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllUsers() throws Exception {
		return this.userService.getAllUsers();	
	}
	
	/**
	 * @param userRequest
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createUser(@RequestBody UserRequestDTO userRequest)throws Exception{
		CommonResponseDTO resultDto= userService.addUser(userRequest);
		return ResponseEntity.ok(resultDto);
	}
	
	
	/**
	 * @param user
	 * @param id
	 * @return user
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updateUser(@RequestBody UserRequestDTO user , @PathVariable("id")String id)throws Exception{
		CommonResponseDTO resultDto = userService.updateUser(user, id);
		return ResponseEntity.ok(resultDto);	
	}
	
	/**
	 * @param id
	 * @return string 
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteUser(@PathVariable("id")String id)throws Exception{
		CommonResponseDTO resultDto = userService.deleteUser( id);
		return ResponseEntity.ok(resultDto);	
	}
	
	

}
