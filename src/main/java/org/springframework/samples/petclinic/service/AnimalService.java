package org.springframework.samples.petclinic.service;

import java.util.Collection;
//import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.repository.AnimalRepository;
import org.springframework.samples.petclinic.repository.CuidadorRepository;
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
	
	@Autowired
	public AnimalService(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}
	
	@Transactional
	public Collection<Animal> findAll() {
		Collection<Animal> result;
		result = animalRepository.findAll();
		return result;
	}
	
	@Transactional(readOnly = true)
	public Optional<Animal> findAnimalById(int id) throws DataAccessException{
		return animalRepository.findById(id);
	}

	@Transactional
	public void save(@Valid Animal animal) throws DataAccessException{
		//categoriaService.save(animal.getCategoria());
		animalRepository.save(animal);
	}
	
	public void saveEdicion(Animal modifiedAnimal, int animalId) {
		Optional<Animal> animal=findAnimalById(animalId);
		Cuidador c=cuidadorService.findDuenoAdoptivoById(modifiedAnimal.getCuidador().getId());
		CentroDeAdopcion cda=centroDeAdopcionService.findById(modifiedAnimal.getCentroDeAdopcion().getId());
		modifiedAnimal.setId(animalId);
		modifiedAnimal.setCuidador(c);
		modifiedAnimal.setCentroDeAdopcion(cda);
		modifiedAnimal.getCategoria().setId(animal.get().getCategoria().getId());
	save(modifiedAnimal);
	}

}
