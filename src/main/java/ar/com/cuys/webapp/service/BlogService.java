package ar.com.cuys.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Blog;
import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.entity.User;
import ar.com.cuys.webapp.exception.RssException;
import ar.com.cuys.webapp.repository.BlogRepository;
import ar.com.cuys.webapp.repository.ItemRepository;
import ar.com.cuys.webapp.repository.UserRepository;

@Service
public class BlogService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	public void saveItems(Blog blog){		
		List<Item> items;
		try {
			items = rssService.getItems(blog.getUrl());
			for(Item item : items){
				Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
				if(savedItem == null){
					item.setBlog(blog);
					itemRepository.save(item);
				}				
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
		
	}
	
	//@Scheduled(fixedDelay=3600000)
	public void reloadBlogs(){
		List<Blog> blogs = blogRepository.findAll();
		for(Blog blog : blogs){
			saveItems(blog);
		}
	}
	
	public void save(Blog blog, String name) {
		User user = userRepository.findByNameIgnoreCase(name);
		blog.setUser(user);
		blogRepository.save(blog);		
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog){
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

}
