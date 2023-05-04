package com.example.finalexam;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comments, String> {

    List<Comments> findCommentsByNameContainingIgnoreCase(String name);


}
