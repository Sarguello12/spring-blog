package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "imagine your posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable int id){
        return "imagine viewing this post id: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "imagine viewing the form";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createForm(){
        return "imagine the create form";
    }
}
