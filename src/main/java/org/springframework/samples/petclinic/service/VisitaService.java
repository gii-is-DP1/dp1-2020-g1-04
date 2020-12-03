package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.VisitaRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitaService {
	
private VisitaRepository visitaRepository;
	
	
	@Autowired
	public VisitaService(VisitaRepository visitaRepository) {
		this.visitaRepository = visitaRepository;
	}

}
