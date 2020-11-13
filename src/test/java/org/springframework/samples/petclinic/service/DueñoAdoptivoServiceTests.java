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
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
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
 * DueñoAdoptivoServiceTests#clinicService clinicService}</code> instance variable, which uses
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
class DueñoAdoptivoServiceTests {                
        @Autowired
	protected DuenoAdoptivoService dueñoAdoptivoService;

	/*@Test
	void shouldFindOwnersByLastName() {
		Collection<DueñoAdoptivo> dueñosAdoptivos = this.dueñoAdoptivoService.findDueñoAdoptivoByApellidos("Davis");
		assertThat(dueñosAdoptivos.size()).isEqualTo(2);

		dueñosAdoptivos = this.dueñoAdoptivoService.findDueñoAdoptivoByApellidos("Daviss");
		assertThat(dueñosAdoptivos.isEmpty()).isTrue();
	}*/

/*
	@Test
	@Transactional
	public void shouldInsertOwner() {
		Collection<DueñoAdoptivo> dueñosAdoptivos = this.dueñoAdoptivoService.findDueñoAdoptivoByApellidos("Schultz");
		int found = dueñosAdoptivos.size();

		DueñoAdoptivo owner = new DueñoAdoptivo();
		owner.setNombre("Sam");
		owner.setApellidos("Schultz");
		owner.setDireccion("4, Evans Street");
		owner.setDni("Wollongong");
		owner.setTelefono("4444444444");
		owner.setEmail("prueba@gmail.com");
                User user=new User();
                user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                owner.setUser(user);                
                
		this.dueñoAdoptivoService.saveDueñoAdoptivo(owner);
		assertThat(owner.getId().longValue()).isNotEqualTo(0);

		dueñosAdoptivos = this.dueñoAdoptivoService.findDueñoAdoptivoByApellidos("Schultz");
		assertThat(dueñosAdoptivos.size()).isEqualTo(found + 1);
	}
	*/
/*
	@Test
	@Transactional
	void shouldUpdateDueñoAdoptivo() {
		DueñoAdoptivo dueñoAdoptivo = this.dueñoAdoptivoService.findDueñoAdoptivoById(1);
		String oldApellidos = dueñoAdoptivo.getApellidos();
		String newApellidos = oldApellidos + "X";

		dueñoAdoptivo.setApellidos(newApellidos);
		this.dueñoAdoptivoService.saveDueñoAdoptivo(dueñoAdoptivo);

		// retrieving new name from database
		dueñoAdoptivo = this.dueñoAdoptivoService.findDueñoAdoptivoById(1);
		assertThat(dueñoAdoptivo.getApellidos()).isEqualTo(newApellidos);
	}
*/

}
