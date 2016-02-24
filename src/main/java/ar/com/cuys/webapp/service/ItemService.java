package ar.com.cuys.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;	
	
	public List<Item> getItems(PageRequest pageRequest){
		return itemRepository.findAll(pageRequest).getContent();
	}
	
	public List<Item> getItemsByExternalBlog(Boolean external, PageRequest pageRequest){		
		return itemRepository.findByBlog_External(external, pageRequest).getContent();
	}
	
	
}
