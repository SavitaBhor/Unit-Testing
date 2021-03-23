package com.examples.unittesting.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.examples.unittesting.business.ItemBusinessService;
import com.examples.unittesting.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class) //this is a spring test
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private ItemBusinessService businessService;
    
    @Test
    public void dummyItem_basic() throws Exception{
       
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);
        
        Item item = new Item(1, "Ball", 10, 100);
        ObjectMapper mapper =new ObjectMapper();
        String itemJson = mapper.writeValueAsString(item);
        
        MvcResult result = mockMvc.perform(request)
        		.andExpect(status().isOk()) //is(200)
        		.andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                .andReturn();
        
        //verify "Hello World"
        //assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}",result.getResponse().getContentAsString());
        
        //JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}", result.getResponse().getContentAsString(), true);
        //JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10}", result.getResponse().getContentAsString(), false);
        //JSONAssert.assertEquals("{id:1, name:Ball, price:10}", result.getResponse().getContentAsString(), false);
    }
    
    @Test
    public void itemFromBusinessService_basic() throws Exception{
    	
    	when(businessService.getHardcodedItem()).thenReturn(new Item(2,"Bat",10,10));
       
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-service")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(request)
        		.andExpect(status().isOk()) //is(200)        		
        		.andExpect(content().json("{\"id\":2,\"name\":\"Bat\",\"price\":10,\"quantity\":10}"))
                .andReturn();
        
        //verify "Hello World"
        //assertEquals("{\"id\":2,\"name\":\"Bat\",\"price\":10,\"quantity\":10}",result.getResponse().getContentAsString());
        
    }
    
    @Test
    public void getAllItems_basic() throws Exception{
    	
    	when(businessService.getAllItems()).thenReturn(Arrays.asList(new Item(2,"Bat",10,10)));
       
        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-item-from-database")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(request)
        		.andExpect(status().isOk()) //is(200)        		
        		.andExpect(content().json("[{id:2,name:Bat,price:10,quantity:10}]"))
                .andReturn();
       
        
    }
}
