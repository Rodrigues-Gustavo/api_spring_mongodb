package com.techgustavorodrigues.wsspringmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.techgustavorodrigues.wsspringmongo.domain.Post;

@Repository
public interface IPostRepository extends MongoRepository<Post, String>{
}