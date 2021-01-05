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

	private VisitaRepository visitaRepository;

	@Autowired
	private DuenoAdoptivoService duenoAdoptivoService;
	@Autowired
	private CuidadorService cuidadorService;

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

	@Transactional
	public Collection<Visita> findVisitasByPrincipalProximas() {
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaProximasByDuenoAdoptivoId(dueno.getId(), now);
		return result;
	}

	@Transactional
	public Collection<Visita> findVisitasByPrincipalPasadas() {
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaPasadasByDuenoAdoptivoId(dueno.getId(), now);
		return result;
	}

	public Visita findVisitaById(int visitaId) {
		Visita result;
		result = visitaRepository.findVisitaById(visitaId);

		return result;
	}
	
	@Transactional
	public Collection<Visita> findVisitasByCuidadorPrincipalProximas() {
		Cuidador cuidador = cuidadorService.findCuidadorByPrincipal();
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaProximasByCuidadorId(cuidador.getId(), now);
		return result;
	}

	@Transactional
	public Collection<Visita> findVisitasByCuidadorPrincipalPasadas() {
		Cuidador cuidador = cuidadorService.findCuidadorByPrincipal();
		LocalDate now = LocalDate.now();
		Collection<Visita> result = visitaRepository.findVisitaPasadasByCuidadorId(cuidador.getId(), now);
		return result;
	}

}
