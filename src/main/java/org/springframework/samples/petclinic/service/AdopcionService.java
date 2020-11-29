package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.repository.AdopcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdopcionService {

	private AdopcionRepository adopcionRepository;
	
	
	@Autowired
	public AdopcionService(AdopcionRepository adopcionRepository) {
		this.adopcionRepository = adopcionRepository;
	}
	
	@Transactional(readOnly=true)
	public Adopcion findAdopcionById(int id) throws DataAccessException{
		return adopcionRepository.findById(id);
	}
	
	@Transactional
	public Collection<Adopcion> findAll(){
		Collection<Adopcion> result;
		result = adopcionRepository.findAll();
		return result;
	}
	
	@Transactional
	public void saveAdopcion(Adopcion adopcion) throws DataAccessException{
		
		adopcionRepository.save(adopcion);	
		
	}
	
	@Transactional
	public Collection<Adopcion> findAllByDuenoAdoptivo(int duenoAdoptivoId) {
		Collection<Adopcion> result;
		result=adopcionRepository.findAllByDuenoAdoptivo(duenoAdoptivoId);
		return result;
	}
	
}
