
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
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.samples.petclinic.repository.AnimalRepository;
import org.springframework.samples.petclinic.service.exceptions.AforoCentroCompletadoException;
import org.springframework.samples.petclinic.service.exceptions.RatioAnimalesPorCuidadorSuperadoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnimalService {

	private final AnimalRepository animalRepository;

	private static final double RATIO_CUIDADORES = 15;
	private static final double MINIMO_CUIDADORES = 1;

	@Autowired
	public AnimalService(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	@Transactional(readOnly = true)
	public Optional<Animal> findAnimalById(int id) throws DataAccessException {
		return animalRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAll() {
		return animalRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAllNoAdopted() {
		return animalRepository.findAllNoAdopted();
	}

	@Transactional
	public void save(Animal animal) throws RatioAnimalesPorCuidadorSuperadoException, AforoCentroCompletadoException {
		comprobarRatioCuidador(animal);
		animalRepository.save(animal);

	}
	@Transactional
	public void saveAdoptado(Animal animal) {
		animalRepository.save(animal);

	}

	@Transactional
	public boolean comprobarAforo(CentroDeAdopcion centro) {
		boolean res = true;
		res = cantidadDeAnimalesActualEnCentro(centro.getId()) > centro.getCantidadMax();
		return res;

	}

	@Transactional(rollbackFor={RatioAnimalesPorCuidadorSuperadoException.class, AforoCentroCompletadoException.class})
	public void comprobarRatioCuidador(Animal animal)
			throws RatioAnimalesPorCuidadorSuperadoException, AforoCentroCompletadoException {
		CentroDeAdopcion centro = animal.getCentroDeAdopcion();
		Collection<Animal> a = findAllNoAdoptedByCentro(centro.getId());
		double numeroAnimalesNoAdoptados = a.size();
		double cantidadCuidadores = centro.getCuidadores().size();
		double j;
		boolean aux;
		boolean aux2;
		if (cantidadCuidadores < MINIMO_CUIDADORES) {
			throw new RatioAnimalesPorCuidadorSuperadoException("Se ha superado el ratio de Animales por Cuidadores");
		}
		j = numeroAnimalesNoAdoptados / cantidadCuidadores;
		aux2=comprobarColeccion(centro.getId(), animal.getId())>0;
		if (aux2) {
			j = (numeroAnimalesNoAdoptados - 1) / cantidadCuidadores;
		}
		aux = j > RATIO_CUIDADORES;
		if (aux) {
			throw new RatioAnimalesPorCuidadorSuperadoException("Se ha superado el ratio de Animales por Cuidadores");
		}
		if (cantidadCuidadores < MINIMO_CUIDADORES) {
			throw new RatioAnimalesPorCuidadorSuperadoException("Se ha superado el ratio de Animales por Cuidadores");
		}
		if (comprobarAforo(centro)) {
			throw new AforoCentroCompletadoException("Este Centro está completo y no acepta mas animales");
		}

	}

	private int comprobarColeccion(Integer centroId, Integer animalId) {
		int result;
		
		result = animalRepository.comprobarColeccion(centroId,animalId);
		
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAllNoAdoptedByCentro(Integer centroId) {
		Collection<Animal> result = animalRepository.findAllNoAdoptedByCentro(centroId);
		return result;
	}

	public ArrayList<Integer> listaAuxiliar() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			result.add(i);
		}
		return result;
	}

	public String nuevoNRegistro(String categoria) {
		String result;
		String codigo;
		Random rand = new Random();
		codigo = rand.nextInt(900) + 1000 + "";
		result = LocalDate.now().getYear() + "-" + categoria.substring(0, 2) + "-" + codigo;
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAllNoAdoptados() {
		Collection<Animal> result;
		result = animalRepository.findAllNoAdopted();
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAnimalAsignadoCanino(int cuidadorId) {

		Collection<Animal> result = animalRepository.findAnimalAsignadoCanino(cuidadorId);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAnimalAsignadoFelino(int cuidadorId) {

		Collection<Animal> result = animalRepository.findAnimalAsignadoFelino(cuidadorId);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAnimalAsignadoReptil(int cuidadorId) {

		Collection<Animal> result = animalRepository.findAnimalAsignadoReptil(cuidadorId);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Animal> findAnimalAsignadoAve(int cuidadorId) {

		Collection<Animal> result = animalRepository.findAnimalAsignadoAve(cuidadorId);
		return result;
	}
	@Transactional(readOnly = true)
	public Collection<Animal> findAnimalAsignadoTipo(Tipo tipo, int cuidadorId) {

		Collection<Animal> result = animalRepository.findAnimalAsignadoTipo(tipo, cuidadorId);
		return result;
	}
	

	@Transactional
	public int cantidadDeAnimalesActualEnCentro(int centroId) {
		int result;
		result = animalRepository.cantidadDeAnimalesActualEnCentro(centroId);

		return result;
	}

	public Animal inicializarAnimal(Categoria categoria, Animal animal) {
		LocalDate now = LocalDate.now();
		animal.setAdoptado(false);
		animal.setFechaPrimeraIncorporacion(now);
		animal.setFechaUltimaIncorporacion(now);
		String nRgistro = nuevoNRegistro(categoria.getTipo().toString());
		animal.setNumeroRegistro(nRgistro);
		animal.setCategoria(categoria);

		return animal;
	}

	public Animal reincorporarAnimal(Animal animal) {
		animal.setAdoptado(false);
		LocalDate now = LocalDate.now();
		animal.setFechaUltimaIncorporacion(now);

		return animal;
	}

	public void adoptado(Animal animal) {
		animal.setAdoptado(true);
		saveAdoptado(animal);
		
	}

}
