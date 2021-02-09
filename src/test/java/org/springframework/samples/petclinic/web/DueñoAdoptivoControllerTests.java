package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.BusquedaVaciaException;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for {@link DuenoAdoptivoController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers = DuenoAdoptivoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class DuenoAdoptivoControllerTests {

	private static final int TEST_OWNER_ID = 1;

	@Autowired
	private DuenoAdoptivoController duenoAdoptivoController;

	@MockBean
	private DuenoAdoptivoService duenoAdoptivoService;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	private DuenoAdoptivo george;

	@BeforeEach
	void setup() throws DataAccessException, BusquedaVaciaException {

		george = new DuenoAdoptivo();
		george.setId(TEST_OWNER_ID);
		george.setNombre("George");
		george.setApellidos("Franklin");
		george.setDireccion("110 W. Liberty St.");
		george.setDni("24324324G");
		george.setTelefono("6085551023");
		george.setEmail("test@email.com");
		given(this.duenoAdoptivoService.findDuenoAdoptivoById(TEST_OWNER_ID)).willReturn(Optional.of(george));

	}

	void principal() {
		george = new DuenoAdoptivo();
		george.setId(TEST_OWNER_ID);
		george.setNombre("George");
		george.setApellidos("Franklin");
		george.setDireccion("110 W. Liberty St.");
		george.setDni("24324324G");
		george.setTelefono("6085551023");
		george.setEmail("test@email.com");
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		george.setUser(user);
		given(this.duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(george);

	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/duenoAdoptivo/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("duenoAdoptivo"))
				.andExpect(view().name("duenosAdoptivos/createOrUpdateduenoAdoptivoForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/duenoAdoptivo/new").param("nombre", "Joe").param("apellidos", "Bloggs").with(csrf())
				.param("direccion", "123 Caramel Street").param("email", "London@mail.com").param("dni", "423423423G")
				.param("telefono", "01316761638")).andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/duenoAdoptivo/new").with(csrf()).param("nombre", "Joe").param("apellidos", "Bloggs")
				.param("direccion", "London")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("duenoAdoptivo"))
				.andExpect(model().attributeHasFieldErrors("duenoAdoptivo", "email"))
				.andExpect(model().attributeHasFieldErrors("duenoAdoptivo", "telefono"))
				.andExpect(model().attributeHasFieldErrors("duenoAdoptivo", "dni"))
				.andExpect(view().name("duenosAdoptivos/createOrUpdateduenoAdoptivoForm"));
	}

	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateDuenoAdoptivoForm() throws Exception {
		mockMvc.perform(get("/duenosAdoptivos/edit/{duenoAdoptivoId}", TEST_OWNER_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("duenoAdoptivo"))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("apellidos", is("Franklin"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("nombre", is("George"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("direccion", is("110 W. Liberty St."))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("dni", is("24324324G"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("telefono", is("6085551023"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("email", is("test@email.com"))))
				.andExpect(view().name("duenosAdoptivos/createOrUpdateduenoAdoptivoForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateDuenoAdoptivoFormSuccess() throws Exception {
		principal();
		mockMvc.perform(post("/duenosAdoptivos/edit/{duenoAdoptivoId}", TEST_OWNER_ID).with(csrf())
				.param("nombre", "Joe").param("apellidos", "Bloggs").param("direccion", "123 Caramel Street")
				.param("dni", "45556667G").param("telefono", "01616291589").param("email", "test2@email.com")
				.param("user.username", "Sam")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/duenosAdoptivos/show"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateDuenoAdoptivoFormHasErrors() throws Exception {
		mockMvc.perform(post("/duenosAdoptivos/edit/{duenoAdoptivoId}", TEST_OWNER_ID).with(csrf())
				.param("nombre", "Joe").param("apellidos", "Bloggs").param("direccion", "London"))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("duenoAdoptivo"))
				.andExpect(model().attributeHasFieldErrors("duenoAdoptivo", "dni"))
				.andExpect(model().attributeHasFieldErrors("duenoAdoptivo", "telefono"))
				.andExpect(model().attributeHasFieldErrors("duenoAdoptivo", "email"))
				.andExpect(view().name("duenosAdoptivos/createOrUpdateduenoAdoptivoForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowDuenoAdoptivo() throws Exception {
		mockMvc.perform(get("/duenosAdoptivos/show/{duenoAdoptivoId}", TEST_OWNER_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("apellidos", is("Franklin"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("nombre", is("George"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("direccion", is("110 W. Liberty St."))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("dni", is("24324324G"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("telefono", is("6085551023"))))
				.andExpect(model().attribute("duenoAdoptivo", hasProperty("email", is("test@email.com"))))
				.andExpect(view().name("duenosAdoptivos/duenoAdoptivoDetails"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateDuenoAdoptivoFormSuccess2() throws Exception {
		principal();
		mockMvc.perform(post("/duenosAdoptivos/editByName", TEST_OWNER_ID).with(csrf()).param("nombre", "Joe")
				.param("apellidos", "Bloggs").param("direccion", "123 Caramel Street").param("dni", "45556667G")
				.param("telefono", "01616291589").param("email", "test2@email.com"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/duenosAdoptivos/1"));
	}

	// H13 Test Positivo
	@WithMockUser(value = "spring")
	@Test
	void testListadoTodosDueños() throws Exception {
		mockMvc.perform(get("/duenosAdoptivos")).andExpect(status().isOk())
				.andExpect(view().name("duenosAdoptivos/duenosAdoptivosList"));
	}
}
