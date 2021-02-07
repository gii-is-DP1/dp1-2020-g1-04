package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Enfermedad;
import org.springframework.samples.petclinic.repository.EnfermedadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnfermedadService {

	private final EnfermedadRepository enfermedadRepository;
	
	@Autowired
	public EnfermedadService(EnfermedadRepository enfermedadRepository) {
		this.enfermedadRepository = enfermedadRepository;
		
	}
	
	@Transactional
	public void saveEnfermedad(Enfermedad enfermedad) {
		
		enfermedadRepository.save(enfermedad);
	}

	@Transactional(readOnly = true)
	public Optional<Enfermedad> findEnfermedadById(int enfermedadId) {
		Optional<Enfermedad> result;
		result=enfermedadRepository.findEnfermedadById(enfermedadId);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Enfermedad> findAllEnfermedadByAnimalId(int animalId) {
		Collection <Enfermedad> enfermedades;
		enfermedades=enfermedadRepository.findAllEnfermedadeByAnimalId(animalId);
		return enfermedades;
	}
	
	@Transactional(readOnly = true)
	public Collection<Enfermedad> findAllEnfermedadAnimalByCuidadorId(int cuidadorId) {
		Collection <Enfermedad> enfermedades;
		enfermedades=enfermedadRepository.findAllEnfermedadeAnimalByCuidador(cuidadorId);
		return enfermedades;
	}

}
