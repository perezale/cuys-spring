package ar.com.cuys.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Category;
import ar.com.cuys.webapp.entity.Post;

public interface CategoryRepository extends  JpaRepository<Category, Integer>{

	public List<Category> findByPosts(List<Post> posts, Pageable pageable);

	public Category findByName(String name);
}
