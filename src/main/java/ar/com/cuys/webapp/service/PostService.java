package ar.com.cuys.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.User;
import ar.com.cuys.webapp.exception.RssException;
import ar.com.cuys.webapp.repository.ItemRepository;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	public void saveItems(Post post){		
		List<Item> items;
		try {
			items = rssService.getItems(post.getUrl());
			for(Item item : items){
				Item savedItem = itemRepository.findByPostAndLink(post, item.getLink());
				if(savedItem == null){
					item.setPost(post);
					itemRepository.save(item);
				}				
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
		
	}
	
	//@Scheduled(fixedDelay=3600000)
	public void reloadPosts(){
		List<Post> posts = postRepository.findAll();
		for(Post post : posts){
			saveItems(post);
		}
	}
	
	public void save(Post post, String name) {
		User user = userRepository.findByName(name);
		post.setUser(user);
		postRepository.save(post);		
		saveItems(post);
	}

	@PreAuthorize("#post.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("post") Post post){
		postRepository.delete(post);
	}

	public Post findOne(int id) {
		return postRepository.findOne(id);
	}

}
