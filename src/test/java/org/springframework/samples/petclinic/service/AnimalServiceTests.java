/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.GradoDeAtencion;
import org.springframework.samples.petclinic.model.Peligrosidad;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.samples.petclinic.service.exceptions.AforoCentroCompletadoException;
import org.springframework.samples.petclinic.service.exceptions.EventoSinCuidadoresAsignadosException;
import org.springframework.samples.petclinic.service.exceptions.RatioAnimalesPorCuidadorSuperadoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AnimalServiceTests {

	@Autowired
	protected AnimalService animalService;

	@Autowired
	protected DirectorService directorService;
	@Autowired
	protected CuidadorService cuidadorService;
	@Autowired
	protected CentroDeAdopcionService centroDeAdopcionService;

	//H12 Test Positivo
	@Test
	@Transactional
	public void findAll() {

		Collection<Animal> adopciones = animalService.findAll();
		assertThat(adopciones.size()).isEqualTo(18);

	}

	@Test
	@Transactional
	public void findAnimalById() {

		Animal animal = animalService.findAnimalById(2).get();

		assertThat(animal.getId() == 2);
		assertThat(animal.getAdoptado() == true);
		assertThat(animal.getAtencion().getAtencion() == 3);
		assertThat(animal.getAtencion().getDificultad() == 3);
		assertThat(animal.getChip() == "CHIP123");
		assertThat(animal.getEdad() == 5);
		assertThat(animal.getFechaNacimiento() == LocalDate.of(2019, 10, 1));
		assertThat(animal.getFechaPrimeraIncorporacion() == LocalDate.of(2019, 9, 1));
		assertThat(animal.getFechaUltimaIncorporacion() == LocalDate.of(2019, 10, 1));
		assertThat(animal.getNombre() == "NombreAnimal2");
		assertThat(animal.getNombre() == "Nregistro3");
		assertThat(animal.getPeligrosidad().getGrado() == 3);
		assertThat(animal.getPeligrosidad().getLicencia() == true);
		assertThat(animal.getRequisitos().getLicencia() == true);
		assertThat(animal.getRequisitos().getSeguro() == true);
		assertThat(animal.getSexo() == "Masculino");
		assertThat(animal.getTamanyo() == "Grande");
		assertThat(animal.getCuidador().getId() == 1);
		assertThat(animal.getCategoria().getId() == 1);
	}

	@Test
	@Transactional
	public void shouldUpdateAnimalName() throws Exception {
		Animal animal1 = animalService.findAnimalById(1).get();
		String antiguoNombre = animal1.getNombre();

		String nuevoNombre = antiguoNombre + "X";
		animal1.setNombre(nuevoNombre);
		this.animalService.save(animal1);

		animal1 = this.animalService.findAnimalById(1).get();

		assertThat(animal1.getNombre()).isEqualTo(nuevoNombre);

	}

	@Test
	@Transactional
	public void noShouldUpdateAnimalNoExist() throws Exception {
		Optional<Animal> animal = animalService.findAnimalById(23);

		assertThat(animal).isEmpty();

	}

	// ReglaDeNegocioR6--1cuidadorCada15Animales-Sin Cuidador
	@Test
	@Transactional
	void comprobarRatioCuidador() throws RatioAnimalesPorCuidadorSuperadoException, AforoCentroCompletadoException {
		Animal animal = animalService.findAnimalById(1).get();
		CentroDeAdopcion centroDeAdopcion = centroDeAdopcionService.findById(3);
		animal.setCentroDeAdopcion(centroDeAdopcion);
		Exception exception = assertThrows(RatioAnimalesPorCuidadorSuperadoException.class, () -> {
			animalService.comprobarRatioCuidador(animal);
			;
		});

	}

	@Test
	@Transactional
	void comprobarRatioCuidadorModifica1de15()
			throws RatioAnimalesPorCuidadorSuperadoException, AforoCentroCompletadoException {
		Animal animal = animalService.findAnimalById(11).get();
		animal.setNombre("cambia");
		animalService.comprobarRatioCuidador(animal);
		assertThat(animalService.findAllNoAdoptedByCentro(3).size()).isEqualTo(15);
	}

	
	
	
}