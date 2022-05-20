package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.repositories.PostImagesRepository;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final PostImagesRepository postImageDao;

    public PostController(PostRepository postDao, PostImagesRepository postImageDao){
        this.postDao = postDao;
        this.postImageDao = postImageDao;
    }

    @GetMapping("/posts")
    public String posts(Model model){

        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable int id, Model model){
        Post post = new Post("one cool post", "this is a cool post about a cool subject");
        model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createForm(@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/details")
    public String viewDetails(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/details";
    }









    @GetMapping("/posts/add")
    public String addPost(Model model){
        model.addAttribute("postImage", new PostImage());
        return "posts/add";
    }

    @PostMapping("/add")
    public String addImage(@ModelAttribute PostImage postImage){

        Post post = postDao.getById(postImage.getPost().getId());

        post.getPostImageList().add(postImage);
        postDao.save(post);
//        postImageDao.save(postImage);
        return "redirect:/posts/details";
    }

}
