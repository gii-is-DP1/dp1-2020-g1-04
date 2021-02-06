package org.springframework.samples.petclinic.web;


import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.GradoDeAtencion;
import org.springframework.samples.petclinic.model.Peligrosidad;
import org.springframework.samples.petclinic.model.RequisitosDeAdopcion;

import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.CategoriaService;
import org.springframework.samples.petclinic.service.CentroDeAdopcionService;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.samples.petclinic.model.Tipo;



@WebMvcTest(controllers = AnimalController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class AnimalControllerTests {

	private static final int TEST_ANIMAL_ID = 1;
	private static final int TEST_CATEGORIA_ID = 1;

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

	private void createAnimal() {
		animal = new Animal();
		animal.setId(TEST_ANIMAL_ID);
		animal.setAdoptado(false);
		animal.setChip("sdfsd");
		animal.setFechaNacimiento(LocalDate.now().minusWeeks(20));
		animal.setFechaPrimeraIncorporacion(LocalDate.now().minusMonths(3));
		animal.setFechaUltimaIncorporacion(animal.getFechaPrimeraIncorporacion());
		animal.setFoto("http://animal.com/foto.jpg");
		animal.setNombre("NombreTest");
		Peligrosidad peligrosidad = new Peligrosidad();
		peligrosidad.setGrado(5);
		peligrosidad.setLicencia(false);
		animal.setPeligrosidad(peligrosidad);
		RequisitosDeAdopcion requisitos = new RequisitosDeAdopcion();
		requisitos.setLicenciarequerida(false);
		requisitos.setSeguro(false);
		animal.setRequisitos(requisitos);
		GradoDeAtencion atencion = new GradoDeAtencion();
		atencion.setAtencion(5);
		atencion.setDificultad(5);
		animal.setAtencion(atencion);
		animal.setSexo("M");
		animal.setTamanyo("L");
		Categoria cat = categoriaService.findCategoriaById(1).get();
		animal.setCategoria(cat);
		animal.setNumeroRegistro(animalService.nuevoNRegistro(animal.getCategoria().toString()));
		given(animalService.findAnimalById(TEST_ANIMAL_ID)).willReturn(Optional.of(animal));
		given(animalService.inicializarAnimal(cat, animal)).willReturn(animal);
	}

	@BeforeEach
	void setup() {
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setRaza("agua");
		categoria.setTipo(Tipo.CANINO);

		given(categoriaService.findCategoriaById(TEST_CATEGORIA_ID)).willReturn(Optional.of(categoria));

	}


	// H12 Test Positivo

	@WithMockUser(value = "spring")
	@Test
	void testListadoTodosAnimales() throws Exception {
		mockMvc.perform(get("/animales/todos")).andExpect(status().isOk())
				.andExpect(view().name("animales/AnimalListing"));
	}
	
	//HU-3
	@WithMockUser(value = "spring")
	@Test
	void testShowAnimal() throws Exception{
		createAnimal();
		mockMvc.perform(get("/animales/show/{animalId}", TEST_ANIMAL_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("animales/showAnimal"));

		
	}

	// H10 Test Positivo
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		createAnimal();
		mockMvc.perform(get("/animales/nuevo/{categoriaId}", TEST_CATEGORIA_ID)).andExpect(status().isOk())
				.andExpect(view().name("animales/createOrUpdateAnimal"));
	}

	// H10 Test Positivo
	@WithMockUser(value = "spring")
	@Test
	void testCreateAnimalFormSuccess() throws Exception {
		mockMvc.perform(post("/animales/nuevo/{categoriaId}", TEST_CATEGORIA_ID).with(csrf()).param("nombre", "Joe")
				.param("adoptado", "false").param("chip", "123Chip").param("fechaNacimiento", "25/10/2020")
				.param("fechaPrimeraIncorporacion", "26/11/2020").param("fechaUltimaIncorporacion", "26/11/2020")
				.param("foto", "http://animal.com/foto.jpg").param("peligrosidad.grado", "5")
				.param("peligrosidad.licencia", "false").param("requisitos.licenciarequerida", "false")
				.param("numeroRegistro", "sdfsdf").param("requisitos.seguro", "false").param("atencion.atencion", "5")
				.param("atencion.dificultad", "5").param("sexo", "M").param("tamanyo", "L").param("cuidador.id", "1")
				.param("centroDeAdopcion.id", "1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/animales/show/null"));
	}

	// H10 Negativo
	@WithMockUser(value = "spring")
	@Test
	void testCreateAnimalFormError() throws Exception {
		mockMvc.perform(post("/animales/nuevo/{categoriaId}", TEST_CATEGORIA_ID).with(csrf()).param("nombre", "Joe")
				.param("adoptado", "false").param("chip", "123Chip").param("fechaNacimiento", "25/10/2020")
				.param("fechaPrimeraIncorporacion", "26/11/2020").param("fechaUltimaIncorporacion", "26/11/2020")
				.param("foto", "http://animal.com/foto.jpg").param("peligrosidad.grado", "5")
				.param("peligrosidad.licencia", "false").param("requisitos.licenciarequerida", "false")
				.param("requisitos.seguro", "false").param("atencion.atencion", "5").param("atencion.dificultad", "5")
				.param("sexo", "M").param("tamanyo", "L").param("cuidador.id", "1").param("centroDeAdopcion.id", "1"))
				.andExpect(model().attributeHasFieldErrors("animal", "numeroRegistro")).andExpect(status().isOk())
				.andExpect(view().name("animales/createOrUpdateAnimal"));
	}

	// H11 Positivo
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateAnimalFormSuccess() throws Exception {
		createAnimal();
		mockMvc.perform(post("/animales//edit/{animalId}", TEST_ANIMAL_ID).with(csrf()).param("nombre", "Joe")
				.param("adoptado", "false").param("chip", "123Chip").param("fechaNacimiento", "25/10/2020")
				.param("fechaPrimeraIncorporacion", "26/11/2020").param("fechaUltimaIncorporacion", "26/11/2020")
				.param("foto", "http://animal.com/foto.jpg").param("peligrosidad.grado", "5")
				.param("peligrosidad.licencia", "false").param("requisitos.licenciarequerida", "false")
				.param("numeroRegistro", "sdfsdf").param("requisitos.seguro", "false").param("atencion.atencion", "5")
				.param("atencion.dificultad", "5").param("sexo", "M").param("tamanyo", "L").param("cuidador.id", "1")
				.param("centroDeAdopcion.id", "1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/animales/show/1"));
	}

	// H11 Positivo
	@WithMockUser(value = "spring")
		@Test
		void testProcessUpdateAnimalFormError() throws Exception {
		mockMvc.perform(post("/animales/edit/{animal}", TEST_ANIMAL_ID).with(csrf()).param("nombre", "Joe")
				.param("adoptado", "false").param("chip", "123Chip").param("fechaNacimiento", "25/10/2020")
				.param("fechaPrimeraIncorporacion", "26/11/2020").param("fechaUltimaIncorporacion", "26/11/2020")
				.param("foto", "http://animal.com/foto.jpg").param("peligrosidad.grado", "5")
				.param("peligrosidad.licencia", "false").param("requisitos.licenciarequerida", "false")
				.param("requisitos.seguro", "false").param("atencion.atencion", "5").param("atencion.dificultad", "5")
				.param("sexo", "M").param("tamanyo", "L").param("cuidador.id", "1").param("centroDeAdopcion.id", "1"))
				.andExpect(model().attributeHasFieldErrors("animal", "numeroRegistro")).andExpect(status().isOk())
				.andExpect(view().name("animales/createOrUpdateAnimal"));
	}
}
