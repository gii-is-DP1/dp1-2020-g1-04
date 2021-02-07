package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.repository.VisitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitaService {

	private final VisitaRepository visitaRepository;

	private final DuenoAdoptivoService duenoAdoptivoService;
	private final CuidadorService cuidadorService;

	@Autowired
	public VisitaService(VisitaRepository visitaRepository, DuenoAdoptivoService duenoAdoptivoService,
			CuidadorService cuidadorService) {
		this.visitaRepository = visitaRepository;
		this.cuidadorService = cuidadorService;
		this.duenoAdoptivoService = duenoAdoptivoService;
	}

	@Transactional(readOnly = true)
	public Collection<Visita> findVisitasByPrincipal(int duenoId) {

		Collection<Visita> result = visitaRepository.findVisitaByDuenoAdoptivoId(duenoId);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Visita> findVisitasByPrincipalProximas(int duenoId) {
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaProximasByDuenoAdoptivoId(duenoId, now);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Visita> findVisitasByPrincipalPasadas(int duenoId) {
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaPasadasByDuenoAdoptivoId(duenoId, now);
		return result;
	}

	@Transactional(readOnly = true)
	public Visita findVisitaById(int visitaId) {
		Visita result;
		result = visitaRepository.findVisitaById(visitaId);

		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Visita> findVisitasByCuidadorPrincipalProximas(int cuidadorId) {
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaProximasByCuidadorId(cuidadorId, now);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Visita> findVisitasByCuidadorPrincipalPasadas(int cuidadorId) {
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaPasadasByCuidadorId(cuidadorId, now);
		return result;
	}

}
