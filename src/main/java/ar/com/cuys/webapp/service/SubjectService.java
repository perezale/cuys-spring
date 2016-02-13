package ar.com.cuys.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Subject;
import ar.com.cuys.webapp.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;	
	
	public List<Subject> getSubjects(){
		return subjectRepository.findAllByOrderByTitleAsc();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(Subject subject){							
		subjectRepository.save(subject);		
	}
}
