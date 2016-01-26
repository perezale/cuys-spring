package ar.com.cuys.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
}
