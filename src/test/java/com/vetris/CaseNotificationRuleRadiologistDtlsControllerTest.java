package com.vetris;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.vetris.adminmanagement.AdminManagementApplication;

import lombok.NoArgsConstructor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdminManagementApplication.class)
@TestPropertySource(value = { "classpath:application.properties" })
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@NoArgsConstructor
public class CaseNotificationRuleRadiologistDtlsControllerTest {


	int port = 8976;

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		// build mockMvc
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllCaseNotificationRuleRadiologistDtls() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/case_notification_rule_radiologist_dtls").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
	}

	@Test
	public void testGetByIdInstitutionCategoryLinkNotFound() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.get("/v1/case_notification_rule_radiologist_dtls/123").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void testPostInstitutionCategoryLinkNotFound() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/case_notification_rule_radiologist_dtls").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

}