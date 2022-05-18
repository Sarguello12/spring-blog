USE spring_blog_db;


INSERT INTO posts (body, title, post_details_id) VALUES ('post1', 'wow post1 is so interesting', 1),
                                       ('post2', 'wow post2 is definably the best post', 2),
                                       ('post3', 'eh post3 is alright', 3);

INSERT INTO post_details (history_of_post, is_awesome, topic_description) VALUES
                                                                                ('today', TRUE, 'interesting'),
                                                                                ('yesterday', TRUE, 'sensational'),
                                                                                ('monday', FALSE, 'eh');

