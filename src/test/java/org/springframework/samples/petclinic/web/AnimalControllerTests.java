package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Enfermedad;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.CategoriaService;
import org.springframework.samples.petclinic.service.CentroDeAdopcionService;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = AnimalController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AnimalControllerTests {

	private static final int TEST_ANIMAL_ID = 1;

	@Autowired
	private AnimalController animalController;
	
	@MockBean
	private AnimalService animalService;
	
	@MockBean
	private CuidadorService cuidadorService;
	
	@MockBean
	private CentroDeAdopcionService centroDeAdopcionService;
	
	@MockBean
	private CategoriaService categoriaService;
	
	@Autowired
	private MockMvc mockMvc;

	private Animal animal;
	
	//H12 Test Positivo
	@WithMockUser(value = "spring")
	@Test
	void testListadoTodosAnimales() throws Exception {
		mockMvc.perform(get("/animales/todos")).andExpect(status().isOk())
				.andExpect(view().name("animales/AnimalListing"));
	}

}
