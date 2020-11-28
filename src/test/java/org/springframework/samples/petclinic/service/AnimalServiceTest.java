package org.springframework.samples.petclinic.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AnimalServiceTest {
	
	@Autowired
	protected AnimalService animalService;
	
	@Autowired
	protected DirectorService directorService;
	@Autowired
	protected CuidadorService cuidadorService;
	
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



}
