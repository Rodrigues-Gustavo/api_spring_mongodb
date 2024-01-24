package com.techgustavorodrigues.wsspringmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techgustavorodrigues.wsspringmongo.domain.User;

@RestController
@RequestMapping(path ="/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User Dane = new User("1", "Dane Doe", "DaneDoe@gmail.com");
		User John = new User("2", "John Doe", "JohnDoe@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(Dane, John));
		return ResponseEntity.ok().body(list);
	}
}
