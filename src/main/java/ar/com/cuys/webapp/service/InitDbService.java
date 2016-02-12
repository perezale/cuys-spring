package ar.com.cuys.webapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import ar.com.cuys.legacy.entity.Catedra;
import ar.com.cuys.legacy.entity.Categoria;
import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.Role;
import ar.com.cuys.webapp.entity.User;
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
		
		Post postProbabilidad = new Post();
		postProbabilidad.setTitle("Twitter");
		postProbabilidad.setUrl("https://queryfeed.net/tw?q=%40cuys");
		postProbabilidad.setUser(userAdmin);
		postRepository.save(postProbabilidad);		
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Catedra>> rateResponse =
		        restTemplate.exchange("http://www.comoustedyasabe.com.ar/api/catedras",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Catedra>>() {
		            });
		List<Catedra> catedras = rateResponse.getBody();
		ResponseEntity<List<Categoria>> categoriasResponse =
		        restTemplate.exchange("http://www.comoustedyasabe.com.ar/api/categorias",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Categoria>>() {
		            });
		List<Categoria> categorias = categoriasResponse.getBody();
		System.out.println("Catedras");
		for(Catedra s : catedras){
			System.out.println(s.getTitle());
		}
		System.out.println("Categorias");
		for(Categoria c : categorias){
			System.out.println(c.getNombre());
		}
	}
	
}

