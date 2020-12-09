package org.springframework.samples.petclinic.repository;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Evento;

public interface EventoRepository extends Repository<Evento, Integer>{

	void save(@Valid Evento evento) throws DataAccessException;
;

}
