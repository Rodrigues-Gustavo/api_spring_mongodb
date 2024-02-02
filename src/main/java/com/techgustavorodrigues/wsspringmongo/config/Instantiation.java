package com.techgustavorodrigues.wsspringmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.techgustavorodrigues.wsspringmongo.domain.Post;
import com.techgustavorodrigues.wsspringmongo.domain.User;
import com.techgustavorodrigues.wsspringmongo.dto.AuthorDTO;
import com.techgustavorodrigues.wsspringmongo.dto.CommentDTO;
import com.techgustavorodrigues.wsspringmongo.repository.IPostRepository;
import com.techgustavorodrigues.wsspringmongo.repository.IUserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private IUserRepository iuserRepository;
	
	@Autowired
	private IPostRepository ipostReposiroty;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		
		iuserRepository.deleteAll();
		ipostReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		iuserRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("29/01/2024"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("30/01/2024"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("02/02/2024"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("02/02/2024"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("02/02/2024"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		ipostReposiroty.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		iuserRepository.save(maria);
	}
}
