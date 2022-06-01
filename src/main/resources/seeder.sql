USE spring_blog_db;
DROP TABLE post_images;
DROP TABLE post_tag;
DROP TABLE post_details;

ALTER TABLE posts
DROP CONSTRAINT FK1pxc57ws5rs02evi8knnlp968;

ALTER TABLE posts
DROP COLUMN post_details_id;

