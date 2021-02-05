package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

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
import static org.mockito.Mockito.verify;
import org.springframework.samples.petclinic.model.Tipo;



@WebMvcTest(controllers = AnimalController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
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
	}

	@BeforeEach
	void setup() {
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setRaza("agua");
		categoria.setTipo(Tipo.CANINO);

		given(categoriaService.findCategoriaById(TEST_CATEGORIA_ID)).willReturn(Optional.of(categoria));

	}
	
	//H12 Test Positivo
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
		mockMvc.perform(get("/animales/show/{animalId}"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("animal", hasProperty("id", is(TEST_ANIMAL_ID))))
				.andExpect(model().attribute("animal", hasProperty("nombre", is("nombreAnimal"))))
				.andExpect(model().attribute("animal", hasProperty("chip", is("chip"))))
				.andExpect(model().attribute("animal", hasProperty("numeroRegistro", is("numRegistro"))))
				.andExpect(model().attribute("animal", hasProperty("tamanyo", is("pequeño"))))
				.andExpect(model().attribute("animal", hasProperty("sexo", is("masculino"))))
				.andExpect(view().name("animales/showAnimal"));

		
	}
	//HU-3
	@WithMockUser(value = "spring")
	@Test
	void testShowAnimalSuccess() throws Exception{
		createAnimal();
		mockMvc.perform(get("/animales/show/{animalId}",TEST_ANIMAL_ID))
				.andExpect(status().isOk())
				.andExpect(model().attribute("animal", hasProperty("nombre", is("NombreTest"))))
				.andExpect(model().attribute("animal", hasProperty("adoptado", is(false))))
				.andExpect(model().attribute("animal", hasProperty("chip", is("sdfsd"))))
				.andExpect(model().attribute("animal", hasProperty("fechaNacimiento", is(LocalDate.now().minusWeeks(20)))))
				.andExpect(model().attribute("animal", hasProperty("fechaPrimeraIncorporacion", is(LocalDate.now().minusMonths(3)))))
				.andExpect(model().attribute("animal", hasProperty("fechaUltimaIncorporacion", is(animal.getFechaPrimeraIncorporacion()))))
				.andExpect(model().attribute("animal", hasProperty("foto", is("http://animal.com/foto.jpg"))))
//				.andExpect(model().attribute("animal", hasProperty("peligrosidad.grado", is("5"))))
//				.andExpect(model().attribute("animal", hasProperty("peligrosidad.licencia", is(false))))		
//				.andExpect(model().attribute("animal", hasProperty("requisitos.licenciarequerida", is(false))))
				.andExpect(model().attribute("animal", hasProperty("numeroRegistro", is(animalService.nuevoNRegistro(animal.getCategoria().toString())))))
//				.andExpect(model().attribute("animal", hasProperty("requisitos.seguro", is(false))))
//				.andExpect(model().attribute("animal", hasProperty("atencion.atencion", is(5))))
//				.andExpect(model().attribute("animal", hasProperty("atencion.dificultad", is(5))))
				.andExpect(model().attribute("animal", hasProperty("sexo", is("M"))))
				.andExpect(model().attribute("animal", hasProperty("tamanyo", is("L"))))
//				.andExpect(model().attribute("animal", hasProperty("cuidador.id", is(1))))
//				.andExpect(model().attribute("animal", hasProperty("centroDeAdopcion.id", is(1))))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("animales/showAnimal"));
	}

}
