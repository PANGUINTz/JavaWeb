package com.example.finalexam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class mainController {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private movieRepository movieRepo;

    @GetMapping("/")
    String getAll(Model model) {
        model.addAttribute("comments");
        return "main";
    }

    @GetMapping("/movieLists")
    String getMovieAll(Model model) {
        model.addAttribute("movies");
        return "MoviePage";
    }

    @GetMapping("/movieName")
    String FindMovieByTitle(@RequestParam(name = "title") String title, Model model) {
        model.addAttribute("movies", movieRepo.findMovieByTitleIsContainingIgnoreCase(title));
        model.addAttribute("title", title);
        return "MoviePage";
    }

    @GetMapping("/comments")
    String FindBookByIsbn(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("comments", commentRepo.findCommentsByNameContainingIgnoreCase(name));
        model.addAttribute("name", name);
        return "main";
    }

    @GetMapping("/addcom")
    String addRestaurant(Model model) {
        model.addAttribute("comments", new Comments());
        return "addComment";
    }

    @PostMapping("/addcom")
    String submitRest(Comments comments, Model model) {
        commentRepo.save(comments);
        model.addAttribute("comments", commentRepo.findAll());
        return "main";
    }

}
