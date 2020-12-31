package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.service.exceptions.EventoSinCuidadoresAsignadosException;
import org.springframework.samples.petclinic.service.exceptions.ExcedidoAforoEventoException;
import org.springframework.samples.petclinic.service.exceptions.SinPermisoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoService {

	private EventoRepository eventoRepository;
	@Autowired
	private DirectorService directorService;
	@Autowired
	private DuenoAdoptivoService duenoAdoptivoService;
	@Autowired
	private UserService userService;

	@Autowired
	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	@Transactional
	public void saveEvento(@Valid Evento evento) {
		Director director = directorService.findDirectorByPrincipal();
		evento.setDirector(director);
		eventoRepository.save(evento);

	}

	public Collection<Evento> findEventosByDirector() {
		Director director = directorService.findDirectorByPrincipal();
		Collection<Evento> result = eventoRepository.findEventosByDirector(director.getId());
		return result;
	}

	public Optional<Evento> findEventoById(int eventoId) {
		Optional<Evento> result;
		result = eventoRepository.findEventoById(eventoId);
		return result;
	}

	public void quitarCuidadorEvento(Evento evento, Cuidador cuidador)
			throws EventoSinCuidadoresAsignadosException, SinPermisoException {

		// Evita que un evento con duenos inscritos pueda quedarse sin cuidadores
		// asignados
		if (evento.getDuenos().size() > 0 && evento.getCuidadores().size() <= 1) {
			throw new EventoSinCuidadoresAsignadosException(
					"Un evento con asistentes no se puede quedar sin cuidadores");
		}
		// Person p = directorService.findPersonByPrincipal();
		// User user = userService.findPrincipal();
		// org.springframework.samples.petclinic.model.User user= p.getUser();
		// String role = user.getAuthorities().toString();
		String role = userService.principalAuthorityString();
		Boolean b = role.contains("director");
		if (!b) {
			throw new SinPermisoException("Terreno del Director");
		}
		evento.getCuidadores().remove(cuidador);
		eventoRepository.save(evento);

	}

	public void añadirCuidadorEvento(Evento evento, Cuidador cuidador) throws SinPermisoException {

		// User user = userService.findPrincipal();
		// String role = user.getAuthorities().toString();
		String role = userService.principalAuthorityString();
		if (!role.contains("director")) {
			throw new SinPermisoException("Terreno del Director");
		}
		evento.getCuidadores().add(cuidador);
		eventoRepository.save(evento);

	}

	public Collection<Evento> findEventosByDueno() {
		DuenoAdoptivo duenoAdoptivo = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		Collection<Evento> result = eventoRepository.findEventosByDuenoAdoptivo(duenoAdoptivo.getId());
		return result;
	}

	// Aquellos con fecha>=now y con algún cuidador asignado
	public Collection<Evento> findEventosDisponibles() {
		LocalDate now = LocalDate.now();
		Collection<Evento> result = eventoRepository.findEventosDisponibles(now);
		return result;
	}

	public void añadirDuenoAdoptivoEvento(Evento evento)
			throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException {

		// Comrpueba que el evento tiene algún Cuidador asignado
		if (evento.getCuidadores().size() == 0) {
			throw new EventoSinCuidadoresAsignadosException("Sin cuidadores Asignados");
		}
		// assertFalse("Sin cuidadores Asignados",e.getCuidadores().size()==0);
		// Comprueba que el aforo no está completo
		if (evento.getAforo() <= evento.getDuenos().size()) {
			throw new ExcedidoAforoEventoException("Aforo Completo");
		}
		// assertTrue("Aforo Completo", e.getAforo()>e.getDuenos().size());
		DuenoAdoptivo d = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();

		evento.getDuenos().add(d);
		eventoRepository.save(evento);

	}

	public void quitarDuenoAdoptivoEvento(Evento evento) {
		DuenoAdoptivo d = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		evento.getDuenos().remove(d);
		eventoRepository.save(evento);

	}
}
