package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Enfermedad;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EnfermedadServiceTest {

	@Autowired
	protected EnfermedadService enfermedadService;
	@Mock
	protected AnimalService animalServiceMock;
	@Mock
	protected CuidadorService cuidadorServiceMock;

	@Autowired
	protected CuidadorService cuidadorService;

	@Autowired
	protected AnimalService animalService;

	private Enfermedad createEnfermedad() {
		Enfermedad enfermedad = new Enfermedad();
		enfermedad.setComienzo(LocalDate.now().minusDays(1));
		enfermedad.setCurado(false);
		enfermedad.setNombre("Rabia");
		return enfermedad;
	}

	private Animal createAnimal() {
		Animal animal = new Animal();
		animal.setId(1);
		animal.setAdoptado(false);
		animal.getAtencion().setAtencion(5);
		animal.getAtencion().setDificultad(5);
		animal.getCategoria().setRaza("raza");
		animal.getCategoria().setTipo(Tipo.FELINO);
		animal.setChip("sdfsd");
		animal.setNumeroRegistro(animalService.nuevoNRegistro(animal.getCategoria()));
		animal.setFechaNacimiento(LocalDate.now().minusWeeks(20));
		animal.setFechaPrimeraIncorporacion(LocalDate.now().minusMonths(3));
		animal.setFechaUltimaIncorporacion(animal.getFechaPrimeraIncorporacion());
		animal.setFoto("http://animal.com/foto.jpg");
		animal.setNombre("Firulais");
		animal.getPeligrosidad().setGrado(5);
		animal.getPeligrosidad().setLicencia(false);
		animal.getRequisitos().setLicenciarequerida(false);
		animal.getRequisitos().setSeguro(false);
		animal.setSexo("M");
		animal.setTamanyo("L");
		return animal;
	}

	private void cuidadorMock() {
		Cuidador cuidador = cuidadorService.findCuidadorById(1).get();

		given(cuidadorServiceMock.findCuidadorByPrincipal()).willReturn((cuidador));
	}

	private void animalMock() {
		Animal animal = createAnimal();

		given(animalServiceMock.findAnimalById(1).get()).willReturn((animal));
	}

//	@Test
//	@Transactional
//	public void addCustomerWithDummyTest() {
//		Animal dummy = Mock(Animal.class);
//		Enfermedad enfermedad = createEnfermedad();
//		enfermedad.setAnimal(dummy);
//		enfermedadService.saveEnfermedad(enfermedad);
//	}

	// H19 Test Positivo
	@Test
	@Transactional
	void crearEnfermedadCorrectamente() {
		Enfermedad enfermedad = createEnfermedad();
		Animal animal = animalService.findAnimalById(5).get();
		enfermedad.setAnimal(animal);
		assertThat(enfermedadService.findAllEnfermedadByAnimalId(5).size()).isEqualTo(0);
		enfermedadService.saveEnfermedad(enfermedad);
		assertThat(enfermedadService.findAllEnfermedadByAnimalId(5).size()).isEqualTo(1);
	}

	// H19 Test Negativo
	@Test
	@Transactional
	void crearEnfermedadSinAnimal() {
		Enfermedad enfermedad = createEnfermedad();
		Exception exception = assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
			enfermedadService.saveEnfermedad(enfermedad);
			;
		});

	}

	// H20 Test Positivo
	@Test
	void findEnfermedadesAnimalesCuidador() {
		cuidadorMock();
		Cuidador cuidador = cuidadorServiceMock.findCuidadorByPrincipal();
		int cuidadorId = cuidador.getId();
		assertThat(enfermedadService.findAllEnfermedadAnimalByCuidadorId(cuidadorId).size()).isEqualTo(0);
	}

	// H20 Test Negativo
	// A este servicio solo se llega por el controlador que solo permite a un
	// cuidador autenticado, por lo que siemre va a recibir un id de un cuidador por
	// lo que lo maximo que puede devolver es una lista vacia

}
