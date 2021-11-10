package com.vetris;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
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

import lombok.NoArgsConstructor;

/**
 * Test class for InstitutionPhysicianLinkController
 * 
 * @author Jini
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApiManagementApplication.class)
@TestPropertySource(value = { "classpath:application.properties" })
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@NoArgsConstructor
public class InstitutionPhysicianLinkControllerTest {

	@Value("${server.port}")
	int port;

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		// build mockMvc
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllInstitutionPhysicianLink() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/institution_physician_link").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
	}

	@Test
	public void testUpdateInstitutionPhysicianLink() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.put("/v1/institution_physician_link/pid02").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetByIdInstitutionPhysicianLinkNotFound() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.get("/v1/institution_physician_link/pid01").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void testPostInstitutionPhysicianLinkNotFound() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/institution_physician_link").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteInstitutionPhysicianLinkNotFound() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/v1/institution_physician_link/pid03").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound()).andExpect(MockMvcResultMatchers
						.jsonPath("$.responseMessage").value(" not found"));
	}
}
