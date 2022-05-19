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
    public String create(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createForm(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post post = new Post(title, body);
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
    public String addPost(){
        return "posts/add";
    }

    @PostMapping("/add")
    public String addImage(@RequestParam(name = "image_title") String imgTitle, @RequestParam(name = "url") String url, @RequestParam(name = "post_id") Long postId){

        Post post = postDao.getById(postId);
        PostImage postImage = new PostImage(imgTitle, url, post);

        post.getPostImageList().add(postImage);
        postDao.save(post);
//        postImageDao.save(postImage);
        return "redirect:/posts/details";
    }

}
