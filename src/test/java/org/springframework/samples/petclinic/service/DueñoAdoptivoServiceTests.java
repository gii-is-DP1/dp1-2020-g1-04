/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUserNameException;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test of the Service and the Repository layer.
 * <p>
 * ClinicServiceSpringDataJpaTests subclasses benefit from the following services provided
 * by the Spring TestContext Framework:
 * </p>
 * <ul>
 * <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li>
 * <li><strong>Dependency Injection</strong> of test fixture instances, meaning that we
 * don't need to perform application context lookups. See the use of
 * {@link Autowired @Autowired} on the <code>{@link
 * DuenoAdoptivoServiceTests#clinicService clinicService}</code> instance variable, which uses
 * autowiring <em>by type</em>.
 * <li><strong>Transaction management</strong>, meaning each test method is executed in
 * its own transaction, which is automatically rolled back by default. Thus, even if tests
 * insert or otherwise change database state, there is no need for a teardown or cleanup
 * script.
 * <li>An {@link org.springframework.context.ApplicationContext ApplicationContext} is
 * also inherited and can be used for explicit bean lookup if necessary.</li>
 * </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Dave Syer
 */

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class DuenoAdoptivoServiceTests {                
        @Autowired
	protected DuenoAdoptivoService duenoAdoptivoService;

	@Test
	void shouldFindDuenosAdoptivosByLastName() {
		Collection<DuenoAdoptivo> duenosAdoptivos = this.duenoAdoptivoService.findDuenoAdoptivoByApellidos("Durán");
		assertThat(duenosAdoptivos.size()).isEqualTo(1);

		duenosAdoptivos = this.duenoAdoptivoService.findDuenoAdoptivoByApellidos("Duránnn");
		assertThat(duenosAdoptivos.isEmpty()).isTrue();
	}


	@Test
	@Transactional
	public void shouldInsertDuenoAdoptivo() throws DataAccessException, DuplicatedUserNameException {
		Collection<DuenoAdoptivo> duenosAdoptivos = this.duenoAdoptivoService.findDuenoAdoptivoByApellidos("Durán");
		int found = duenosAdoptivos.size();

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Sam");
		duenoAdoptivo.setApellidos("Durán");
		duenoAdoptivo.setDireccion("4, Evans Street");
		duenoAdoptivo.setDni("Wollongong");
		duenoAdoptivo.setTelefono("4444444444");
		duenoAdoptivo.setEmail("prueba@gmail.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);                
                
		this.duenoAdoptivoService.saveDuenoAdoptivo(duenoAdoptivo);
		assertThat(duenoAdoptivo.getId().longValue()).isNotEqualTo(0);

		duenosAdoptivos = this.duenoAdoptivoService.findDuenoAdoptivoByApellidos("Durán");
		assertThat(duenosAdoptivos.size()).isEqualTo(found + 1);
	}
	
	//H13 Test Positivo
	@Test
	@Transactional
	public void findAll() throws DataAccessException, DuplicatedUserNameException {
		Collection<DuenoAdoptivo> duenosAdoptivos = this.duenoAdoptivoService.findAllDuenosAdoptivos();
		int found = duenosAdoptivos.size();

		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Sam");
		duenoAdoptivo.setApellidos("Durán");
		duenoAdoptivo.setDireccion("4, Evans Street");
		duenoAdoptivo.setDni("Wollongong");
		duenoAdoptivo.setTelefono("4444444444");
		duenoAdoptivo.setEmail("prueba@gmail.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                duenoAdoptivo.setUser(user);          
         
                this.duenoAdoptivoService.saveDuenoAdoptivo(duenoAdoptivo);

		duenosAdoptivos = this.duenoAdoptivoService.findAllDuenosAdoptivos();
		assertThat(duenosAdoptivos.size()).isEqualTo(found + 1);
	}
	
	
	@Test
	@Transactional
	public void busquedaByUsername() {
		DuenoAdoptivo duenoAdoptivo = this.duenoAdoptivoService.findDuenoAdoptivoByUserName("josdurgar1");
		Optional<DuenoAdoptivo> aux=this.duenoAdoptivoService.findDuenoAdoptivoById(11);
		
		assertThat(duenoAdoptivo).isEqualTo(aux.get());
	}

	@Test
	@Transactional
	public void noBusquedaByUsername() throws Exception {
		DuenoAdoptivo res = this.duenoAdoptivoService.findDuenoAdoptivoByUserName("noExisto");
		
		assertThat(res).isEqualTo(null);
		
		
	}
	
	@Test
	@Transactional
	public void noShouldListDuenoAdoptivoNoExist() throws Exception {
		Optional<DuenoAdoptivo> res=duenoAdoptivoService.findDuenoAdoptivoById(23);
		
		assertThat(res).isEmpty();
		
		
	}
	
	

}
