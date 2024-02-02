package com.techgustavorodrigues.wsspringmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techgustavorodrigues.wsspringmongo.domain.Post;
import com.techgustavorodrigues.wsspringmongo.services.PostService;

@RestController
@RequestMapping(path ="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	

	@GetMapping(path = "/{id}")
	public ResponseEntity<Post> findbyId(@PathVariable String id) {
	    Post obj = service.findById(id);
//	    service.save(obj); 
		return ResponseEntity.ok().body(obj);
	}
}
