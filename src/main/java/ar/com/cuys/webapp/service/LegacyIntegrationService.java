package ar.com.cuys.webapp.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.com.cuys.legacy.entity.Catedra;
import ar.com.cuys.legacy.entity.Categoria;
import ar.com.cuys.webapp.entity.Subject;
import ar.com.cuys.webapp.repository.SubjectRepository;

@Service
public class LegacyIntegrationService {
	
	private static Logger logger = Logger.getRootLogger(); 

	@Autowired
	private SubjectRepository subjectRepository;
	
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
	
	public static void main(String[] args){
		LegacyIntegrationService service = new LegacyIntegrationService();
		for(Catedra catedra : service.getCatedras()){
			logger.info(catedra.toString());
		}
		for(Categoria categoria : service.getCategorias()){
			logger.info(categoria.toString());
		}
	}
}
