package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;

public interface CentroDeAdopcionRepository extends CrudRepository<CentroDeAdopcion, Integer> {

	Collection<CentroDeAdopcion> findAll() throws DataAccessException;

	@Query("SELECT centro FROM CentroDeAdopcion centro WHERE size(centro.animales)<centro.cantidadMax")
	Collection<CentroDeAdopcion> findAllNoEstenLlenos();

	
	
}
