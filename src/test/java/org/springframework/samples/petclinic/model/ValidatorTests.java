package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.service.DirectorService;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {

	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	private DuenoAdoptivo crearDuenoAdoptivo() {
		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Juan");
		duenoAdoptivo.setApellidos("perez");
		duenoAdoptivo.setDireccion("calle falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user); 
                
                return duenoAdoptivo;
	}

	@Test
	void shouldNotValidateDuenoAdoptivoWhenNombreEmpty() {

		DuenoAdoptivo duenoAdoptivo=crearDuenoAdoptivo();
		duenoAdoptivo.setNombre("");

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenApellidosEmpty() {

		DuenoAdoptivo duenoAdoptivo=crearDuenoAdoptivo();
		duenoAdoptivo.setApellidos("");

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("apellidos");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenDireccionEmpty() {

		DuenoAdoptivo duenoAdoptivo=crearDuenoAdoptivo();
		duenoAdoptivo.setDireccion("");
		

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("direccion");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenTelefonoEmpty() {

		DuenoAdoptivo duenoAdoptivo=crearDuenoAdoptivo();
		duenoAdoptivo.setTelefono("");  

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhendniEmpty() {

		DuenoAdoptivo duenoAdoptivo=crearDuenoAdoptivo();
		duenoAdoptivo.setDni("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}

	@Test
	void shouldNotValidateWhenEmailEmpty() {

		DuenoAdoptivo duenoAdoptivo=crearDuenoAdoptivo();
		duenoAdoptivo.setEmail("");
               
		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenEmailNotEmail() {

		DuenoAdoptivo duenoAdoptivo=crearDuenoAdoptivo();
		duenoAdoptivo.setEmail("sdfsd");
                
		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("no es una dirección de correo bien formada");
	}
	
	private Cuidador crearCuidador() {
		Cuidador cuidador = new Cuidador();
		cuidador.setNombre("Juan");
		cuidador.setApellidos("perez");
		cuidador.setDni("1234567H");
		cuidador.setTelefono("666777888");
		cuidador.setEmail("fsfd@dfs.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                cuidador.setUser(user); 
                
                return cuidador;
	}
	
	@Test
	void shouldNotValidateDuenoAdoptivoWhenNombreEmptyCuidador() {

		Cuidador cuidador=crearCuidador();
		cuidador.setNombre("");

		Validator validator = createValidator();
		Set<ConstraintViolation<Cuidador>> constraintViolations = validator.validate(cuidador);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cuidador> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenApellidosEmptyCuidador() {

		Cuidador cuidador=crearCuidador();
		cuidador.setApellidos("");

		Validator validator = createValidator();
		Set<ConstraintViolation<Cuidador>> constraintViolations = validator.validate(cuidador);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cuidador> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("apellidos");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
		
	@Test
	void shouldNotValidateWhenTelefonoEmptyCuidador() {

		Cuidador cuidador=crearCuidador();
		cuidador.setTelefono("");  

		Validator validator = createValidator();
		Set<ConstraintViolation<Cuidador>> constraintViolations = validator.validate(cuidador);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cuidador> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhendniEmptyCuidador() {

		Cuidador cuidador=crearCuidador();
		cuidador.setDni("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cuidador>> constraintViolations = validator.validate(cuidador);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cuidador> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}

	@Test
	void shouldNotValidateWhenEmailEmptyCuidador() {

		Cuidador cuidador=crearCuidador();
		cuidador.setEmail("");
               
		Validator validator = createValidator();
		Set<ConstraintViolation<Cuidador>> constraintViolations = validator.validate(cuidador);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cuidador> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenEmailNotEmailCuidador() {

		Cuidador cuidador=crearCuidador();
		cuidador.setEmail("sdfsd");
                
		Validator validator = createValidator();
		Set<ConstraintViolation<Cuidador>> constraintViolations = validator.validate(cuidador);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cuidador> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("no es una dirección de correo bien formada");
	}
	
	private Director crearDirector() {
		Director director=new Director();
		director.setNombre("director");
		director.setApellidos("apellido1 apellido2");
		director.setEmail("email@us.es");
		director.setTelefono("666444333");
		 User user=new User();
         user.setUsername("Sam");
         user.setPassword("supersecretpassword");
         user.setEnabled(true);
         director.setUser(user);
		return director; 
	}
	
	private Evento crearEvento() {
		
		Evento evento = new Evento();
		evento.setTitulo("Titulo");
		LocalDate d=LocalDate.of(2021, 12, 14);
		evento.setFecha(d);
		evento.setDireccion("calle falsa");
		evento.setAforo(1);
		evento.setDescripcion("descripción");
		evento.setDirector(crearDirector());
			return evento;
	}
	
	
	@Test
	void shouldNotValidateEventoWhenTituloEmpty() {

		Evento evento=crearEvento();
		evento.setTitulo("");

		Validator validator = createValidator();
		Set<ConstraintViolation<Evento>> constraintViolations = validator.validate(evento);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Evento> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("titulo");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}

	@Test
	void shouldNotValidateEventoWhenFechaEmpty() {

		Evento evento=crearEvento();
		evento.setFecha(null);

		Validator validator = createValidator();
		Set<ConstraintViolation<Evento>> constraintViolations = validator.validate(evento);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Evento> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("fecha");
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");
	}
	
	
	//No funciona al quitar @Future
//	@Test
//	void shouldNotValidateEventoWhenFechaPast() {
//
//		Evento evento=crearEvento();
//		LocalDate d=LocalDate.of(2019, 12, 14);
//		evento.setFecha(d);
//
//		Validator validator = createValidator();
//		Set<ConstraintViolation<Evento>> constraintViolations = validator.validate(evento);
//
//		assertThat(constraintViolations.size()).isEqualTo(1);
//		ConstraintViolation<Evento> violation = constraintViolations.iterator().next();
//		
//		assertThat(violation.getPropertyPath().toString()).isEqualTo("fecha");
//		assertThat(violation.getMessage()).isEqualTo("tiene que ser una fecha en el futuro");
//	}
	
	@Test
	void shouldNotValidateEventoWhenDireccionEmpty() {

		Evento evento=crearEvento();
		evento.setDireccion("");

		Validator validator = createValidator();
		Set<ConstraintViolation<Evento>> constraintViolations = validator.validate(evento);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Evento> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("direccion");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateEventoWhenAforoEmpty() {

		Evento evento=crearEvento();
		evento.setAforo(null);

		Validator validator = createValidator();
		Set<ConstraintViolation<Evento>> constraintViolations = validator.validate(evento);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Evento> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("aforo");
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");
	}
	
	@Test
	void shouldNotValidateEventoWhenDescripcionEmpty() {

		Evento evento=crearEvento();
		evento.setDescripcion("");

		Validator validator = createValidator();
		Set<ConstraintViolation<Evento>> constraintViolations = validator.validate(evento);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Evento> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcion");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
}
