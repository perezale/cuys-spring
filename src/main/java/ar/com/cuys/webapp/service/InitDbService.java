package ar.com.cuys.webapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.cuys.webapp.entity.Blog;
import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.Role;
import ar.com.cuys.webapp.entity.User;
import ar.com.cuys.webapp.repository.BlogRepository;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.RoleRepository;
import ar.com.cuys.webapp.repository.SubjectRepository;
import ar.com.cuys.webapp.repository.UserRepository;

@Transactional
@Service
@Profile("dev")
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;	
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;	
	
	@Autowired
	private LegacyIntegrationService legacyService;
	
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
		blogExactas.setTitle("Exactas");
		blogExactas.setUrl("http://www.exa.unicen.edu.ar/es/noticias/rss");
		blogExactas.setUser(userAdmin);
		blogRepository.save(blogExactas);		
		
		List<Post> posts = postRepository.findAll();
		for(Post p : posts){
			p.setUser(userAdmin);
			postRepository.save(p);
		}
						
	}
	
}

