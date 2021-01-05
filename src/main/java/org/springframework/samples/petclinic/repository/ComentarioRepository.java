package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Comentario;

public interface ComentarioRepository extends Repository<Comentario, Integer> {
	
	void save(Comentario comentario) throws DataAccessException;

}
