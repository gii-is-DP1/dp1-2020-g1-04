package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.DirectorService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers=EventoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class EventoControllerTest {
	
	private static final int TEST_EVENTO_ID = 1;
	
	@MockBean
	private EventoService eventoService;
	
	@MockBean
	private CuidadorService cuidadorService;
	
	@MockBean
	private DuenoAdoptivoService duenoAdoptivoService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private DirectorService directorService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Evento evento;
	
//	@BeforeEach
//	void setup() {
//		evento = new Evento();
//		evento.setId(TEST_EVENTO_ID);
//		given(this.eventoService.findEventoById(TEST_EVENTO_ID)).willReturn(evento);
//	}
	
	@WithMockUser(value = "spring")
    @Test
    void testListadoEventosDirector() throws Exception{
		mockMvc.perform(get("/director/misEventos", TEST_EVENTO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("eventos"))
				.andExpect(view().name("eventos/listadoEventos"));
				
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testListadoEventosDirectorError() throws Exception{
		mockMvc.perform(get("/director/misEventos", TEST_EVENTO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeDoesNotExist("eventos"))
				.andExpect(view().name("exception"));
				
	}

	@WithMockUser(value = "spring")
    @Test
    void testListadoEventosDueno() throws Exception{
		mockMvc.perform(get("/misEventos", TEST_EVENTO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("eventos"))
				.andExpect(view().name("eventos/listadoEventos"));
				
	}
	
}
