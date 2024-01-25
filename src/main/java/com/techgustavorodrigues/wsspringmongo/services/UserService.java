package com.techgustavorodrigues.wsspringmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgustavorodrigues.wsspringmongo.domain.User;
import com.techgustavorodrigues.wsspringmongo.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository iurepo;
	
	public List<User> findAll() {
		return iurepo.findAll();	
	}
}
