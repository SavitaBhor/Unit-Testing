package com.examples.unittesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.unittesting.business.ItemBusinessService;
import com.examples.unittesting.model.Item;

@RestController
public class ItemController {
	
	@Autowired
	private ItemBusinessService businessService;
	
	@GetMapping("/dummy-item")
	public Item dummyItem() {
		return new Item(1, "Ball",10,100);
	}
	
	@GetMapping("/item-from-service")
	public Item itemFromService() {
		return businessService.getHardcodedItem();
	}
	
	@GetMapping("/all-item-from-database")
	public List<Item> allItemFromDB() {
		return businessService.getAllItems();
	}
	
	
}
