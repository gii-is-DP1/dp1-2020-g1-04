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

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CuidadorServiceTests {                
        @Autowired
		protected CuidadorService cuidadorService;
        @Autowired
		protected CentroDeAdopcionService centroDeAdopcionService;
	 
	 @Test
	 @Transactional
	 @Secured("director")
	 public void findByCentro() {
		
		CentroDeAdopcion c=centroDeAdopcionService.findById(2);
		 
		Collection<Cuidador> cuidadores= cuidadorService.findAllCuidadoresPorCentro(2);
		
		
		assertThat(cuidadores.size()).isEqualTo(2);
		assertThat(cuidadores.size()).isEqualTo(c.getCuidadores().size());
		assertThat(cuidadores).containsAll(c.getCuidadores());
		Cuidador cui = EntityUtils.getById(cuidadores, Cuidador.class, 5);
		assertThat(cui.getDni()).isEqualTo("37685775E");
		Collection<Cuidador> cuidadores2= cuidadorService.findAllCuidadoresPorCentro(1);
		
		assertThat(cuidadores2.size()).isEqualTo(2);
		 
	 }
	 
	 	@Test
		@Transactional
		public void shouldInsertCuidador() {
			Collection<Cuidador> cuidadores = this.cuidadorService.findAllCuidadores();
			int found = cuidadores.size();
			CentroDeAdopcion centroDeAdopcion=centroDeAdopcionService.findById(1);

			Cuidador cuidador = new Cuidador();
			cuidador.setNombre("Sam");
			cuidador.setApellidos("Durán");
			cuidador.setDni("Wollongong");
			cuidador.setTelefono("4444444444");
			cuidador.setEmail("prueba@gmail.com");
			cuidador.setCentroDeAdopcion(centroDeAdopcion);
	                User user=new User();
	                user.setUsername("Sam");
	                user.setPassword("supersecretpassword");
	                user.setEnabled(true);
	                cuidador.setUser(user);                
	                
			this.cuidadorService.saveCuidador(cuidador);
			assertThat(cuidador.getId().longValue()).isNotEqualTo(0);

			cuidadores = this.cuidadorService.findAllCuidadores();
			assertThat(cuidadores.size()).isEqualTo(found + 1);
		}
	 	
	 	@Test
		@Transactional
		public void noShouldListCuidadorNoExist() throws Exception {
			Optional<Cuidador> res=cuidadorService.findCuidadorById(23);
			
			assertThat(res).isEmpty();
			
			
		}

}
