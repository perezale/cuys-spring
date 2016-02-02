package ar.com.cuys.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.cuys.webapp.service.ItemService;

@Controller
public class IndexController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("items", itemService.getItems());
		return "index";
	}

}
