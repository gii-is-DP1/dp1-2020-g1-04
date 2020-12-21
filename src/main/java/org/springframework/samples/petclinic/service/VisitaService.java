package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.repository.VisitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitaService {

	private VisitaRepository visitaRepository;

	@Autowired
	private DuenoAdoptivoService duenoAdoptivoService;

	@Autowired
	public VisitaService(VisitaRepository visitaRepository) {
		this.visitaRepository = visitaRepository;
	}

	@Transactional
	public Collection<Visita> findVisitasByPrincipal() {
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();

		Collection<Visita> result = visitaRepository.findVisitaByDuenoAdoptivoId(dueno.getId());
		return result;
	}

}
