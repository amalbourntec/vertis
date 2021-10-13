package com.bourntec.vetris.module.usermanagement.v1.contoller;

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

import com.bourntec.vetris.module.usermanagement.v1.dto.request.UserRequestDTO;
import com.bourntec.vetris.module.usermanagement.v1.service.UserService;
import com.bourntec.vetris.utils.ResponseUtil;


/**
 * Controller for UserManagement
 * @author Amal
 *
 */

@RestController(value="UserManagementController")
@RequestMapping("/usermanagement/v1/user")
@CrossOrigin(origins = "*")
public class UserManagementController {
	
	@Autowired
	UserService userService;
	
	/**
	 * @param id
	 * @return list of users
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<ResponseUtil> fetchUserById(@PathVariable("id")  String id) throws Exception {
		ResponseUtil resultDto= userService.getUserById(id);
		return ResponseEntity.ok(resultDto);	
	}
	
	/**
	 * 
	 * @return all users 
	 * @throws Exception
	 */
	@GetMapping("")
	public ResponseUtil fetchAllUsers() throws Exception {
		return this.userService.getAllUsers();	
	}
	
	/**
	 * @param userRequest
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<ResponseUtil> createUser(@RequestBody UserRequestDTO userRequest)throws Exception{
		ResponseUtil resultDto= userService.addUser(userRequest);
		return ResponseEntity.ok(resultDto);
	}
	
	
	/**
	 * @param user
	 * @param id
	 * @return user
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<ResponseUtil> updateUser(@RequestBody UserRequestDTO user , @PathVariable("id")String id)throws Exception{
		ResponseUtil resultDto = userService.updateUser(user, id);
		return ResponseEntity.ok(resultDto);	
	}
	
	/**
	 * @param id
	 * @return string 
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseUtil> deleteUser(@PathVariable("id")String id)throws Exception{
		ResponseUtil resultDto = userService.deleteUser( id);
		return ResponseEntity.ok(resultDto);	
	}
	
	

}
