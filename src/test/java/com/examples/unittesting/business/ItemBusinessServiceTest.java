package com.examples.unittesting.business;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.examples.unittesting.data.ItemRepository;
import com.examples.unittesting.model.Item;


//MockitoJUnitRunner-The automatic validation of framework usage is actually worth having. 
//It gives you better reporting if you make one of these mistakes.
//you can't use it if you need another JUnit runner, such as the Spring one.
@RunWith(MockitoJUnitRunner.class)
public class ItemBusinessServiceTest {
	
	@InjectMocks
	private ItemBusinessService business;		
	
	@Mock
	private ItemRepository repository;
	
	@Test
	public void getAllItems_Basic() {
		
		when(repository.findAll()).thenReturn(Arrays.asList(new Item(2,"Bat",100,10),new Item(3,"Ball",10,10)));
		List<Item> items = business.getAllItems();
		assertEquals(1000,items.get(0).getValue());
		assertEquals(100,items.get(1).getValue());
	}
	
	
}
