package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Enfermedad;
import org.springframework.samples.petclinic.repository.EnfermedadRepository;
import org.springframework.stereotype.Service;

@Service
public class EnfermedadService {

	private final EnfermedadRepository enfermedadRepository;
	
	@Autowired
	public EnfermedadService(EnfermedadRepository enfermedadRepository) {
		this.enfermedadRepository = enfermedadRepository;
		
	}
	
	public void saveEnfermedad(@Valid Enfermedad enfermedad) {
		
		enfermedadRepository.save(enfermedad);
	}

	public Optional<Enfermedad> findEnfermedadById(int enfermedadId) {
		Optional<Enfermedad> result;
		result=enfermedadRepository.findEnfermedadById(enfermedadId);
		return result;
	}

}
