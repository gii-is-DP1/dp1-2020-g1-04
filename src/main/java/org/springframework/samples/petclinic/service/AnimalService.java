
package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AnimalService {
	
	@Autowired
	AnimalRepository animalRepository;
	@Autowired
	CategoriaService categoriaService;
	@Autowired
	CuidadorService cuidadorService;
	@Autowired
	CentroDeAdopcionService centroDeAdopcionService;
	
	
	@Transactional(readOnly = true)
	public Optional<Animal> findAnimalById(int id) throws DataAccessException{
		return animalRepository.findById(id);
	}
	
	public Collection<Animal> findAll(){
		return animalRepository.findAll();
	}

	@Transactional
	public void save(@Valid Animal animal) {
		//categoriaService.save(animal.getCategoria());
		animalRepository.save(animal);
		
		
		
	}
	
	public void comprobarRatioCuidador(@Valid Animal animal) throws DataAccessException{
		CentroDeAdopcion centro=animal.getCentroDeAdopcion();
		Integer cantidadAnimales=centro.getCantidadActual();
		Integer cantidadCuidadores=centro.getCuidadores().size();
		assertTrue("El ratio de Cuidadores es inferior a 15",(cantidadAnimales/cantidadCuidadores<15));
		save(animal);
		
	}
	
}
