package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
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

	private DuenoAdoptivo duenoNuevo() {
		DuenoAdoptivo result = new DuenoAdoptivo();
		result.setNombre("Juan");
		result.setApellidos("perez");
		result.setDireccion("calle falsa");
		result.setDni("1234567H");
		result.setTelefono("666777888");
		result.setEmail("fsfd@dfs.com");
		result.setId(1);
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		Authorities authorities = new Authorities();
		authorities.setAuthority("duenoadoptivo");
		Set<Authorities> a = new HashSet<Authorities>();
		a.add(authorities);
		user.setAuthorities(a);
		result.setUser(user);

		return result;

	}

	private Director directorNuevo() {
		Director director = new Director();
		director.setNombre("Juan");
		director.setApellidos("perez");
		director.setTelefono("666777888");
		director.setEmail("fsfd@dfs.com");
		director.setId(13);
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
		return director;
	}

	@Transactional
	private Evento eventoNuevo() {
		Evento evento = new Evento();
		DuenoAdoptivo dueno = duenoNuevo();
		evento.setAforo(1);
		evento.setDescripcion("des");
		evento.setDireccion("dfdf");
		evento.setFecha(LocalDate.now().withYear(2022));
		evento.setTitulo("tit");
		evento.setDirector(directorService.findDirectorById(1));
		evento.setId(88);
		// Set<Evento> eventos=new HashSet<Evento>();
		// eventos.add(evento);
		// dueno.setEventos(eventos);
		Set<DuenoAdoptivo> duenos = new HashSet<DuenoAdoptivo>();
		duenos.add(dueno);
		evento.setDuenos(duenos);
		return evento;
	}

	@Transactional
	private BDDMyOngoingStubbing<DuenoAdoptivo> duenoAdoptivoMock() {
		DuenoAdoptivo duenoAdoptivo = duenoNuevo();
		int i = duenoAdoptivo.getId();
		return given(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(duenoAdoptivo);
	}

	private BDDMyOngoingStubbing<Director> directorMock() {
		Director director = directorNuevo();

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
		DuenoAdoptivo dueno = duenoNuevo();
		Exception exception = assertThrows(EventoSinCuidadoresAsignadosException.class, () -> {
			Evento evento = eventoService.findEventoById(2).get();
			eventoService.añadirDuenoAdoptivoEvento(evento, dueno);
			;
		});

		String expectedMessage = "Sin cuidadores Asignados";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	// Test negativo
	@Test
	void añadirDuenoAdoptivoEventoAforoLleno() {
		DuenoAdoptivo dueno = duenoNuevo();
		Exception exception = assertThrows(ExcedidoAforoEventoException.class, () -> {
			Evento evento = eventoService.findEventoById(3).get();
			eventoService.añadirDuenoAdoptivoEvento(evento, dueno);
		});

	}

	@Test
	void añadirDuenoAdoptivoEvento()
			throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException, BusquedaVaciaException {
		duenoAdoptivoMock();
		DuenoAdoptivo dueno = duenoNuevo();
		Evento evento = eventoService.findEventoById(1).get();
		eventoService.añadirDuenoAdoptivoEvento(evento, dueno);

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

	// queda sin valor al cambiar las comprobaciones al controlador
//	@Test
//	void quitarCuidadorSinSerDirector() {
//		duenoStringMock();
//		Exception exception = assertThrows(SinPermisoException.class, () -> {
//			Evento evento = eventoService.findEventoById(1).get();
//			Cuidador cuidador = cuidadorService.findCuidadorById(1).get();
//			eventoService.quitarCuidadorEvento(evento, cuidador);
//		});
//	}

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

	// queda sin valor al cambiar las comprobaciones al controlador
//	@Test
//	@Transactional
//	void añadirCuidadorEventoSinSerDirector() {
//		duenoStringMock();
//		Exception exception = assertThrows(SinPermisoException.class, () -> {
//			Evento evento = eventoService.findEventoById(1).get();
//			Cuidador cuidador = cuidadorService.findCuidadorById(3).get();
//			eventoService.añadirCuidadorEvento(evento, cuidador);
//		});
//	}

	// quitarDuenoAdoptivoEvento
	@Test
	@Transactional
	void quitarDuenoAdoptivoEvento() {
		Evento evento = eventoService.findEventoById(1).get();
		DuenoAdoptivo dueno = duenoNuevo();
		evento.getDuenos().add(dueno);
		// eventoService.saveEvento(evento);

		eventoService.quitarDuenoAdoptivoEvento(evento, dueno);
		// al estar creado el MockBean de duenoAdoptivo, no es capaz de recuperar un
		// duenoAdoptivo del repositorio ni creando otro duenoAdoptivoService
	}

	// findAllByPrincipalDueno
	@Test
	@Transactional
	void findAllByPrincipalDueno() {
		duenoAdoptivoMock();
		DuenoAdoptivo dueno = duenoNuevo();
		assertThat(eventoService.findEventosByDueno(dueno.getId()).size()).isEqualTo(1);
	}

	// deleteEvento
	@Test
	@Transactional
	void deleteEvento() throws SinPermisoException {
		directorStringMock();
		Evento evento = eventoService.findEventoById(1).get();
		eventoService.deleteEvento(evento);

		Optional<Evento> aux = eventoService.findEventoById(1);
		boolean b = aux.isPresent();
		assertThat(aux.isPresent()).isEqualTo(false);

	}

	// queda sin valor al cambiar las comprobaciones al controlador
//	// deleteEventoNegative
//	@Test
//	@Transactional
//	void deleteEventoSiendoDueno() throws SinPermisoException {
//		duenoStringMock();
//		Evento evento = eventoService.findEventoById(1).get();
//		Exception exception = assertThrows(SinPermisoException.class, () -> {
//			eventoService.deleteEvento(evento);
//		});
//
//	}

	// findEventosByDirector
	@Test
	@Transactional
	void findEventosByDirector() {
		directorMock();
		Director director = directorNuevo();
		assertThat(eventoService.findEventosByDirector(director.getId()).size()).isEqualTo(3);
	}

	// save
	@Test
	@Transactional
	void saveEvento() {
		directorMock();
		Director director = directorNuevo();
		Evento evento = eventoNuevo();
		evento.setDuenos(null);
		evento.setDirector(director);
		int i = eventoService.findEventosByDirector(director.getId()).size();
		eventoService.saveEvento(evento);
		int j = eventoService.findEventosByDirector(director.getId()).size();
		Boolean b = i + 1 == j;
		assertThat(b).isEqualTo(true);
	}

}
