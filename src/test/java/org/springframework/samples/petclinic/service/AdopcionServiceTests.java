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

import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Estado;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AdopcionServiceTests {

	@Autowired
	protected AdopcionService adopcionService;
	@MockBean
	protected DuenoAdoptivoService duenoAdoptivoService;
	@Autowired
	protected AnimalService animalService;

	private void PrincipalMock() {
		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setNombre("Jose Manuel");
		duenoAdoptivo.setApellidos("Durán");
		duenoAdoptivo.setDireccion("calle falsa");
		duenoAdoptivo.setDni("1234567H");
		duenoAdoptivo.setTelefono("666777888");
		duenoAdoptivo.setEmail("fsfd@dfs.com");
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		duenoAdoptivo.setUser(user);
		duenoAdoptivo.setId(11);

		given(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(duenoAdoptivo);
		given(duenoAdoptivoService.findDuenoAdoptivoById(11)).willReturn(Optional.of(duenoAdoptivo));
	}

	@Test
	@Transactional
	public void findAll() {
		PrincipalMock();
		Collection<Adopcion> adopciones = adopcionService.findAll();
		assertThat(adopciones.size()).isEqualTo(3);
		int found = adopciones.size();

		Optional<Animal> animal = animalService.findAnimalById(1);
		Optional<DuenoAdoptivo> dueno = duenoAdoptivoService.findDuenoAdoptivoById(11);
		LocalDate fechaDecision = LocalDate.now();

		Adopcion adopcion = new Adopcion();
		adopcion.setEstado(Estado.ACEPTADA);
		adopcion.setAnimal(animal.get());
		adopcion.setDueno(dueno.get());
		adopcion.setFechaDecision(fechaDecision);
		adopcion.setLeidoRequisitos(true);
		adopcion.setMayoresDeEdad(2);
		adopcion.setMotivo("motivo");
		adopcion.setMotivoDecision("decision");
		adopcion.setOtrosAnimales(false);
		adopcion.setPermisoComunidadVecinos(true);
		adopcion.setUnidadFamiliar(1);

		adopcionService.saveAdopcion(adopcion);

		Collection<Adopcion> adopciones2 = adopcionService.findAll();
		assertThat(adopciones2.size()).isEqualTo(found + 1);

	}

	// Este Test estaba preparado para funcionar recuperando los datos de la BD,
	// donde al final comparaba los Duenos, al usar mocks se ha cambiado para que
	// compare los apellidos, que aunque en la vida real pudieran ser repetidos,
	// aquí sabemos que es único para ese usuario.
	@Test
	@Transactional
	public void findAllByDuenoAdoptivo() {
		PrincipalMock();
		Collection<Adopcion> adopciones = adopcionService.findAllByDuenoAdoptivo(11);

		assertThat(adopciones.size()).isEqualTo(1);
		for (Adopcion a : adopciones) {
			assertThat(a.getDueno().getApellidos()).isEqualTo("Durán");
		}

		Optional<DuenoAdoptivo> d2 = duenoAdoptivoService.findDuenoAdoptivoById(11);

		for (Adopcion a : adopciones) {
			assertThat(a.getDueno().getApellidos()).isEqualTo(d2.get().getApellidos());
		}

	}

	@Test
	@Transactional
	public void noShouldListAdoptionNoExist() throws Exception {
		Optional<Adopcion> adopcion = adopcionService.findAdopcionById(23);

		assertThat(adopcion).isEmpty();

	}

	// H22
	@Test
	@Transactional
	public void findSolicitadasByDuenoAdoptivo() throws Exception {
		PrincipalMock();
		Collection<Adopcion> adopciones = adopcionService.findSolicitadasByDuenoAdoptivo();
		assertThat(adopciones.size()).isEqualTo(1);
	}
	// H22
	@Test
	@Transactional
	public void findAceptadasByDuenoAdoptivo() throws Exception {
		PrincipalMock();
		Collection<Adopcion> adopciones = adopcionService.findAceptadasByDuenoAdoptivo();
		assertThat(adopciones.size()).isEqualTo(0);
	}
	// H22
	@Test
	@Transactional
	public void findDenegadasByDuenoAdoptivo() throws Exception {
		PrincipalMock();
		Collection<Adopcion> adopciones = adopcionService.findDenegadasByDuenoAdoptivo();
		assertThat(adopciones.size()).isEqualTo(0);
	}
}
