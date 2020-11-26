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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AdopcionServiceTests { 
	
	@Autowired
	protected AdopcionService adopcionService;
	@Autowired
	protected DuenoAdoptivoService duenoAdoptivoService;
	
	 @Test
	 @Transactional
	 public void findAll() {
		
		 Collection<Adopcion> adopciones=adopcionService.findAll();
		 
		
		
		assertThat(adopciones.size()).isEqualTo(3);
		
		 
	 }
	 
	 @Test
	 @Transactional
	 public void findAllByDuenoAdoptivo() {
		
		Collection<Adopcion> adopciones=adopcionService.findAllByDuenoAdoptivo(11);
		 
		assertThat(adopciones.size()).isEqualTo(1);
		for(Adopcion a: adopciones) {
			assertThat(a.getDueno().getApellidos()).isEqualTo("Durán");
		}
		
		DuenoAdoptivo d2=duenoAdoptivoService.findDuenoAdoptivoById(11);
		
		for(Adopcion a: adopciones) {
			assertThat(a.getDueno()).isEqualTo(d2);
		}
		 
	 }
	 

}
