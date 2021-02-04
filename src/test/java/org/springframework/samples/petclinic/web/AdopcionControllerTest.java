package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.AdopcionService;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AdopcionController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AdopcionControllerTest {
	
	private static final int TEST_ADOPCION_ID = 1;
	
	@MockBean
	private AdopcionService adopcionService;
	
	@MockBean 
	private UserService userService;
	
	@MockBean
	private DuenoAdoptivoService duenoAdoptivoService;
	
	@MockBean 
	private AnimalService animalService;
	
	@Autowired
	private MockMvc mockMvc;
	
//	@BeforeEach
//	void setup() {
//		given(this.adopcionService.findAdopcionById(TEST_ADOPCION_ID)).willReturn(new Adopcion());
//	}

	@WithMockUser(value = "spring")
    @Test
	void testFindAdopciones() throws Exception {
		mockMvc.perform(get("/adopcion", TEST_ADOPCION_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("adopciones"))
				.andExpect(view().name("adopcion/adopcionList"));
	}
	
	//H22
	@WithMockUser(value = "spring")
	@Test
	void testListadoAdopcionesByDueñoAdoptivo() throws Exception {
		mockMvc.perform(get("/adopcion/misSolicitudesDeAdopcion")).andExpect(status().isOk())
				.andExpect(view().name("adopcion/adopcionList2"));
	}
}
