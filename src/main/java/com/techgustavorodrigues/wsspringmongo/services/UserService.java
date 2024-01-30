package com.techgustavorodrigues.wsspringmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgustavorodrigues.wsspringmongo.domain.User;
import com.techgustavorodrigues.wsspringmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return iurepo.insert(obj);
	}
	
	public void delete(String id) {
		User userId = findById(id);
		iurepo.delete(userId);
	}
	
	public User update(User obj) {
		User newObj = iurepo.findById(obj.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));
		
		updateData(newObj, obj);
		return iurepo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
