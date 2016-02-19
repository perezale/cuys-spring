package ar.com.cuys.webapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.com.cuys.legacy.entity.Aporte;
import ar.com.cuys.legacy.entity.Catedra;
import ar.com.cuys.legacy.entity.Categoria;
import ar.com.cuys.webapp.entity.Attachment;
import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.Subject;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.SubjectRepository;

@Service
public class LegacyIntegrationService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	private RestTemplate restTemplate = new RestTemplate();	
	
	@PostConstruct
	public void init(){
		List<Catedra> catedras = this.getCatedras();
		for(Catedra catedra : catedras){
			Subject subject = new Subject();
			subject.setTitle(catedra.getTitle());
			subject.setUrlTitle(catedra.getUrl_title());
			subject.setReferenceLink(catedra.getRef_link());
			subjectRepository.save(subject);
		}
		
		
		List<Aporte> aportes = this.getAportes();
		for(Aporte aporte : aportes){
			Attachment attachment = new Attachment();
			attachment.setLink(aporte.getPath());		
			
			Post post = new Post();
			post.setTitle(aporte.getTitle());
			post.setPublishedDate(new Date());
			post.setSubjects(this.findSubjects(new int[]{aporte.getCatedra()}, catedras));
			post.setAttachments(Arrays.asList(new Attachment[]{attachment}));
			postRepository.save(post);
			
		}
	}
	
	private List<Subject> findSubjects(int[] busqueda, List<Catedra> catedras) {
		List<Subject> output = new ArrayList<Subject>();		
		for(int catedra : busqueda){
			for(Catedra c : catedras){
				if(c.getId() == catedra){
					output.add(subjectRepository.findByTitle(c.getTitle()));
				}
			}
		}
		return output;		
	}

	public List<Categoria> getCategorias(){
		ResponseEntity<List<Categoria>> categoriasResponse =
		        restTemplate.exchange("http://www.comoustedyasabe.com.ar/api/categorias",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Categoria>>() {
		            });
		return categoriasResponse.getBody();
	}
		
	public List<Catedra> getCatedras(){
		ResponseEntity<List<Catedra>> restResponse =
		        restTemplate.exchange("http://www.comoustedyasabe.com.ar/api/catedras",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Catedra>>() {
		            });
		return restResponse.getBody();
	}
	
	public List<Aporte> getAportes(){
		ResponseEntity<List<Aporte>> restResponse =
		        restTemplate.exchange("http://www.comoustedyasabe.com.ar/api/aportes",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Aporte>>() {
		            });
		return restResponse.getBody();
	}	
	
	public static void main(String[] args){
		LegacyIntegrationService service = new LegacyIntegrationService();
		for(Catedra catedra : service.getCatedras()){
			System.out.println(catedra.toString());
		}
		for(Categoria categoria : service.getCategorias()){
			System.out.println(categoria.toString());
		}
		for(Aporte aporte : service.getAportes()){
			System.out.println(aporte.toString());
		}

	}
}
