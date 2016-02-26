package ar.com.cuys.webapp.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.cuys.webapp.entity.Subject;
import ar.com.cuys.webapp.service.ItemService;
import ar.com.cuys.webapp.service.PostService;
import ar.com.cuys.webapp.service.SubjectService;

@Controller
public class IndexController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SubjectService subjectService;
	
	@ModelAttribute("subject")
	public Subject constructSubject(){
		return new Subject();
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("items", itemService.getItemsByExternalBlog(true,new PageRequest(0,4,Direction.DESC,"publishedDate")));
		model.addAttribute("news", itemService.getItemsByExternalBlog(false,new PageRequest(0,3,Direction.DESC,"publishedDate")));
		model.addAttribute("posts", postService.findAllWithSubjectsAndCategories(new PageRequest(0,20,Direction.DESC,"publishedDate")));		
		return "index";
	}
	
	@RequestMapping("/catedras")
	public String subjects(Model model){
		model.addAttribute("subjects", subjectService.getSubjects());
		return "subjects";
	}
	

	@RequestMapping(path="/catedras", method=RequestMethod.POST)
	public String doAddSubject(Model model, @Valid @ModelAttribute("subject") Subject subject,BindingResult result, Principal principal){		
		if(result.hasErrors()){
			return index(model);
		}
		subjectService.save(subject);
		return "redirect:/catedras.html";
	}
}
