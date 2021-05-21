package com.cognizant.springlearn;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.springlearn.controller.CountryController;

@AutoConfigureMockMvc
@SpringBootTest
class SpringLearnApplicationTests {
	
	@Autowired
	private CountryController countryController;
	
	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		assertNotNull(countryController);
	}

	@Test
	public void testGetCountry() throws Exception {
		
		ResultActions actions = mvc.perform(get("/country"));
		
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("IN"));
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("India"));
	}
	
	@Test
	public void testGetCountryException() throws Exception {
		ResultActions actions = mvc.perform(get("/countries/az"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Country not found"));
	}
	
	@Test
	public void testUpdateEmployeeException() throws Exception {
		ResultActions actions = mvc.perform(put("/employees"));
		actions.andExpect(status().isBadRequest());
	}
}
