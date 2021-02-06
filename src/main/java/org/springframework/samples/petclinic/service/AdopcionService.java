package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.repository.AdopcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdopcionService {

	private final AdopcionRepository adopcionRepository;

	private final DuenoAdoptivoService duenoAdoptivoService;

	@Autowired
	public AdopcionService(AdopcionRepository adopcionRepository, DuenoAdoptivoService duenoAdoptivoService) {
		this.duenoAdoptivoService = duenoAdoptivoService;
		this.adopcionRepository = adopcionRepository;
	}

	@Transactional(readOnly = true)
	public Optional<Adopcion> findAdopcionById(int id) throws DataAccessException {
		return adopcionRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Adopcion> findAll() {
		Collection<Adopcion> result;
		result = adopcionRepository.findAll();
		return result;
	}

	@Transactional
	public void saveAdopcion(Adopcion adopcion) throws DataAccessException {

		adopcionRepository.save(adopcion);

	}

	@Transactional(readOnly = true)
	public Collection<Adopcion> findAllByDuenoAdoptivo(int duenoAdoptivoId) {
		Collection<Adopcion> result;
		result = adopcionRepository.findAllByDuenoAdoptivo(duenoAdoptivoId);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Adopcion> findSolicitadasByDuenoAdoptivo() {
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();

		Collection<Adopcion> result = adopcionRepository.findSolicitadasByDuenoAdoptivo(dueno.getId());
		return result;
	}
	@Transactional(readOnly = true)
	public Collection<Adopcion> findAceptadasByDuenoAdoptivo() {
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();

		Collection<Adopcion> result = adopcionRepository.findAceptadasByDuenoAdoptivo(dueno.getId());
		return result;
	}
	@Transactional(readOnly = true)
	public Collection<Adopcion> findDenegadasByDuenoAdoptivo() {
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();

		Collection<Adopcion> result = adopcionRepository.findDenegadasByDuenoAdoptivo(dueno.getId());
		return result;
	}
	@Transactional(readOnly = true)
	public Collection<Adopcion> findAllSolicitadas() {
		Collection<Adopcion> result = adopcionRepository.findAllSolicitadas();
		return result;
	}

}
