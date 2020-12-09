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
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Animal;
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
	@Autowired
	protected AnimalService animalService;
	
	 @Test
	 @Transactional
	 public void findAll() {
		
		 Collection<Adopcion> adopciones=adopcionService.findAll();
		 assertThat(adopciones.size()).isEqualTo(3);
		 int found = adopciones.size();
		 
		 Optional<Animal> animal=animalService.findAnimalById(1);
		 Optional<DuenoAdoptivo> dueno=duenoAdoptivoService.findDuenoAdoptivoById(1);
		 LocalDate fechaDecision=LocalDate.now();
		 
		 Adopcion adopcion= new Adopcion();
		 adopcion.setAceptada(true);
		 adopcion.setAnimal(animal.get());
		 adopcion.setDueno(dueno.get());
		 adopcion.setFechaDecision(fechaDecision);
		 adopcion.setLeidoRequisitos(true);
		 adopcion.setMayoresDeEdad(2);
		 adopcion.setMotivo("motivo");
		 adopcion.setMotivoDecision("decision");
		 adopcion.setOtrosAnimales(false);
		 adopcion.setPermisoCmunidadVecinos(true);
		 adopcion.setUnidadFamiliar(1);
		 
		 adopcionService.saveAdopcion(adopcion);
		
		 Collection<Adopcion> adopciones2=adopcionService.findAll();
		 assertThat(adopciones2.size()).isEqualTo(found + 1);
		
		 
	 }
	 
	 @Test
	 @Transactional
	 public void findAllByDuenoAdoptivo() {
		
		Collection<Adopcion> adopciones=adopcionService.findAllByDuenoAdoptivo(11);
		 
		assertThat(adopciones.size()).isEqualTo(1);
		for(Adopcion a: adopciones) {
			assertThat(a.getDueno().getApellidos()).isEqualTo("Durán");
		}
		
		Optional<DuenoAdoptivo> d2=duenoAdoptivoService.findDuenoAdoptivoById(11);
		
		for(Adopcion a: adopciones) {
			assertThat(a.getDueno()).isEqualTo(d2.get());
		}
		 
	 }
	 
	 @Test
		@Transactional
		public void noShouldListAdoptionNoExist() throws Exception {
			Optional<Adopcion> adopcion=adopcionService.findAdopcionById(23);
			
			assertThat(adopcion).isEmpty();
			
			
		}
	 

}
