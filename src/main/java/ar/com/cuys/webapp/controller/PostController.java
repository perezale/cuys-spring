package ar.com.cuys.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.service.PostService;
import ar.com.cuys.webapp.service.SubjectService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private SubjectService subjectService;
	
	@ModelAttribute("post")
	public Post constructPost(){
		return new Post();
	}
	
	@RequestMapping("/aportes/publicar")
	public String publishPost(Model model){
		model.addAttribute("subjects", subjectService.getSubjects());
		return "publish-post";
	}
}
