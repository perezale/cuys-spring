package ar.com.cuys.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@ModelAttribute("post")
	public Post constructPost(){
		return new Post();
	}
	
	@RequestMapping("/aportes/agregar")
	public String publishPost(){
		return "publish-post";
	}
}
