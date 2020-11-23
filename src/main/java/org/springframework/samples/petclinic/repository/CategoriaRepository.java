package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Categoria;


public interface CategoriaRepository extends Repository<Categoria, Integer>{

	void save(Categoria categoria) throws DataAccessException;

	
	
	
}
