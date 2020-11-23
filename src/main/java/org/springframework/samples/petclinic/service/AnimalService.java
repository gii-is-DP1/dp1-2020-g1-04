
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Animal;
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
	
	public void saveEdicion(Animal modifiedAnimal, int animalId) {
		Optional<Animal> animal=findAnimalById(animalId);
		Cuidador c=cuidadorService.findDuenoAdoptivoById(modifiedAnimal.getCuidador().getId());
		modifiedAnimal.setId(animalId);
		modifiedAnimal.setCuidador(c);
		modifiedAnimal.getCategoria().setId(animal.get().getCategoria().getId());
	save(modifiedAnimal);
	}

}
