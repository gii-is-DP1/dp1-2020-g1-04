package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.BusquedaVaciaException;
import org.springframework.samples.petclinic.service.exceptions.EventoSinCuidadoresAsignadosException;
import org.springframework.samples.petclinic.service.exceptions.ExcedidoAforoEventoException;
import org.springframework.samples.petclinic.service.exceptions.SinPermisoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EventoServiceTest {

	@Autowired
	protected EventoService eventoService;
	@Autowired
	protected CuidadorService cuidadorService;
	// @Autowired
	// protected DuenoAdoptivoService duenoAdoptivoService2;
	@MockBean
	private DuenoAdoptivoService duenoAdoptivoService;
	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;
	@MockBean
	private DirectorService directorService;

	private BDDMyOngoingStubbing<DuenoAdoptivo> duenoAdoptivoMock() {
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
		Set<Authorities> a = new HashSet<Authorities>();
		a.add(authorities);
		user.setAuthorities(a);
		duenoAdoptivo.setUser(user);

		return given(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(duenoAdoptivo);
	}

	private BDDMyOngoingStubbing<Director> directorMock() {
		Director director = new Director();
		director.setNombre("Juan");
		director.setApellidos("perez");
		director.setTelefono("666777888");
		director.setEmail("fsfd@dfs.com");
		director.setId(66);
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		Authorities authorities = new Authorities();
		authorities.setAuthority("director");
		Set<Authorities> a = new HashSet<Authorities>();
		a.add(authorities);
		user.setAuthorities(a);
		director.setUser(user);

		return given(directorService.findDirectorByPrincipal()).willReturn(director);
	}

	private BDDMyOngoingStubbing<String> directorStringMock() {

		return given(userService.principalAuthorityString()).willReturn("director");
	}

	private BDDMyOngoingStubbing<String> duenoStringMock() {

		return given(userService.principalAuthorityString()).willReturn("duenoadoptivo");
	}

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

	@Test
	void añadirDuenoAdoptivoEvento()
			throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException, BusquedaVaciaException {
		duenoAdoptivoMock();

		Evento evento = eventoService.findEventoById(1).get();
		eventoService.añadirDuenoAdoptivoEvento(evento);

		assertThat(eventoService.findEventoById(1).get().getDuenos().size()).isEqualTo(1);
	}

	// Test quitarCuidadorEvento

	@Test
	@Transactional
	void quitarCuidadorEvento() throws EventoSinCuidadoresAsignadosException, SinPermisoException {
		directorStringMock();
		Evento evento = eventoService.findEventoById(1).get();
		Cuidador cuidador = cuidadorService.findCuidadorById(1).get();

		eventoService.quitarCuidadorEvento(evento, cuidador);

		assertThat(eventoService.findEventoById(1).get().getCuidadores().size()).isEqualTo(1);
	}

	@Test
	void dejarEventoConDuenosInscritosSinCuidadores() {
		Exception exception = assertThrows(EventoSinCuidadoresAsignadosException.class, () -> {
			Evento evento = eventoService.findEventoById(3).get();
			Cuidador cuidador = cuidadorService.findCuidadorById(1).get();
			eventoService.quitarCuidadorEvento(evento, cuidador);
		});
	}

	@Test
	void quitarCuidadorSinSerDirector() {
		duenoStringMock();
		Exception exception = assertThrows(SinPermisoException.class, () -> {
			Evento evento = eventoService.findEventoById(1).get();
			Cuidador cuidador = cuidadorService.findCuidadorById(1).get();
			eventoService.quitarCuidadorEvento(evento, cuidador);
		});
	}

	// AñadirCuidadorEvento

	@Test
	@Transactional
	void añadirCuidadorEvento() throws SinPermisoException {
		directorStringMock();

		Evento evento = eventoService.findEventoById(1).get();
		Cuidador cuidador = cuidadorService.findCuidadorById(3).get();

		eventoService.añadirCuidadorEvento(evento, cuidador);

		assertThat(eventoService.findEventoById(1).get().getCuidadores().size()).isEqualTo(3);

	}

	@Test
	@Transactional
	void añadirCuidadorEventoSinSerDirector() {
		duenoStringMock();
		Exception exception = assertThrows(SinPermisoException.class, () -> {
			Evento evento = eventoService.findEventoById(1).get();
			Cuidador cuidador = cuidadorService.findCuidadorById(3).get();
			eventoService.añadirCuidadorEvento(evento, cuidador);
		});
	}

	// quitarDuenoAdoptivoEvento
	@Test
	@Transactional
	void quitarDuenoAdoptivoEvento(){
		// al estar creado el MockBean de duenoAdoptivo, no es capaz de recuperar un
		// duenoAdoptivo del repositorio ni creando otro duenoAdoptivoService
	}
}
