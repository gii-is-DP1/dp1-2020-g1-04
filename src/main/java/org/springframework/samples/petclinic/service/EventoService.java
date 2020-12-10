package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.AdopcionRepository;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoService {

	private EventoRepository eventoRepository;
	
	@Autowired
	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	
	@Autowired
	private DirectorService directorService;
	

	@Transactional
	public void saveEvento(@Valid Evento evento) {	
		Director director= directorService.findDirectorByPrincipal();
		evento.setDirector(director);
		eventoRepository.save(evento);
		
	}
}
