--liquibase formatted sql
--changeset uh635c:step1
CREATE TABLE writers (
                         id int NOT NULL,
                         name varchar(255) NOT NULL,
                         PRIMARY KEY (id)
);

--changeset uh635c:step2 splitStatements:true endDelimiter:;
INSERT INTO writers VALUES(1, 'writer1');
INSERT INTO writers VALUES(2, 'writer2');
INSERT INTO writers VALUES(3, 'writer3');

--changeset uh635c:step3
CREATE TABLE tags (
                      id int NOT NULL,
                      name varchar(255) NOT NULL,
                      PRIMARY KEY (id)
);

--changeset uh635c:step4 splitStatements:true endDelimiter:;
INSERT INTO tags VALUES(1, 'tag1');
INSERT INTO tags VALUES(2, 'tag2');
INSERT INTO tags VALUES(3, 'tag3');

--changeset uh635c:step5
CREATE TABLE posts (
                       id int NOT NULL,
                       content text NOT NULL,
                       writer_id int NOT NULL,
                       tag_status varchar(255) NOT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT `posts_writers` FOREIGN KEY (writer_id) REFERENCES writers (id) ON DELETE CASCADE
);

--changeset uh635c:step6 splitStatements:true endDelimiter:;
INSERT INTO posts VALUES(1, 'content1', 1, 'ACTIVE');
INSERT INTO posts VALUES(2, 'content2', 2, 'ACTIVE');
INSERT INTO posts VALUES(3, 'content3', 3, 'ACTIVE');

--changeset uh635c:step7
CREATE TABLE posts_tags (
                            post_id int DEFAULT NULL,
                            tag_id int DEFAULT NULL,
                            CONSTRAINT posts_tags_ibfk_1 FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
                            CONSTRAINT posts_tags_ibfk_2 FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE
);

--changeset uh635c:step8 splitStatements:true endDelimiter:;
INSERT INTO posts_tags VALUES(1, 1);
INSERT INTO posts_tags VALUES(1, 3);
INSERT INTO posts_tags VALUES(2, 2);
INSERT INTO posts_tags VALUES(2, 3);
INSERT INTO posts_tags VALUES(3, 1);
INSERT INTO posts_tags VALUES(3, 2);