package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;


public interface CentroDeAdopcionRepository extends CrudRepository<CentroDeAdopcion, Integer>{

	Collection<CentroDeAdopcion> findAll() throws DataAccessException;
	
		
		

}
