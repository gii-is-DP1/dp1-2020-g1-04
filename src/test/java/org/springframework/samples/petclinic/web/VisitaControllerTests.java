package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
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
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Enfermedad;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.EnfermedadService;
import org.springframework.samples.petclinic.service.VisitaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = VisitaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class VisitaControllerTests {

	private static final int TEST_VISITA_ID = 1;

	@Autowired
	private VisitaController visitaController;

	@MockBean
	private VisitaService visitaService;
	
	@MockBean
	private AnimalService animalService;

	@MockBean
	private CuidadorService cuidadorService;

	@MockBean
	private DuenoAdoptivoService duenoAdoptivoService;

	@MockBean
	private EnfermedadService enfermedadService;
	
	@Autowired
	private MockMvc mockMvc;

	private Visita visita;
	
	void principal() {
		DuenoAdoptivo principal = mock(DuenoAdoptivo.class);
		given(this.duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(principal);

	}

	// H21
	@WithMockUser(value = "spring")
	@Test
	void testListadoVisitasByDueñoAdoptivo() throws Exception {
		principal();
		mockMvc.perform(get("/visitas/misVisitas", TEST_VISITA_ID)).andExpect(status().isOk())
				.andExpect(view().name("/visitas/listadoVisitas"));
	}

}
