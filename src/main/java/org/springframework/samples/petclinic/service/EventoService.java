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
import org.springframework.samples.petclinic.service.exceptions.BusquedaVaciaException;
import org.springframework.samples.petclinic.service.exceptions.EventoSinCuidadoresAsignadosException;
import org.springframework.samples.petclinic.service.exceptions.ExcedidoAforoEventoException;
import org.springframework.samples.petclinic.service.exceptions.SinPermisoException;
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


	public void quitarCuidadorEvento(int eventoId, int cuidadorId) throws BusquedaVaciaException, EventoSinCuidadoresAsignadosException, SinPermisoException {
		
		
		Optional<Evento> even=eventoRepository.findEventoById(eventoId);
		Boolean b=even.isPresent();
		if(!b) {
			throw new BusquedaVaciaException();
		}
		Evento e=even.get();
		//Evita que un evento con duenos inscritos pueda quedarse sin cuidadores asignados
		if(e.getDuenos().size()>0 && e.getCuidadores().size()<=1) {
			throw new EventoSinCuidadoresAsignadosException("Un evento con asistentes no se puede quedar sin cuidadores");
		}
		Optional<Cuidador> c=cuidadorService.findCuidadorById(cuidadorId);
		Boolean c1=c.isPresent();
		if(!c1) {
			throw new BusquedaVaciaException();
		}
		e.getCuidadores().remove(c.get());
		User user=userService.findPrincipal();
		String role=user.getAuthorities().toString();
		if(role.contains("director")) {
			throw new SinPermisoException("Terreno del Director");
		}
		eventoRepository.save(e);
		
	}


	public void añadirCuidadorEvento(int eventoId, int cuidadorId) throws SinPermisoException, BusquedaVaciaException {
		
		Optional<Evento> even=eventoRepository.findEventoById(eventoId);
		Boolean b=even.isPresent();
		if(!b) {
			throw new BusquedaVaciaException();
		}
		Evento e=even.get();
		Optional<Cuidador> c=cuidadorService.findCuidadorById(cuidadorId);
		Boolean c1=c.isPresent();
		if(!c1) {
			throw new BusquedaVaciaException();
		}
		e.getCuidadores().remove(c.get());
		User user=userService.findPrincipal();
		String role=user.getAuthorities().toString();
		if(role.contains("director")) {
			throw new SinPermisoException("Terreno del Director");
		}
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


	public void añadirDuenoAdoptivoEvento(int eventoId) throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException, BusquedaVaciaException {
		
		Optional<Evento> e=eventoRepository.findEventoById(eventoId);
		//Comprueba que existe un evento con esa id
		Boolean b=e.isPresent();
		if(!b) {
			throw new BusquedaVaciaException();
		}
		Evento evento=e.get();
		//Comrpueba que el evento tiene algún Cuidador asignado
		if(evento.getCuidadores().size()==0) {
			throw new EventoSinCuidadoresAsignadosException("Sin cuidadores Asignados");
		}
		//assertFalse("Sin cuidadores Asignados",e.getCuidadores().size()==0);
		//Comprueba que el aforo no está completo
		
		int aforo=evento.getAforo();
		int duenos=evento.getDuenos().size();
		int plazasLibres=aforo-duenos;
		if(evento.getAforo()<=evento.getDuenos().size()) {
			throw new ExcedidoAforoEventoException("Aforo Completo");
		}
		//assertTrue("Aforo Completo", e.getAforo()>e.getDuenos().size());
		DuenoAdoptivo d=duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		
		evento.getDuenos().add(d);
		eventoRepository.save(evento);
		
	}
	
	public void quitarDuenoAdoptivoEvento(int eventoId) throws BusquedaVaciaException {
		Optional<Evento> e=eventoRepository.findEventoById(eventoId);
		Boolean b=e.isPresent();
		if(!b) {
			throw new BusquedaVaciaException();
		}
		Evento evento=e.get();
		DuenoAdoptivo d=duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		evento.getDuenos().remove(d);
		eventoRepository.save(evento);
		
	}
}
