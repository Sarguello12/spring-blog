package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    @GetMapping
    @ResponseBody
    public String allAlbums(){
        return "Imagine viewing all the albums";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String showOneAlbum(@PathVariable long id){
        return "Imagine viewing one album with the ID: " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String createAlbumForm(){
        return "Imagine viewing the form to create an album";
    }

    @PostMapping
    @ResponseBody
    public String createAlbum(){
        return "imagine creating an album";
    }
}
