package com.bourntec.vetris.module.usermanagement.v1.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bourntec.vetris.entity.User;
import com.bourntec.vetris.module.usermanagement.v1.dto.request.UserRequestDTO;
import com.bourntec.vetris.module.usermanagement.v1.dto.response.UserResponseDTO;
import com.bourntec.vetris.module.usermanagement.v1.service.UserService;


/**
 * Controller for UserManagement
 * @author Amal
 *
 */

@RestController(value="UserManagementController")
@RequestMapping("/usermanagement/v1/user")
public class UserManagementController {
	
	@Autowired
	UserService userService;
	
	/**
	 * @param id
	 * @return list of users
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<UserResponseDTO> fetchUserById(@PathVariable("id")  String id) throws Exception {
		UserResponseDTO userrespDto= userService.getUserById(id);
		System.out.println(userrespDto);
		return ResponseEntity.ok(userrespDto);	
	}
	
	/**
	 * 
	 * @return all users 
	 * @throws Exception
	 */
	@GetMapping("")
	public List<User> fetchAllUsers() throws Exception {
		return this.userService.getAllUsers();	
	}
	
	/**
	 * @param userRequest
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest)throws Exception{
		UserResponseDTO userrespDto= userService.addUser(userRequest);
		return ResponseEntity.ok(userrespDto);
	}
	
	
	/**
	 * @param user
	 * @param id
	 * @return user
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user , @PathVariable("id")String id)throws Exception{
		UserResponseDTO userrespDto= userService.updateUser(user, id);
		return ResponseEntity.ok(userrespDto);	
	}
	
	/**
	 * @param id
	 * @return string 
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable("id")String id)throws Exception{
		UserResponseDTO userrespDto= userService.deleteUser( id);
		return ResponseEntity.ok(userrespDto);	
	}
	
	

}
