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
import ar.com.cuys.legacy.entity.BlogPost;
import ar.com.cuys.legacy.entity.Catedra;
import ar.com.cuys.legacy.entity.Categoria;
import ar.com.cuys.webapp.entity.Attachment;
import ar.com.cuys.webapp.entity.Blog;
import ar.com.cuys.webapp.entity.Category;
import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.Subject;
import ar.com.cuys.webapp.repository.BlogRepository;
import ar.com.cuys.webapp.repository.CategoryRepository;
import ar.com.cuys.webapp.repository.ItemRepository;
import ar.com.cuys.webapp.repository.PostRepository;
import ar.com.cuys.webapp.repository.SubjectRepository;

@Service
public class LegacyIntegrationService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;	
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	@PostConstruct
	public void init(){
		List<Catedra> catedras = this.getCatedras();
		List<Categoria> categorias = this.getCategorias();
		List<Aporte> aportes = this.getAportes();
		List<BlogPost> blogposts = this.getBlogPosts();
		
		for(Catedra catedra : catedras){
			Subject subject = new Subject();
			subject.setTitle(catedra.getTitle());
			subject.setUrlTitle(catedra.getUrl_title());
			subject.setReferenceLink(catedra.getRef_link());
			subjectRepository.save(subject);
		}
		
		for(Categoria categoria: categorias){
			Category category = new Category();
			category.setName(categoria.getNombre());						
			categoryRepository.save(category);
		}
					
		for(Aporte aporte : aportes){
			Attachment attachment = new Attachment();
			attachment.setLink(aporte.getPath());		
			
			Post post = new Post();
			post.setTitle(aporte.getTitle());
			post.setPublishedDate(new Date());
			post.setSubjects(this.findSubjects(new int[]{aporte.getCatedra()}, catedras));
			List<Category> findCategories = this.findCategories(aporte.getCategorias(), categorias);			
			post.setCategories(findCategories);
			
			post.setAttachments(Arrays.asList(new Attachment[]{attachment}));
			postRepository.save(post);		
		}
		
		// Blog
		Blog blog = new Blog();
		blog.setTitle("CUYS");
		blog.setExternal(false);	
		blog = blogRepository.save(blog);
				
		for(BlogPost post : blogposts){
			Item item = new Item();
			item.setBlog(blog);
			item.setTitle(post.getTitle());
			item.setPublishedDate(post.getEntry_date());
			item.setThumbnail(post.getThumbnail());
			item.setDescription(post.getEntrada());			
			//item.setLink(post.getUrl_title());
			itemRepository.save(item);			
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
	
	private List<Category> findCategories(List<Integer> busqueda, List<Categoria> categorias) {
		List<Category> output = new ArrayList<Category>();		
		for(int categoria : busqueda){
			for(Categoria c : categorias){
				if(c.getId_categoria() == categoria){
					output.add(categoryRepository.findByName(c.getNombre()));
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
	
	public List<BlogPost> getBlogPosts(){
		ResponseEntity<List<BlogPost>> restResponse =
		        restTemplate.exchange("http://www.comoustedyasabe.com.ar/api/blog",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<BlogPost>>() {
		            });
		return restResponse.getBody();
	}	
	
	public static void main(String[] args){
		LegacyIntegrationService service = new LegacyIntegrationService();
//		for(Catedra catedra : service.getCatedras()){
//			System.out.println(catedra.toString());
//		}
//		for(Categoria categoria : service.getCategorias()){
//			System.out.println(categoria.toString());
//		}
//		for(Aporte aporte : service.getAportes()){
//			System.out.println(aporte.toString());
//		}
		for(BlogPost post : service.getBlogPosts()){
			System.out.println(post.toString());
		}
		
	}
}
