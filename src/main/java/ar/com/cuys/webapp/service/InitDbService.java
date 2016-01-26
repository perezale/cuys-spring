package ar.com.cuys.webapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.Role;
import ar.com.cuys.webapp.entity.User;
import ar.com.cuys.webapp.repository.ItemRepository;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.RoleRepository;
import ar.com.cuys.webapp.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setPassword("admin");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Post postProbabilidad = new Post();
		postProbabilidad.setTitle("Final Febrero 2016");
		postProbabilidad.setMessage("Ultimo examen de probabilidad");
		postProbabilidad.setUser(userAdmin);
		postRepository.save(postProbabilidad);
		
		Item item1 = new Item();
		item1.setLink("http://www.comoustedyasabe.com.ar/datos/estadisticas-Final_09.jpg");
		item1.setPost(postProbabilidad);
		item1.setPublishedDate(new Date());
		item1.setTitle("Final Probabilidad Febrero 2016");
		itemRepository.save(item1);		
		
		Item item2 = new Item();
		item2.setLink("http://www.comoustedyasabe.com.ar/datos/estadisticas-Parcial(2008).jpg");
		item2.setPost(postProbabilidad);
		item2.setPublishedDate(new Date());
		item2.setTitle("Parcial 2008");
		itemRepository.save(item2);				
		
	}
	
}

