package ar.com.cuys.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cuys.webapp.entity.Subject;

public interface SubjectRepository extends  JpaRepository<Subject, Integer>{

	List<Subject> findAllByOrderByTitleAsc();
	
	Subject findByTitle(String title);

}
