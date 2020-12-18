package org.springframework.samples.petclinic.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.BusquedaVaciaException;
import org.springframework.samples.petclinic.service.exceptions.EventoSinCuidadoresAsignadosException;
import org.springframework.samples.petclinic.service.exceptions.ExcedidoAforoEventoException;
import org.springframework.stereotype.Service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.samples.petclinic.util.EntityUtils;

import aj.org.objectweb.asm.ClassTooLargeException;

@ExtendWith(MockitoExtension.class)
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
		
		assertThat(eventos.size()).isEqualTo(2);
				
	}
	
	//Test negativo
	@Test
	void añadirDuenoAdoptivoEventoNoExiste() {
		try {
			eventoService.añadirDuenoAdoptivoEvento(3);
		}catch(BusquedaVaciaException | EventoSinCuidadoresAsignadosException  | ExcedidoAforoEventoException e) {
			e.printStackTrace();
		}
			  
	
			 Assertions.assertThrows(BusquedaVaciaException.class, () ->{
				 eventoService.añadirDuenoAdoptivoEvento(4);
	});	
	
	}
	
	//Test negativo
	@Test
	void añadirDuenoAdoptivoEventoSinCuidadoresAsignados() {
		
		Exception exception = assertThrows(EventoSinCuidadoresAsignadosException.class, () -> {
			 eventoService.añadirDuenoAdoptivoEvento(2); ;
		    });

		    String expectedMessage = "Sin cuidadores Asignados";
		    String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
		}	
	
	//Test negativo
	@Test
	void añadirDuenoAdoptivoEventoAforoLleno() {
		
		Exception exception = assertThrows(ExcedidoAforoEventoException.class, () -> {
			 eventoService.añadirDuenoAdoptivoEvento(3); ;
		    });

		}
	
	@Test
	void quitarDuenoAdoptivoEventoNoExiste() {
		Exception exception = assertThrows(BusquedaVaciaException.class, () -> {
			 eventoService.quitarDuenoAdoptivoEvento(13); ;
		    });

		}
	
	@Test
	void añadirCuidadorEventoNoExiste() {
		Exception exception = assertThrows(BusquedaVaciaException.class, () -> {
			 eventoService.añadirCuidadorEvento(13,1); ;
		    });

	}
	@Test
	void añadirCuidadorNoExisteEvento() {
		Exception exception = assertThrows(BusquedaVaciaException.class, () -> {
			 eventoService.añadirCuidadorEvento(2,67); ;
		    });

	}
	
	@Test
	void quitarCuidadorEventoNoExiste() {
		Exception exception = assertThrows(BusquedaVaciaException.class, () -> {
			 eventoService.quitarCuidadorEvento(13,1); ;
		    });
	}
	
	@Test
	void dejarEventoConDuenosInscritosSinCuidadores() {
		Exception exception = assertThrows(EventoSinCuidadoresAsignadosException.class, () -> {
			 eventoService.quitarCuidadorEvento(3,1);
		    });
	}
	
	@Test
	void quitarCuidadorNoExisteEvento() {
		Exception exception = assertThrows(BusquedaVaciaException.class, () -> {
			 eventoService.quitarCuidadorEvento(1,19); ;
		    });
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Test
	void añadirDuenoAdoptivoEvento() throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException, BusquedaVaciaException {
		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Juan");
		duenoAdoptivo.setApellidos("perez");
		duenoAdoptivo.setDireccion("calle falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
		duenoAdoptivo.setId(66);
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                Authorities authorities=new Authorities();
                authorities.setAuthority("duenoadoptivo");
        duenoAdoptivo.setUser(user);
	
		DuenoAdoptivoService duenoAdoptivoService = mock(DuenoAdoptivoService.class);
		when(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).thenReturn(duenoAdoptivo);
		
		eventoService.añadirDuenoAdoptivoEvento(1);
		}
		
	*/
}
