package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.service.DirectorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= DirectorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class DirectorControllerTest {
	
	private static final int TEST_DIRECTOR_ID = 1;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DirectorService directorService;

	@BeforeEach
	void setup() {
		given(this.directorService.findDirectorById(TEST_DIRECTOR_ID)).willReturn(new Director());
	}
	
	@WithMockUser(value="spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/director/new", TEST_DIRECTOR_ID)).andExpect(status().isOk())
				.andExpect(view().name("director/createOrUpdateOwnerForm"));
	}
	

	@WithMockUser(value="spring")
	@Test
	void testShowDirector() throws Exception {
		mockMvc.perform(get("/director/{directorId}", TEST_DIRECTOR_ID)).andExpect(status().isOk())
		.andExpect(view().name("director/directorDetails"));
	}
}
