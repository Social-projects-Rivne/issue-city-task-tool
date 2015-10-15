package edu.com.softserveinc.bawl.controllers;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-root-context.xml", "classpath:test-data-context.xml","classpath:test-mail-context.xml"
,"classpath:test-root-context.xml"})
public class HomeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private HomeController mockHomeController;

	@Before
	public void setup() {
//		mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wa99c).build();
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
	}

	@Test
	public void testGetHome() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
		.andExpect(view().name("home"));
	}
	
	@Test
	public void testPostHome() throws Exception {
		mockMvc.perform(post("/")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void testPutHome() throws Exception {
		mockMvc.perform(put("/")).andExpect(status().isMethodNotAllowed());
	}
	
	@Test
	public void testDeleteHome() throws Exception {
		mockMvc.perform(delete("/")).andExpect(status().isMethodNotAllowed());
	}
	
}
