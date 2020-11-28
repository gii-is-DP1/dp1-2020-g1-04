package org.springframework.samples.petclinic.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CentroDeAdopcionServiceTest {
	
	@Autowired
	protected CentroDeAdopcionService centroDeAdopcionService;
	
	@Autowired
	protected DirectorService directorService;
	
	
	//Test findAll centros
	@Test
	void shouldFindAllCentros() {
		Collection<CentroDeAdopcion> centros = this.centroDeAdopcionService.findAll();
		
		CentroDeAdopcion centro1 = EntityUtils.getById(centros, CentroDeAdopcion.class, 1);
		assertThat(centro1.getNombre()).isEqualTo("CENTRO DE ADOPCIÓN 1");
		CentroDeAdopcion centro2 = EntityUtils.getById(centros, CentroDeAdopcion.class, 2);
		assertThat(centro2.getNombre()).isEqualTo("CENTRO DE ADOPCIÓN 2");
		CentroDeAdopcion centro3 = EntityUtils.getById(centros, CentroDeAdopcion.class, 3);
		assertThat(centro3.getNombre()).isEqualTo("CENTRO DE ADOPCIÓN 3");
		
		assertThat(centros.size() == 3);
	}
	

	@Test
	void shouldFindCentroById() {
		CentroDeAdopcion centro = this.centroDeAdopcionService.findById(1);
		
		assertThat(centro.getId() == 1);
		assertThat(centro.getNombre() == "CENTRO DE ADOPCIÓN 1");
		assertThat(centro.getDireccion() == "Dirección 1");
		assertThat(centro.getCantidadMax() == 20);
		
	}
	
	@Test
	void shouldFindAllNoEstenLlenos() {
		Collection<CentroDeAdopcion> centros = this.centroDeAdopcionService.findAll();
		
		CentroDeAdopcion centro1 = EntityUtils.getById(centros, CentroDeAdopcion.class, 1);
		assertThat(centro1.getAnimales().size() <= centro1.getCantidadMax());	
		CentroDeAdopcion centro2 = EntityUtils.getById(centros, CentroDeAdopcion.class, 2);
		assertThat(centro2.getAnimales().size() <= centro2.getCantidadMax());
		CentroDeAdopcion centro3 = EntityUtils.getById(centros, CentroDeAdopcion.class, 3);
		assertThat(centro3.getAnimales().size() <= centro3.getCantidadMax());

	}
	




}
