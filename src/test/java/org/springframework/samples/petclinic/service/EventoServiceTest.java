package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.BusquedaVaciaException;
import org.springframework.samples.petclinic.service.exceptions.EventoSinCuidadoresAsignadosException;
import org.springframework.samples.petclinic.service.exceptions.ExcedidoAforoEventoException;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EventoServiceTest {

	@Autowired
	protected EventoService eventoService;
	@Autowired
	CuidadorService cuidadorService;

	@MockBean
	private DuenoAdoptivoService duenoAdoptivoService;
	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Test
	void findEventoById() {
		Optional<Evento> evento = eventoService.findEventoById(1);

		assertThat(evento.get().getDirector().getNombre()).isEqualTo("LEO");
	}

	@Test
	void findEventosDisponibles() {
		Collection<Evento> eventos = eventoService.findEventosDisponibles();

		assertThat(eventos.size()).isEqualTo(2);

	}

	// Test negativo
	@Test
	void añadirDuenoAdoptivoEventoSinCuidadoresAsignados() {

		Exception exception = assertThrows(EventoSinCuidadoresAsignadosException.class, () -> {
			Evento evento = eventoService.findEventoById(2).get();
			eventoService.añadirDuenoAdoptivoEvento(evento);
			;
		});

		String expectedMessage = "Sin cuidadores Asignados";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	// Test negativo
	@Test
	void añadirDuenoAdoptivoEventoAforoLleno() {

		Exception exception = assertThrows(ExcedidoAforoEventoException.class, () -> {
			Evento evento = eventoService.findEventoById(3).get();
			eventoService.añadirDuenoAdoptivoEvento(evento);
		});

	}
	/*
	 * @Test void quitarDuenoAdoptivoEventoNoExiste() { Exception exception =
	 * assertThrows(BusquedaVaciaException.class, () -> {
	 * eventoService.quitarDuenoAdoptivoEvento(13); ; });
	 * 
	 * }
	 */

	@Test
	void dejarEventoConDuenosInscritosSinCuidadores() {
		Exception exception = assertThrows(EventoSinCuidadoresAsignadosException.class, () -> {
			Evento evento = eventoService.findEventoById(3).get();
			Cuidador cuidador = cuidadorService.findCuidadorById(1).get();
			eventoService.quitarCuidadorEvento(evento, cuidador);
		});
	}

	@Test
	void añadirDuenoAdoptivoEvento()
			throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException, BusquedaVaciaException {
		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Juan");
		duenoAdoptivo.setApellidos("perez");
		duenoAdoptivo.setDireccion("calle falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
		duenoAdoptivo.setId(66);
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		Authorities authorities = new Authorities();
		authorities.setAuthority("duenoadoptivo");
		duenoAdoptivo.setUser(user);

		// DuenoAdoptivoService duenoAdoptivoService = mock(DuenoAdoptivoService.class);
		// when(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).thenReturn(duenoAdoptivo);
		given(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(duenoAdoptivo);
		Evento evento = eventoService.findEventoById(1).get();
		eventoService.añadirDuenoAdoptivoEvento(evento);

		assertThat(eventoService.findEventoById(1).get().getDuenos().size()).isEqualTo(1);
	}

}
