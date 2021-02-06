package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.repository.CentroDeAdopcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CentroDeAdopcionService {

	private CentroDeAdopcionRepository centroDeAdopcionRepository;

	@Autowired
	public CentroDeAdopcionService(CentroDeAdopcionRepository centroDeAdopcionRepository) {
		this.centroDeAdopcionRepository = centroDeAdopcionRepository;
	}

	@Transactional(readOnly = true)
	public Collection<CentroDeAdopcion> findAll() {
		Collection<CentroDeAdopcion> result;
		result = centroDeAdopcionRepository.findAll();

		return result;
	}
	
	@Transactional(readOnly = true)
	public Collection<CentroDeAdopcion> findAllNoEstenLlenos() {
		Collection<CentroDeAdopcion> result;
		result = centroDeAdopcionRepository.findAllNoEstenLlenos();

		return result;
	}

	@Transactional(readOnly = true)
	public CentroDeAdopcion findById(int centroId) {
		Optional<CentroDeAdopcion> result;
		result = centroDeAdopcionRepository.findById(centroId);
		return result.get();
	}
	

}
