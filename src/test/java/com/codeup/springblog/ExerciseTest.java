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

    @Test
    public void testPostCreation() throws Exception {
        mvc.perform(
                        post("/posts/create")
                                .with(csrf())
                                .session((MockHttpSession) session)
                                .flashAttr("post", new Post("xxTestTitlexx", "body text"))
                ).andExpect(status()
                        .is3xxRedirection())
                .andDo(print());

        Post p = postDao.findFirstByTitle("xxTestTitlexx");
        postDao.deleteById(p.getId());

    }

    @Test
    public void testPostShow() throws Exception {
        Post post = postDao.findAll().get(0);

        mvc.perform(get("/posts/" + post.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                post.getTitle()
                        )))
                .andExpect(content().string(
                        containsString(
                                post.getBody()
                        )));
    }

    @Test
    public void testPostIndex() throws Exception {
        Post post = postDao.findAll().get(0);

        mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                post.getTitle()
                        )))
                .andExpect(content().string(
                        containsString(
                                post.getBody()
                        )));
    }

    @Test
    public void testPostEdit() throws Exception {
        Post post = new Post("New Post", "I am the body", testUser);
        post = postDao.save(post);

        post.setTitle("Updated Title!");
        post.setBody("Updated Body!!!");

        mvc.perform(post("/posts/edit")
                .with(csrf())
                .session((MockHttpSession) session)
                .flashAttr("post", post))
                .andExpect(status()
                        .is3xxRedirection());

        mvc.perform(get("/posts/" + post.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                post.getTitle()
                        )))
                .andExpect(content().string(
                        containsString(
                                post.getBody()
                        )));
        postDao.deleteById(post.getId());
    }

    @Test
    public void testPostDelete() throws Exception {
        Post post = new Post("New Post", "I am the body", testUser);
        post = postDao.save(post);
        Long id = post.getId();

        mvc.perform(
                post("/posts/delete")
                        .with(csrf())
                        .session((MockHttpSession) session)
                        .param("post_id", String.valueOf(id)))
                .andExpect(status()
                        .is3xxRedirection());

        assertFalse(postDao.existsById(post.getId()));
    }
}
