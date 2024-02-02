package com.techgustavorodrigues.wsspringmongo.services;


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
	
//    public Post save(Post post) {
//    	System.out.print(post);
//        return repo.save(post);
//    }
}
