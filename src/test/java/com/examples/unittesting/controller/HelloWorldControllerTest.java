package com.examples.unittesting.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class) //this is a spring test
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {
	@Autowired
    private MockMvc mockMvc;
    
    @Test
    public void helloWorld_basic() throws Exception{
        //call "/hello-world" GETapplication/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/hello-world")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(request)
        		.andExpect(status().isOk()) //is(200)
        		//.andExpect(content().string("Hello World"))
                .andReturn();
        
        //verify "Hello World"
        assertEquals("Hello World",result.getResponse().getContentAsString());
    }
}
