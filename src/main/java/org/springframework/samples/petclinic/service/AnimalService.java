
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AnimalService {
	
	@Autowired
	AnimalRepository animalRepository;
	
	
	@Transactional(readOnly = true)
	public Optional<Animal> findAnimalById(int id) throws DataAccessException{
		return animalRepository.findById(id);
	}
	
	public Collection<Animal> findAll(){
		return animalRepository.findAll();
	}

	public void save(@Valid Animal animal) {
		animalRepository.save(animal);

	}
}
