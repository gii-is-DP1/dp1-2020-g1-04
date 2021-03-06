package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Estado;
import org.springframework.samples.petclinic.repository.AdopcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdopcionService {

	private final AdopcionRepository adopcionRepository;

	@Autowired
	public AdopcionService(AdopcionRepository adopcionRepository) {
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

		if (adopcion.getEstado() == Estado.ACEPTADA || adopcion.getEstado() == Estado.DENEGADA) {
			adopcion.setFechaDecision(LocalDate.now());
		}

		adopcionRepository.save(adopcion);

	}

	@Transactional(readOnly = true)
	public Collection<Adopcion> findAllByDuenoAdoptivo(int duenoAdoptivoId) {
		Collection<Adopcion> result;
		result = adopcionRepository.findAllByDuenoAdoptivo(duenoAdoptivoId);
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Adopcion> findAllSolicitadas() {
		Collection<Adopcion> result = adopcionRepository.findAllSolicitadas();
		return result;
	}

	@Transactional(readOnly = true)
	public Collection<Adopcion> findAdopcionEstadoByDuenoAdoptivo(Estado estado, Integer duenoId) {
		Collection<Adopcion> result = adopcionRepository.findAdopcionEstadoByDuenoAdoptivo(estado, duenoId);
		return result;
	}

}
