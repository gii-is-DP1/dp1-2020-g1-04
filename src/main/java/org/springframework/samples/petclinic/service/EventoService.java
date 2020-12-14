package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
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
import org.springframework.security.core.userdetails.User;
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
	@Autowired
	private CuidadorService cuidadorService;
	@Autowired
	private DuenoAdoptivoService duenoAdoptivoService;
	@Autowired
	private UserService userService;

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
		User user=userService.findPrincipal();
		String role=user.getAuthorities().toString();
		assertTrue("Terreno del Director",role.contains("director"));
		Evento e=eventoRepository.findEventoById(eventoId).get();
		Cuidador c=cuidadorService.findCuidadorById(cuidadorId).get();
		e.getCuidadores().remove(c);
		eventoRepository.save(e);
		
	}


	public void añadirCuidadorEvento(int eventoId, int cuidadorId) {
		User user=userService.findPrincipal();
		String role=user.getAuthorities().toString();
		assertTrue("Terreno del Director",role.contains("director"));
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

	//Aquellos con fecha>=now y con algún cuidador asignado
	public Collection<Evento> findEventosDisponibles() {
		LocalDate now=LocalDate.now();
		Collection<Evento>result=eventoRepository.findEventosDisponibles(now);
		return result;
	}


	public void añadirDuenoAdoptivoEvento(int eventoId) {
		
		Evento e=eventoRepository.findEventoById(eventoId).get();
		assertFalse("Sin cuidadores Asignados",e.getCuidadores().size()==0);
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
