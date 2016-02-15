package ar.com.cuys.webapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.cuys.webapp.entity.Blog;
import ar.com.cuys.webapp.entity.Role;
import ar.com.cuys.webapp.entity.User;
import ar.com.cuys.webapp.repository.BlogRepository;
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
	private BlogRepository blogRepository;	
	
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog blogExactas = new Blog();
		blogExactas.setTitle("Twitter");
		blogExactas.setUrl("http://www.exa.unicen.edu.ar/es/noticias/rss");
		blogExactas.setUser(userAdmin);
		blogRepository.save(blogExactas);		
		
		
	}
	
}

