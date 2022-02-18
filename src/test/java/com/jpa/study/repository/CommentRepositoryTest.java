package com.jpa.study.repository;

import com.jpa.study.domain.Comment;
import com.jpa.study.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Autowired PostRepository postRepository;

    @Test
    void getComment() throws Exception {
        commentRepository.getById(1l);

        System.out.println("=====================");

        commentRepository.findById(1l);
    }

    @Test
    void getCommentProjections1() throws Exception {
        Post post = new Post();
        post.setTitle("JPA");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("spring data jpa projection");
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach(c -> {
            System.out.println("===========");
            System.out.println(c.getComment());
        });
    }

}