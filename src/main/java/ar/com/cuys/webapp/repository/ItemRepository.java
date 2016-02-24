package ar.com.cuys.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.entity.Blog;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByBlog(Blog blog, Pageable pageable);
	
	Item findByBlogAndLink(Blog blog, String link);
	
	Page<Item> findByBlog_External(Boolean external, Pageable pageable);

}
