package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.DirectorService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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
	
	@BeforeEach
	void setup() {
		evento = new Evento();
		evento.setId(TEST_EVENTO_ID);
		evento.setTitulo("Evento 1");
		evento.setDireccion("Direccion 1");
		evento.setFecha(LocalDate.of(2020, 01, 01));
		evento.setAforo(14);
		evento.setDescripcion("Descripcion");
		Set<Cuidador> cuidadores = new HashSet<Cuidador>();
		Cuidador cuidador1 = new Cuidador();
		Cuidador cuidador2 = new Cuidador();
		Cuidador cuidador3 = new Cuidador();
		cuidadores.add(cuidador1);
		cuidadores.add(cuidador2);
		cuidadores.add(cuidador3);
		evento.setCuidadores(cuidadores);
		Set<DuenoAdoptivo> duenos = new HashSet<DuenoAdoptivo>();
		DuenoAdoptivo dueno1 = new DuenoAdoptivo();
		DuenoAdoptivo dueno2 = new DuenoAdoptivo();
		DuenoAdoptivo dueno3 = new DuenoAdoptivo();
		duenos.add(dueno1);
		duenos.add(dueno2);
		duenos.add(dueno3);
		evento.setDuenos(duenos);
		
		given(this.eventoService.findEventoById(TEST_EVENTO_ID)).willReturn(Optional.of(evento));
	}
	
	
	//HU-3
	@WithMockUser(value = "spring")
    @Test
    void testListadoEventosDirector() throws Exception{
		mockMvc.perform(get("/eventos/director/misEventos", TEST_EVENTO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("eventos"))
				.andExpect(view().name("eventos/listadoEventos"));
				
	}
	

	@WithMockUser(value = "spring")
    @Test
    void testListadoEventosDueno() throws Exception{
		mockMvc.perform(get("/eventos/misEventos", TEST_EVENTO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("eventos"))
				.andExpect(view().name("eventos/listadoEventos"));
				
	}
	
	
	//HU-4
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception{
		mockMvc.perform(get("/eventos/nuevo", TEST_EVENTO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("evento"))
				.andExpect(view().name("eventos/createOrUpdateEventoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception{
		mockMvc.perform(post("/eventos/nuevo")
				.param("titulo", "Evento 1")
				.with(csrf())
				.param("direccion","Direccion 1")
				.param("fecha","01/01/2020")
				.param("aforo","14")
				.param("descripcion","Descripcion"))
				.andExpect(status().is3xxRedirection());
				//.andExpect(view().name("redirect:/eventos/show/null"));

	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception{
		mockMvc.perform(post("/eventos/nuevo", TEST_EVENTO_ID)
				.param("titulo", "NuevoEvento")
				.with(csrf())
				.param("direccion","direccion1"))
				.andExpect(model().attributeHasFieldErrors("evento","fecha"))
				.andExpect(model().attributeHasFieldErrors("evento","aforo"))
				.andExpect(model().attributeHasFieldErrors("evento","descripcion"))
				.andExpect(view().name("eventos/createOrUpdateEventoForm"));
	}
	
	//HU-5

	@WithMockUser(value = "spring")
	@Test
	void testEditEventoSuccess() throws Exception{
		mockMvc.perform(get("/eventos/edit/{eventoId}", TEST_EVENTO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("evento"))
				.andExpect(model().attribute("evento", hasProperty("titulo", is("Evento 1"))))
				.andExpect(model().attribute("evento", hasProperty("direccion", is("Direccion 1"))))
				.andExpect(model().attribute("evento", hasProperty("fecha", is(LocalDate.of(2020, 01, 01)))))
				.andExpect(model().attribute("evento", hasProperty("aforo", is(14))))
				.andExpect(model().attribute("evento", hasProperty("descripcion", is("Descripcion"))))
				.andExpect(view().name("eventos/createOrUpdateEventoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditEventoProcessSuccess() throws Exception{
		mockMvc.perform(post("/eventos/edit/{eventoId}", TEST_EVENTO_ID)
				.with(csrf())
				.param("titulo", "Evento 1")
				.param("direccion","Direccion 1")
				.param("fecha", "01/01/2010")
				.param("aforo", "14")
				.param("descripcion","Descripcion"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/eventos/show/null"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditEventoProcessHasErrors() throws Exception{
		mockMvc.perform(post("/eventos/edit/{eventoId}", TEST_EVENTO_ID)
			.with(csrf())
			.param("titulo", "Evento 1")
			.param("direccion","Direccion 1")
			.param("descripcion","Descripcion"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("evento"))
			.andExpect(model().attributeHasFieldErrors("evento", "fecha"))
			.andExpect(model().attributeHasFieldErrors("evento", "aforo"))
			.andExpect(view().name("eventos/createOrUpdateEventoForm"));
	}
}
