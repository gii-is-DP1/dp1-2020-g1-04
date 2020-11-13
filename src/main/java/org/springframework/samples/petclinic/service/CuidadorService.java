package org.springframework.samples.petclinic.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.repository.CuidadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service	
public class CuidadorService {
	

private CuidadorRepository cuidadorRepository;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public CuidadorService(CuidadorRepository cuidadorRepository) {
		this.cuidadorRepository = cuidadorRepository;
	}

	@Transactional
	public Set<Cuidador> findAllCuidadores() {
		Set<Cuidador> result;
		result=cuidadorRepository.findAll();
		return result;
	}
	
	@Transactional(readOnly = true)
	public Cuidador findDuenoAdoptivoById(int cuidadorId) {
		return cuidadorRepository.findById(cuidadorId);
	}	

	//No disponible hasta crear entidad centroAdopcion
	/*
	@Transactional
	public Set<Cuidador> findAllCuidadoresPorCentro(int centroId){
		Set<Cuidador> result;
		result=cuidadorRepository.findAllCuidadoresPorCentro(centroId);
		return result;
	}
*/
}
