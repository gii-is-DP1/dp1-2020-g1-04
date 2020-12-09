package org.springframework.samples.petclinic.service;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoService {

	
	private EventoRepository eventoRepository;

	@Transactional
	public void saveEvento(@Valid Evento evento) {
		eventoRepository.save(evento);
		
	}
}
