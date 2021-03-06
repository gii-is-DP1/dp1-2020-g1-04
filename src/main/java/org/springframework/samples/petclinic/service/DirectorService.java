package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.repository.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorService {

	private final DirectorRepository directorRepository;

	private final UserService userService;
	
	private final AuthoritiesService authoritiesService;

	@Autowired
	public DirectorService(DirectorRepository directorRepository, UserService userService,
			AuthoritiesService authoritiesService) {
		this.directorRepository = directorRepository;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	@Transactional
	public void saveDirector(Director director) throws DataAccessException {
		// creating director
		directorRepository.save(director);
		// creating user
		userService.saveUser(director.getUser());
		// creating authorities
		authoritiesService.saveAuthorities(director.getUser().getUsername(), "director");
	}

	@Transactional(readOnly = true)
	public Director findDirectorById(int id) throws DataAccessException {
		return directorRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Director findDirectorByPrincipal() {
		Director result;
		org.springframework.security.core.userdetails.User user;
		user = userService.findPrincipal();
		result = findDirectorByUserName(user.getUsername());

		return result;

	}

	@Transactional(readOnly = true)
	public Person findPersonByPrincipal() {
		Person result;
		org.springframework.security.core.userdetails.User user;
		user = userService.findPrincipal();
		result = findDirectorByUserName(user.getUsername());

		return result;

	}

	@Transactional(readOnly = true)
	private Director findDirectorByUserName(String directorUserName) {
		Director result;
		result = directorRepository.findByUserName(directorUserName);
		return result;
	}
}
