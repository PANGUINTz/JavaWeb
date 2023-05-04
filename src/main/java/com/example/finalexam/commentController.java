package com.example.finalexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class commentController {
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private movieRepository movieRepo;

    @GetMapping("/userAll")
    List<Users> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("/commentAll")
    List<Comments> getCommentAll() {
        return commentRepo.findAll();
    }

    @GetMapping("/comment")
    List<Comments> FindBookByIsbn(@RequestParam(name = "name") String name) {
        return commentRepo.findCommentsByNameContainingIgnoreCase(name);
    }

    @GetMapping("/movieAll")
    List<Movie> getMovieAll() {
        return movieRepo.findAll();
    }

    @PostMapping("/newComments")
    ResponseEntity<Comments> addNewComments(@RequestBody Comments comments) {
        Comments c = commentRepo.save(comments);
        return new ResponseEntity<Comments>(c, HttpStatus.CREATED);
    }

}
