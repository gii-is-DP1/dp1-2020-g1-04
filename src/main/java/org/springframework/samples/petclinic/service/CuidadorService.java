package org.springframework.samples.petclinic.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.repository.CuidadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuidadorService {

	private final CuidadorRepository cuidadorRepository;

	private final UserService userService;

	private final AuthoritiesService authoritiesService;

	@Autowired
	public CuidadorService(CuidadorRepository cuidadorRepository, UserService userService,
			AuthoritiesService authoritiesService) {
		this.cuidadorRepository = cuidadorRepository;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	@Transactional(readOnly = true)
	public Set<Cuidador> findAllCuidadores() {
		Set<Cuidador> result;
		result = cuidadorRepository.findAll();
		return result;
	}

	@Transactional(readOnly = true)
	public Optional<Cuidador> findCuidadorById(int cuidadorId) {
		return cuidadorRepository.findById(cuidadorId);
	}

	@Transactional
	public void saveCuidador(@Valid Cuidador cuidador) throws DataAccessException {
		// creating duenoAdoptivo
		cuidadorRepository.save(cuidador);
		// creating user
		userService.saveUser(cuidador.getUser());
		// creating authorities
		authoritiesService.saveAuthorities(cuidador.getUser().getUsername(), "cuidador");

	}

	@Transactional(readOnly = true)
	public Set<Cuidador> findAllCuidadoresPorCentro(int centroId) {
		Set<Cuidador> result;
		result = cuidadorRepository.findAllCuidadoresPorCentro(centroId);
		return result;
	}

	@Transactional(readOnly = true)
	public Cuidador findCuidadorByUserName(String cuidadorUserName) {
		Cuidador result;
		result = cuidadorRepository.findByUserName(cuidadorUserName);
		return result;
	}

	@Transactional(readOnly = true)
	public Cuidador findCuidadorByPrincipal() {
		Cuidador result;
		org.springframework.security.core.userdetails.User user;
		user = userService.findPrincipal();
		result = findCuidadorByUserName(user.getUsername());

		return result;

	}
}
