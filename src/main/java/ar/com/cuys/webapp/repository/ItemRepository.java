package ar.com.cuys.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.entity.Post;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByPost(Post post, Pageable pageable);

}
