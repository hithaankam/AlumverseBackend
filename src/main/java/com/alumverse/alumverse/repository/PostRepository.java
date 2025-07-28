package com.alumverse.alumverse.repository;

import com.alumverse.alumverse.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}