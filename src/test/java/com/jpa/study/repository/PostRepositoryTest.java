package com.jpa.study.repository;

import com.jpa.study.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

    @Test
    void findByTitleStartsWith() throws Exception {
        savePost();

        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void findByTitle() throws Exception {
        savePost();

        List<Post> all = postRepository.findByTitle("Spring", Sort.by("title"));
        assertThat(all.size()).isEqualTo(1);
    }
    
    @Test
    void updateTitleWrong() throws Exception {
        Post spring = savePost();

        String Hibernate = "Hibernate";
        int update = postRepository.updateTitle(Hibernate, spring.getId());
        assertThat(update).isEqualTo(1);

        Optional<Post> byId = postRepository.findById(spring.getId());
        assertThat(byId.get().getTitle()).isEqualTo(Hibernate);
    }

    @Test
    void updateTitle() throws Exception {
        Post spring = savePost();
        spring.setTitle("Hibernate");

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("Hibernate");

    }

    private Post savePost() {
        Post post = new Post();
        post.setTitle("Spring");
        return postRepository.save(post); // persist
    }
}