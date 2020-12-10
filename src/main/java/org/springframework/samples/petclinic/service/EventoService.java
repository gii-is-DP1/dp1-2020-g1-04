package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
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

import junit.framework.Assert;

@Service
public class EventoService {

	private EventoRepository eventoRepository;
	
	@Autowired
	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	
	@Autowired
	private DirectorService directorService;
	@Autowired
	private CuidadorService cuidadorService;
	@Autowired
	private DuenoAdoptivoService duenoAdoptivoService;
	

	@Transactional
	public void saveEvento(@Valid Evento evento) {	
		Director director= directorService.findDirectorByPrincipal();
		evento.setDirector(director);
		eventoRepository.save(evento);
		
	}


	public Collection<Evento> findEventosByDirector() {
		Director director=directorService.findDirectorByPrincipal();
		Collection<Evento>result=eventoRepository.findEventosByDirector(director.getId());
		return result;
	}


	public Optional<Evento> findEventoById(int eventoId) {
		Optional<Evento> result;
		result=eventoRepository.findEventoById(eventoId);
		return result;
	}


	public void quitarCuidadorEvento(int eventoId, int cuidadorId) {
		Evento e=eventoRepository.findEventoById(eventoId).get();
		Cuidador c=cuidadorService.findCuidadorById(cuidadorId).get();
		e.getCuidadores().remove(c);
		eventoRepository.save(e);
		
	}


	public void añadirCuidadorEvento(int eventoId, int cuidadorId) {
		Evento e=eventoRepository.findEventoById(eventoId).get();
		Cuidador c=cuidadorService.findCuidadorById(cuidadorId).get();
		e.getCuidadores().add(c);
		eventoRepository.save(e);
		
	}


	public Collection<Evento> findEventosByDueno() {
		DuenoAdoptivo duenoAdoptivo=duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		Collection<Evento>result=eventoRepository.findEventosByDuenoAdoptivo(duenoAdoptivo.getId());
		return result;
	}


	public Collection<Evento> findEventosDisponibles() {
		Collection<Evento>result=eventoRepository.findEventosDisponibles();
		return result;
	}


	public void añadirDuenoAdoptivoEvento(int eventoId) {
		
		Evento e=eventoRepository.findEventoById(eventoId).get();
		assertTrue("Aforo Completo", e.getAforo()>e.getDuenos().size());
		DuenoAdoptivo d=duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		
		e.getDuenos().add(d);
		eventoRepository.save(e);
		
	}
	
	public void quitarDuenoAdoptivoEvento(int eventoId) {
		Evento e=eventoRepository.findEventoById(eventoId).get();
		DuenoAdoptivo d=duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		e.getDuenos().remove(d);
		eventoRepository.save(e);
		
	}
}
