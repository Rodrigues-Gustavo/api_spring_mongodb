package com.techgustavorodrigues.wsspringmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techgustavorodrigues.wsspringmongo.domain.Post;
import com.techgustavorodrigues.wsspringmongo.resources.util.URL;
import com.techgustavorodrigues.wsspringmongo.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@Operation(summary = "Get Post By ID", description = "Get Post By  ID", tags = "Posts")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "returns post"),
			@ApiResponse(responseCode = "404", description = "post not found")
	})
	@GetMapping(path="/{id}")
	public ResponseEntity<Post> findbyId(@PathVariable String id) {
	    Post obj = service.findById(id); 
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Get Post by Title Search", description = "Get Post by Title Search", tags = "Posts")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "returns title search"),
			@ApiResponse(responseCode = "404", description = "post title not found")
	})
	@GetMapping(path="/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue ="") String text) {
	    text = URL.decodeParam(text);
	    List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Get Post by Full Search", description = "Return Post Method By Complete Search", tags = "Posts")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "return complete search"),
			@ApiResponse(responseCode = "404", description = "no searches were found")
	})
	@GetMapping(path="/fullsearch")
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
