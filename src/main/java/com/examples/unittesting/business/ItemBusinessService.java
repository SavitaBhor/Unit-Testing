package com.examples.unittesting.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examples.unittesting.data.ItemRepository;
import com.examples.unittesting.model.Item;

@Component
public class ItemBusinessService {
	
	@Autowired
	ItemRepository repository;

	public Item getHardcodedItem() {
		// TODO Auto-generated method stub
		return new Item(1,"Ball",10,100);
	}
	
	public List<Item> getAllItems(){
		List<Item> items = repository.findAll();
		for(Item item:items) {
			item.setValue(item.getPrice() * item.getQuantity());
		}
		
		return items;
	}

}
