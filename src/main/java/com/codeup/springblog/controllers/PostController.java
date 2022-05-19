package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String posts(Model model){
//        List<Post> posts = new ArrayList<>();
//
//        Post post1 = new Post("title", "this is a ver informative body.");
//        Post post2 = new Post("title", "you should really read this");
//
//        posts.add(post1);
//        posts.add(post2);

        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
        Post post = postDao.getById(id);
        User user = userDao.getById(post.getUser().getId());
        model.addAttribute("post", post);
        model.addAttribute("user", user);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createForm(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @RequestParam(name = "user_id")long user_id){

        User user = userDao.getById(user_id);
        Post post = new Post(title, body, user);
        postDao.save(post);
        return "redirect:/posts";
    }
}
