package ar.com.cuys.webapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.repository.CategoryRepository;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.SubjectRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll(new PageRequest(0,20,Direction.DESC,"publishedDate")).getContent();
	}
	
	public List<Post> findAllWithSubjectsAndCategories(PageRequest pageRequest){
		List<Post> posts = postRepository.findAll(pageRequest).getContent();
		for(Post p : posts){
			List<Post> list = Arrays.asList(new Post[]{p});
			p.setSubjects(subjectRepository.findByPosts(list, new PageRequest(0, 10,Direction.ASC,"title")));
			p.setCategories(categoryRepository.findByPosts(list, new PageRequest(0, 10,Direction.ASC,"name")));
		}
		return posts;
	}

}
