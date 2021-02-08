package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collection;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VisitaServiceTest {

	@Autowired
	protected VisitaService visitaService;

	@MockBean
	protected DuenoAdoptivoService duenoAdoptivoService;

	private void PrincipalMock() {
		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		duenoAdoptivo.setId(11);

		given(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(duenoAdoptivo);

	}

	@Test
	void findByPrincipal() {
		PrincipalMock();

		Collection<Visita> visitas = visitaService.findVisitasByPrincipal(11);
		assertThat(visitas.size()).isEqualTo(5);
	}

	//H21 
	@Test
	void findByPrincipalProximos() {
		PrincipalMock();

		Collection<Visita> visitas = visitaService.findVisitasByPrincipalProximas(11);
		assertThat(visitas.size()).isEqualTo(3);
	}

	//H21
	@Test
	void findByPrincipalPasadas() {
		PrincipalMock();

		Collection<Visita> visitas = visitaService.findVisitasByPrincipalPasadas(11);
		assertThat(visitas.size()).isEqualTo(2);
	}

}
