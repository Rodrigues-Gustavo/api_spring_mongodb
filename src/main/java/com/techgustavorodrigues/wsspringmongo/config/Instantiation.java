package com.techgustavorodrigues.wsspringmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.techgustavorodrigues.wsspringmongo.domain.User;
import com.techgustavorodrigues.wsspringmongo.repository.IUserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private IUserRepository iuserRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		iuserRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		iuserRepository.saveAll(Arrays.asList(maria, alex, bob));
	}
}
