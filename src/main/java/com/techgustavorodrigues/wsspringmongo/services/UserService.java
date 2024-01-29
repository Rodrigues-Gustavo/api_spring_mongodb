package com.techgustavorodrigues.wsspringmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgustavorodrigues.wsspringmongo.domain.User;
import com.techgustavorodrigues.wsspringmongo.repository.IUserRepository;
import com.techgustavorodrigues.wsspringmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private IUserRepository iurepo;
	
	public List<User> findAll() {
		return iurepo.findAll();	
	}
	
	public User findById(String id) {		
	    return iurepo.findById(id)
	            .orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));
	}
}
