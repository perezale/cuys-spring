package ar.com.cuys.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> getPosts(){
		return postRepository.findAll(new PageRequest(0,20,Direction.DESC,"publishedDate")).getContent();
	}
}
