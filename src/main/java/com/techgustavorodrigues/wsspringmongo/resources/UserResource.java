package com.techgustavorodrigues.wsspringmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techgustavorodrigues.wsspringmongo.domain.Post;
import com.techgustavorodrigues.wsspringmongo.domain.User;
import com.techgustavorodrigues.wsspringmongo.dto.UserDTO;
import com.techgustavorodrigues.wsspringmongo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path ="/users")
public class UserResource {
	
	@Autowired
	private UserService uservice;

	@Operation(summary = "Get All", description = "Get All Users", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "returns all users")
	})
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = uservice.findAll();
		List<UserDTO> listDto = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@Operation(summary = "Get By ID", description = "Get User By ID", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "returns user by ID"),
			@ApiResponse(responseCode = "404", description = "user not found")
	})
	@GetMapping(path="/{id}")
	public ResponseEntity<UserDTO> findbyId(@PathVariable String id) {
		User obj = uservice.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@Operation(summary = "Insert New User", description = "Insert New User", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "new user inserted successfully")
	})
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = uservice.fromDTO(objDto);
		obj  = uservice.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Update By ID", description = "Update User By ID", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "user update successful"),
			@ApiResponse(responseCode = "404", description = "user not found")
	})
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = uservice.fromDTO(objDto);
		obj.setId(id);
		obj  = uservice.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Delete By ID", description = "Delete User By ID", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "user successfully deleted"),
			@ApiResponse(responseCode = "404", description = "user not found")
	})
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		uservice.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Get Posts By User ID", description = "Get Posts By User ID", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "returns user post"),
			@ApiResponse(responseCode = "404", description = "post not found")
	})
	@GetMapping(path="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = uservice.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
