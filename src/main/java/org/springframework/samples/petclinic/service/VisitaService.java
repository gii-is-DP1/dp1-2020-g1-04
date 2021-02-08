package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.repository.VisitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitaService {

	private final VisitaRepository visitaRepository;

	@Autowired
	public VisitaService(VisitaRepository visitaRepository) {
		this.visitaRepository = visitaRepository;
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
