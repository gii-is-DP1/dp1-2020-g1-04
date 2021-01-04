package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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
	@Autowired
	protected DuenoAdoptivoService duenoAdoptivoServiceLimpio;
	@MockBean
	protected DuenoAdoptivoService duenoAdoptivoService;

	private void PrincipalMock() {
		DuenoAdoptivo duenoAdoptivo;
		duenoAdoptivo = duenoAdoptivoServiceLimpio.findDuenoAdoptivoById(11).get();

		given(duenoAdoptivoService.findDuenoAdoptivoByPrincipal()).willReturn(duenoAdoptivo);

	}
/*
	@Test
	void findByPrincipal() {
		PrincipalMock();

		Collection<Visita> visitas = visitaService.findVisitasByPrincipal();
		assertThat(visitas.size()).isEqualTo(3);
	}

	@Test
	void findByPrincipalProximos() {
		PrincipalMock();

		Collection<Visita> visitas = visitaService.findVisitasByPrincipalProximas();
		assertThat(visitas.size()).isEqualTo(1);
	}

	@Test
	void findByPrincipalPasadas() {
		PrincipalMock();

		Collection<Visita> visitas = visitaService.findVisitasByPrincipalPasadas();
		assertThat(visitas.size()).isEqualTo(2);
	}
*/
}
