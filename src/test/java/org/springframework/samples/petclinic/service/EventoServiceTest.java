package org.springframework.samples.petclinic.service;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EventoServiceTest {

	@Autowired
	protected EventoService eventoService;
	
	
	
	@Test
	void findEventoById(){
		Optional<Evento> evento=eventoService.findEventoById(1);
		
		assertThat(evento.get().getDirector().getNombre()).isEqualTo("LEO");
	}
	
	@Test
	void findEventosDisponibles() {
		Collection<Evento> eventos= eventoService.findEventosDisponibles();
		
		assertThat(eventos.size()).isEqualTo(1);
				
	}
}
