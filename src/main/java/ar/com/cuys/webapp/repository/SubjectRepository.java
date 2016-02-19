package ar.com.cuys.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.Subject;

public interface SubjectRepository extends  JpaRepository<Subject, Integer>{

	List<Subject> findAllByOrderByTitleAsc();
	
	List<Subject> findByTitleIgnoreCaseContaining(String title);
	
	Subject findByTitle(String title);
	
	List<Subject> findByPosts(List<Post> posts, Pageable pageable);

}
