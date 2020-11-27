package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
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

	@Test
	void shouldNotValidateDuenoAdoptivoWhenNombreEmpty() {

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("");
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

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenApellidosEmpty() {

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Simon");
		duenoAdoptivo.setApellidos("");
		duenoAdoptivo.setDireccion("calle falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);   

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("apellidos");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenDireccionEmpty() {

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Simon");
		duenoAdoptivo.setApellidos("Alcaraz");
		duenoAdoptivo.setDireccion("");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);   

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("direccion");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenTelefonoEmpty() {

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Simon");
		duenoAdoptivo.setApellidos("Alcaraz");
		duenoAdoptivo.setDireccion("Falsa 123");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);   

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhendniEmpty() {

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Simon");
		duenoAdoptivo.setApellidos("Alcaraz");
		duenoAdoptivo.setDireccion("Falsa 123");
		duenoAdoptivo.setDni("");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);   

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}

	@Test
	void shouldNotValidateWhenEmailEmpty() {

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Simon");
		duenoAdoptivo.setApellidos("Alcaraz");
		duenoAdoptivo.setDireccion("Calle Falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);   

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}
	
	@Test
	void shouldNotValidateWhenEmailNotEmail() {

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Simon");
		duenoAdoptivo.setApellidos("Alcaraz");
		duenoAdoptivo.setDireccion("Calle Falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("sdfsd");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);   

		Validator validator = createValidator();
		Set<ConstraintViolation<DuenoAdoptivo>> constraintViolations = validator.validate(duenoAdoptivo);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<DuenoAdoptivo> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("no es una dirección de correo bien formada");
	}

}
