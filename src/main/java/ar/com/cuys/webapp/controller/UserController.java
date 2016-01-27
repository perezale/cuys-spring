package ar.com.cuys.webapp.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.cuys.webapp.entity.Post;
import ar.com.cuys.webapp.entity.User;
import ar.com.cuys.webapp.service.PostService;
import ar.com.cuys.webapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@ModelAttribute("user")
	public User constructUser(){
		return new User();
	}
	
	@ModelAttribute("post")
	public Post constructPost(){
		return new Post();
	}
	
	@RequestMapping("/users")
	public String users(Model model){
		model.addAttribute("users",userService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id){
		User user = userService.findOneWithPosts(id);		
		model.addAttribute("user",user);		
		return "user-detail";
	}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result){
		if(result.hasErrors()){
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal)
	{
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithPosts(name));
		return "user-detail";
	}
	
	@RequestMapping(path="/account", method=RequestMethod.POST)
	public String doAddPost(Model model, @Valid @ModelAttribute("post") Post post,BindingResult result, Principal principal){		
		if(result.hasErrors()){
			return account(model, principal);
		}
		String name = principal.getName();
		postService.save(post,name);
		return "redirect:/account.html";
	}
	
	@RequestMapping("post/remove/{id}")
	public String removePost(@PathVariable int id)	{
		Post post = postService.findOne(id);		
		postService.delete(post);
		return "redirect:/account.html";
	}
	
	@RequestMapping("users/remove/{id}")
	public String removeUser(@PathVariable int id){
		userService.delete(id);
		return "redirect:/users.html";
	}
}
