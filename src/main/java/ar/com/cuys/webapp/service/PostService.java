package ar.com.cuys.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.User;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public void save(Post post, String name) {
		User user = userRepository.findByName(name);
		post.setUser(user);
		postRepository.save(post);		
		
	}

	@PreAuthorize("#post.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("post") Post post){
		postRepository.delete(post);
	}

	public Post findOne(int id) {
		return postRepository.findOne(id);
	}

	
}
