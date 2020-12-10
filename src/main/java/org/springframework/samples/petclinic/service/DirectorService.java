package org.springframework.samples.petclinic.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.repository.CuidadorRepository;
import org.springframework.samples.petclinic.repository.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorService {
	
	private DirectorRepository directorRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public DirectorService(DirectorRepository directorRepository) {
		this.directorRepository = directorRepository;
	}
	
	@Transactional
	public void saveDirector(Director director) throws DataAccessException {
		//creating director
		directorRepository.save(director);		
		//creating user
		userService.saveUser(director.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(director.getUser().getUsername(), "director");
	}	
	
	@Transactional(readOnly = true)
	public Director findDirectorById(int id) throws DataAccessException {
		return directorRepository.findById(id);
	}
	
	@Transactional
	public Director findDirectorByPrincipal() {
		Director result;
		org.springframework.security.core.userdetails.User user;
		user=userService.findPrincipal();
		result=findDirectorByUserName(user.getUsername());
		
		return result;
		
	}
	@Transactional
	private Director findDirectorByUserName(String directorUserName) {
		Director result;
		result=directorRepository.findByUserName(directorUserName);
		return result;
	}
}
