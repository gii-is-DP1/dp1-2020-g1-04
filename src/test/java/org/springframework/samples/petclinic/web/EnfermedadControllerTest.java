package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Enfermedad;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.EnfermedadService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = EnfermedadController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class EnfermedadControllerTest {

	private static final int TEST_ENFERMEDAD_ID = 1;

	@Autowired
	private EnfermedadController enfermedadController;

	@MockBean
	private AnimalService animalService;

	@MockBean
	private CuidadorService cuidadorService;

	@MockBean
	private EnfermedadService enfermedadService;

	@Autowired
	private MockMvc mockMvc;

	private Enfermedad enfermedad;

	void principal() {
		Cuidador principal = mock(Cuidador.class);
		given(this.cuidadorService.findCuidadorByPrincipal()).willReturn(principal);

	}

	void mockAnimal() {
		Animal animal = mock(Animal.class);
		given(this.animalService.findAnimalById(3)).willReturn(Optional.of(animal));
	}

	// H19 Positivo
	@WithMockUser(value = "spring")
	@Test
	void testCreateEnfermedadFormSuccess() throws Exception {
		mockAnimal();
		mockMvc.perform(post("/enfermedad/nuevo/{animalId}", 3).with(csrf()).param("nombre", "Rabia")
				.param("comienzo", "10/11/2020").param("curado", "true").param("fin", "10/12/2020")
				.param("comentario", "cumple requisitos")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/enfermedad/show/null"));
	}

	// H19 Negativo
	@WithMockUser(value = "spring")
	@Test
	void testCreateEnfermedadFormErrorFechaFinAnteriorFechaInicio() throws Exception {
		mockAnimal();
		mockMvc.perform(post("/enfermedad/nuevo/{animalId}", 3).with(csrf()).param("nombre", "Rabia")
				.param("comienzo", "10/11/2020").param("curado", "true").param("fin", "10/09/2020")
				.param("comentario", "cumple requisitos")).andExpect(status().isOk())
				.andExpect(view().name("enfermedad/createOrUpdateEnfermedadForm"));
	}

	// H20 Positivo
	@WithMockUser(value = "spring")
	@Test
	void testListadoEnfermedadesAnimalesByCuidador() throws Exception {
		principal();
		mockMvc.perform(get("/enfermedad/cuidador", TEST_ENFERMEDAD_ID)).andExpect(status().isOk())
				.andExpect(view().name("enfermedad/listadoEnfermedades"));
	}

	// H20 Negativo
	@WithMockUser(value = "spring")
	@Test
	void testListadoEnfermedadesAnimalesByCuidadorNoAutenticado() throws Exception {
		// principal();
		mockMvc.perform(get("/enfermedad/cuidador", TEST_ENFERMEDAD_ID)).andExpect(status().isOk())
				.andExpect(status().isBadRequest());
	}
}
