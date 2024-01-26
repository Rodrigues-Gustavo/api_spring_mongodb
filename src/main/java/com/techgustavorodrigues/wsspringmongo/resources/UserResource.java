package com.techgustavorodrigues.wsspringmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techgustavorodrigues.wsspringmongo.domain.User;
import com.techgustavorodrigues.wsspringmongo.dto.UserDTO;
import com.techgustavorodrigues.wsspringmongo.services.UserService;

@RestController
@RequestMapping(path ="/users")
public class UserResource {
	
	@Autowired
	private UserService uservice;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = uservice.findAll();
		List<UserDTO> listDto = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
