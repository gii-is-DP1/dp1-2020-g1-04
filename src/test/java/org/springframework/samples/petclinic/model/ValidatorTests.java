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

}
