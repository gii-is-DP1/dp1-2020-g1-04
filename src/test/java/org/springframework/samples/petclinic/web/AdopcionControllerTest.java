package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
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
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdopcionService;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.DirectorService;
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
	
	@MockBean 
	private DirectorService directorService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private void duenoAdoptivo() {
		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Jose Manuel");
		duenoAdoptivo.setApellidos("Durán");
		duenoAdoptivo.setDireccion("calle falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		duenoAdoptivo.setUser(user);
		duenoAdoptivo.setId(11);
		given(this.duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(duenoAdoptivo);
	}
	
    //HU-1
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
		duenoAdoptivo();
		mockMvc.perform(get("/adopcion/misSolicitudesDeAdopcion")).andExpect(status().isOk())
				.andExpect(view().name("adopcion/adopcionList2"));
	}
}
