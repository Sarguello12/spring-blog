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
    public String create(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String createForm(@ModelAttribute Post post){
        postDao.save(post);
        return "posts/index";
    }








    @GetMapping("/posts/{id}/edit")
    public String editPost(
            @PathVariable long id,
            Model model) {
        model.addAttribute("post", postDao.getById(id));

        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String updatePost(
            @ModelAttribute Post post) {

        postDao.save(post);
        return "redirect:/posts";
    }


    @PostMapping("/posts/delete")
    public String delete(@RequestParam("post_id") long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }











}
