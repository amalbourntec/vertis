package com.bourntec.vetris;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vetris.apimanagement.ApiManagementApplication;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = ApiManagementApplication.class) 
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class VetrisApplicationTests {

	 @Value("${server.port}") 
	    int port;
	 
	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@BeforeEach
	public void setup() {
	    //build mockMvc
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
//	@Test
//	void contextLoads() throws Exception {
//		MvcResult mvcResult = mockMvc.perform(put("http://localhost:8597/mastermanagement/v1/salesperson/123", "bar"))
//	            .andExpect(status().isOk()).andReturn();
//	}

	@Test
	public void getAllSalesPerson() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/mastermanagement/v1/salesperson")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
	      //.andExpect(MockMvcResultMatchers.jsonPath("$.[*].employeeId").isNotEmpty());
	}
	
	@Test
	public void getSalesPersonNotFound() throws Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/mastermanagement/v1/salesperson/123")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isNotFound());
	}
	
	@Test
	public void postSalesPersonNotFound() throws Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/mastermanagement/v1/salesperson")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isBadRequest());
	}
}
