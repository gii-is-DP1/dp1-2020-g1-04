package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuidador;
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

	private final EventoRepository eventoRepository;

	@Autowired
	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;

	}

	@Transactional
	public void saveEvento(Evento evento) {
		eventoRepository.save(evento);

	}

	@Transactional(readOnly = true)
	public Collection<Evento> findEventosByDirector(Integer directorId) {
		Collection<Evento> result = eventoRepository.findEventosByDirector(directorId);
		return result;
	}

	@Transactional(readOnly = true)
	public Optional<Evento> findEventoById(int eventoId) {
		Optional<Evento> result;
		result = eventoRepository.findEventoById(eventoId);
		return result;
	}

	@Transactional
	public void quitarCuidadorEvento(Evento evento, Cuidador cuidador)
			throws EventoSinCuidadoresAsignadosException, SinPermisoException {

		// Evita que un evento con duenos inscritos pueda quedarse sin cuidadores
		// asignados
		if (evento.getDuenos().size() > 0 && evento.getCuidadores().size() <= 1) {
			throw new EventoSinCuidadoresAsignadosException(
					"Un evento con asistentes no se puede quedar sin cuidadores");
		}
		evento.getCuidadores().remove(cuidador);
		eventoRepository.save(evento);

	}

	@Transactional
	public void añadirCuidadorEvento(Evento evento, Cuidador cuidador) throws SinPermisoException {

		evento.getCuidadores().add(cuidador);
		eventoRepository.save(evento);

	}

	@Transactional(readOnly = true)
	public Collection<Evento> findEventosByDueno(Integer i) {
		Collection<Evento> result = eventoRepository.findEventosByDuenoAdoptivo(i);
		return result;
	}

	// Aquellos con fecha>=now y con algún cuidador asignado
	@Transactional(readOnly = true)
	public Collection<Evento> findEventosDisponibles() {
		LocalDate now = LocalDate.now();
		Collection<Evento> result = eventoRepository.findEventosDisponibles(now);
		return result;
	}

	@Transactional
	public void añadirDuenoAdoptivoEvento(Evento evento, DuenoAdoptivo d)
			throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException {

		// Comrpueba que el evento tiene algún Cuidador asignado
		if (evento.getCuidadores().size() == 0) {
			throw new EventoSinCuidadoresAsignadosException("Sin cuidadores Asignados");
		}
		// Comprueba que el aforo no está completo
		if (evento.getAforo() <= evento.getDuenos().size()) {
			throw new ExcedidoAforoEventoException("Aforo Completo");
		}

		evento.getDuenos().add(d);
		eventoRepository.save(evento);

	}

	@Transactional
	public void quitarDuenoAdoptivoEvento(Evento evento, DuenoAdoptivo d) {
		evento.getDuenos().remove(d);
		eventoRepository.save(evento);

	}

	@Transactional
	public void deleteEvento(Evento evento) {
		evento.setCuidadores(null);
		evento.setDuenos(null);
		eventoRepository.save(evento);
		eventoRepository.delete(evento);

	}

	@Transactional(readOnly = true)
	public Collection<Evento> findEventosByCuidador(Integer cuidadorId) {
		Collection<Evento> result = eventoRepository.findEventosByCuidador(cuidadorId);
		return result;
	}

}
