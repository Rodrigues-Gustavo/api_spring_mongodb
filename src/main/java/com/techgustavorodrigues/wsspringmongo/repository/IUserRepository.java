package com.techgustavorodrigues.wsspringmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.techgustavorodrigues.wsspringmongo.domain.User;

@Repository
public interface IUserRepository extends MongoRepository<User, String>{
}