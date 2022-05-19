USE spring_blog_db;


INSERT INTO posts (body, title, post_details_id) VALUES ('post1', 'wow post1 is so interesting', 1),
                                       ('post2', 'wow post2 is definably the best post', 2),
                                       ('post3', 'eh post3 is alright', 3);

INSERT INTO post_details (history_of_post, is_awesome, topic_description) VALUES
                                                                                ('today', TRUE, 'interesting'),
                                                                                ('yesterday', TRUE, 'sensational'),
                                                                                ('monday', FALSE, 'eh');

INSERT INTO post_images(image_title, url, post_id) VALUES ('cat1','https://www.thesprucepets.com/thmb/ih8Z-ifZlL9h9f6_1kmgZi3nNmQ=/3000x2178/filters:fill(auto,1)/cats-118824989-584d83d33df78c491e316973.jpg', 7),
                                                          ('cat2', 'https://www.top13.net/wp-content/uploads/2017/03/stray-cats-portraits-3.jpg', 8),
                                                          ('cat3', 'https://assets.rebelmouse.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpbWFnZSI6Imh0dHBzOi8vYXNzZXRzLnJibC5tcy80MTQwNzQzL29yaWdpbi5qcGciLCJleHBpcmVzX2F0IjoxNjQ2NDI2MDUzfQ.OvxqNV-y60Z4ypArs_n5MoAYNzC39Pxm1AfrW7cPr6I/img.jpg?width=980', 9),
                                                          ('cat4', 'https://i0.wp.com/themindcircle.com/wp-content/uploads/2019/11/siamese-cats-4.jpg', 9);

