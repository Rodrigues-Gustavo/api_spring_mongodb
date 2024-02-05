package com.techgustavorodrigues.wsspringmongo.services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgustavorodrigues.wsspringmongo.domain.Post;
import com.techgustavorodrigues.wsspringmongo.repository.IPostRepository;
import com.techgustavorodrigues.wsspringmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private IPostRepository repo;
	
	public Post findById(String id) {
		return repo.findById(id)
	            .orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
