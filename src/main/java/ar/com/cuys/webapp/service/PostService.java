package ar.com.cuys.webapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.SubjectRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll(new PageRequest(0,20,Direction.DESC,"publishedDate")).getContent();
	}
	
	public List<Post> findAllWithSubjects(){
		List<Post> posts = postRepository.findAll(new PageRequest(0,20,Direction.DESC,"publishedDate")).getContent();
		for(Post p : posts){
			p.setSubjects(subjectRepository.findByPosts(Arrays.asList(new Post[]{p}), new PageRequest(0, 10,Direction.ASC,"title")));
		}
		return posts;
	}

	public List<Post> getCroppedPosts(){
		List<Post> content = postRepository.findAll(new PageRequest(0,20,Direction.DESC,"publishedDate")).getContent();
		for(Post p : content){
			//p.setMessage(StringUtils.);
		}
		return content;
	} 
}
