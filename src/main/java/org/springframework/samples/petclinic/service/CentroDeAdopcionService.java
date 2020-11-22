package org.springframework.samples.petclinic.service;

import java.util.Collection;


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
	 
	 
	@Transactional
	public Collection<CentroDeAdopcion> findAll() {
		Collection<CentroDeAdopcion> result;
		result=centroDeAdopcionRepository.findAll();
		
		return result;
	}

}
