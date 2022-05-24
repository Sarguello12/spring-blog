package com.codeup.springblog;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseTest {

    @Autowired
    private PostRepository postDao;
    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder pen;

    @Autowired
    private MockMvc mvc;

    private User testUser;

    private HttpSession session;


    private void setUpTestUser() {
        testUser = userDao.findByUsername("testUser");
        if (testUser == null) {
            testUser = new User();
            testUser.setEmail("test@email.com");
            testUser.setPassword(pen.encode("password"));
            testUser.setUsername("testUser");
            testUser = userDao.save(testUser);
        }

    }

    private void setUpSession() throws Exception {
        session = mvc.perform(post("/login").with(csrf())
                        .param("username", testUser.getUsername())
                        .param("password", "password"))
                .andExpect(status().isFound())
                .andReturn()
                .getRequest()
                .getSession();

    }

//
//    Exercises
//    Create at least four integration tests for your Spring blog application.
//    We will be focused on CRUD functionality for posts so make sure that:
//
//    A list of posts can be shown by visiting: /posts.
//    A post page can be shown by visiting: /posts/{id}.
//    A post can be created by submitting all the required params through a HTTP POST request to /posts/create.
//    A test user has a valid HttpSession active before saving the post.
//    Bonus
//    A post can be updated by submitting a HTTP POST request to /posts/{id}/edit.
//    A post can be deleted by submitting a HTTP DELETE request to /posts/{id}/delete.


    @Before
    public void setUp() throws Exception {
        setUpTestUser();
        setUpSession();
    }

    @Test
    public void testContext() {
        assertNotNull(mvc);
        assertNotNull(session);
    }

    // test post creation
    @Test
    public void testPostCreation() throws Exception {
        mvc.perform(
                        post("/posts/create")
                                .with(csrf())
                                .session((MockHttpSession) session)
                                .flashAttr("post", new Post("xxTestTitlexx", "body text"))
                ).andExpect(status().is3xxRedirection())
                .andDo(print());

        Post p = postDao.findFirstByTitle("xxTestTitlexx");
        postDao.deleteById(p.getId());

    }

    // test post show

    @Test
    public void testPostShow() throws Exception {
        Post post = postDao.findAll().get(0);

        mvc.perform(
                        get("/posts/" + post.getId())
                ).andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                post.getTitle()
                        )))
                .andExpect(content().string(
                        containsString(
                                post.getBody()
                        )
                ));

    }

    @Test
    // test post index
    public void testPostIndex() throws Exception {
        Post post = postDao.findAll().get(0);

        mvc.perform(
                        get("/posts")
                ).andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                post.getTitle()
                        )))
                .andExpect(content().string(
                        containsString(
                                post.getBody()
                        )));
    }


    // test post edit
    @Test
    public void testPostEdit() throws Exception {

        // need an existing item to edit
        Post post = new Post("New Post", "I am the body", testUser);

        post = postDao.save(post);

        // new values to change the current values to

        post.setTitle("Updated Title!");
        post.setBody("Updated Body!!!");

        mvc.perform(post("/posts/edit")
                .with(csrf())
                .session((MockHttpSession) session)
                .flashAttr("post", post)
        ).andExpect(status().is3xxRedirection());


        // test if the values have changed
        mvc.perform(
                        get("/posts/" + post.getId())
                ).andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                post.getTitle()
                        )))
                .andExpect(content().string(
                        containsString(
                                post.getBody()
                        )
                ));

        // delete the new post we created.
        postDao.deleteById(post.getId());
    }


    // test post delete
    @Test
    public void testPostDelete() throws Exception {


        // make a post to delete
        // need an existing item to delete
        Post post = new Post("New Post", "I am the body", testUser);



        post = postDao.save(post);

        Long id = post.getId();
//        System.out.println("post.toString() = " + post.toString());

        // post delete:f test that the post doesent exist
        mvc.perform(
                post("/posts/delete")
                        .with(csrf())
                        .session((MockHttpSession) session)
                        .param("post_id", String.valueOf(id))
        ).andExpect(status().is3xxRedirection());


        assertEquals(postDao.existsById(post.getId()), false);
    }

}
