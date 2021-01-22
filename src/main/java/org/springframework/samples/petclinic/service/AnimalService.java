
package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.repository.AnimalRepository;
import org.springframework.samples.petclinic.service.exceptions.AforoCentroCompletadoException;
import org.springframework.samples.petclinic.service.exceptions.RatioAnimalesPorCuidadorSuperadoException;
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
	public Optional<Animal> findAnimalById(int id) throws DataAccessException {
		return animalRepository.findById(id);
	}

	public Collection<Animal> findAll() {
		return animalRepository.findAll();
	}

	public Collection<Animal> findAllNoAdopted() {
		return animalRepository.findAllNoAdopted();
	}

	@Transactional
	public void save(@Valid Animal animal) throws AforoCentroCompletadoException {
		// categoriaService.save(animal.getCategoria());

		CentroDeAdopcion c = animal.getCentroDeAdopcion();
		if (c.getAnimales().size() > c.getCantidadMax()) {
			throw new AforoCentroCompletadoException("Este Centro está completo y no acepta mas animales");
		}
		animalRepository.save(animal);

	}

	@Transactional
	public void comprobarRatioCuidador(@Valid Animal animal)
			throws RatioAnimalesPorCuidadorSuperadoException, AforoCentroCompletadoException {
		CentroDeAdopcion centro = animal.getCentroDeAdopcion();
		Collection <Animal> a=findAllNoAdoptedByCentro(centro.getId());
		Integer numeroAnimalesNoAdoptados=a.size();
		Integer cantidadCuidadores = centro.getCuidadores().size();

		if (cantidadCuidadores < 1) {
			throw new RatioAnimalesPorCuidadorSuperadoException("Se ha superado el ratio de Animales por Cuidadores");
		}
		double j = numeroAnimalesNoAdoptados / cantidadCuidadores;
		if(!(a.contains(animal))) {
			j++;
		}
		if (j >15 || cantidadCuidadores < 1) {
			throw new RatioAnimalesPorCuidadorSuperadoException("Se ha superado el ratio de Animales por Cuidadores");
		}
		save(animal);

	}

	public Collection<Animal> findAllNoAdoptedByCentro(Integer centroId) {
		Collection<Animal> result= animalRepository.findAllNoAdoptedByCentro(centroId);
		return result;
	}

	public ArrayList<Integer> listaAuxiliar() {
		ArrayList<Integer> result=new ArrayList<Integer>();
		for (int i=1; i<=10; i++){
			result.add(i); 
		}
		return result;
	}

	public String nuevoNRegistro(String categoria) {
		String result;
		String codigo;
		Random rand = new Random();
		codigo=rand.nextInt(900)+1000+"";
		result=LocalDate.now().getYear()+"-"+categoria.substring(0,2)+"-"+codigo;
		return result;
	}

	public Collection<Animal> findAllNoAdoptados() {
		Collection<Animal> result;
		result=animalRepository.findAllNoAdopted();
		return result;
	}

	public Collection<Animal> findAnimalAsignadoCanino(int cuidadorId) {

		Collection<Animal> result=animalRepository.findAnimalAsignadoCanino(cuidadorId); 
		return result;
	}
	public Collection<Animal> findAnimalAsignadoFelino(int cuidadorId) {

		Collection<Animal> result=animalRepository.findAnimalAsignadoFelino(cuidadorId); 
		return result;
	}
	public Collection<Animal> findAnimalAsignadoReptil(int cuidadorId) {

		Collection<Animal> result=animalRepository.findAnimalAsignadoReptil(cuidadorId); 
		return result;
	}
	public Collection<Animal> findAnimalAsignadoAve(int cuidadorId) {

		Collection<Animal> result=animalRepository.findAnimalAsignadoAve(cuidadorId); 
		return result;
	}


}
