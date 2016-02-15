package ar.com.cuys.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Blog;
import ar.com.cuys.webapp.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
