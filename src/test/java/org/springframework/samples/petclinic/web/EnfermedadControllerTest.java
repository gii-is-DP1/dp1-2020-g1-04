package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Enfermedad;
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
//		Cuidador principal = new Cuidador();
//		CentroDeAdopcion c = new CentroDeAdopcion();
//		c.setCantidadMax(25);
//		c.setDireccion("dfsd");
//		c.setId(1);
//		c.setNombre("dsf");
//		principal = new Cuidador();
//		principal.setId(TEST_ENFERMEDAD_ID);
//		principal.setNombre("George");
//		principal.setApellidos("Franklin");
//		principal.setDni("24324324G");
//		principal.setTelefono("6085551023");
//		principal.setEmail("test@email.com");
//		principal.setCentroDeAdopcion(c);
//		int i = principal.getId();

		Cuidador principal=mock(Cuidador.class);
		given(this.cuidadorService.findCuidadorByPrincipal()).willReturn(principal);

	}

	@WithMockUser(value = "spring")
	@Test
	void testCreateEnfermedadFormSuccess() throws Exception {
		mockMvc.perform(post("/enfermedad/nuevo/{animalId}", 3).with(csrf()).param("nombre", "Rabia")
				.param("comienzo", "10/11/2020").param("cuado", "false")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/enfermedad/show/1"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoEnfermedadesAnimalesByCuidador() throws Exception {
		principal();
		mockMvc.perform(post("/enfermedad/cuidador", TEST_ENFERMEDAD_ID)).andExpect(status().isOk())
				.andExpect(view().name("enfermedad/listadoEnfermedades"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoEnfermedadesAnimalesByCuidador2() throws Exception {
		principal();
		given(this.enfermedadService.findAllEnfermedadAnimalByCuidadorId(1)).willReturn(new ArrayList<>());
		mockMvc.perform(get("/enfermedad/cuidador").sessionAttr("type","cuidador")).andExpect(status().isOk());
	}
}
