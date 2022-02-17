package com.jpa.study.repository;

import com.jpa.study.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

    @Autowired private PostRepository postRepository;
    @PersistenceContext private EntityManager em;

    @Test
    void save() throws Exception {
        Post post = new Post();
        post.setTitle("JPA");
        Post savedPost = postRepository.save(post);          // persist

        assertThat(em.contains(post)).isTrue();
        assertThat(em.contains(savedPost)).isTrue();
        assertThat(savedPost == post);

        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("Hibernate");
        Post updatedPost = postRepository.save(postUpdate); // merge

        assertThat(em.contains(updatedPost)).isTrue();
        assertThat(em.contains(postUpdate)).isFalse();
        assertThat(updatedPost == postUpdate);

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}