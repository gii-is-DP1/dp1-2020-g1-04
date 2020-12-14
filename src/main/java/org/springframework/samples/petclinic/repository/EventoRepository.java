package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Evento;

public interface EventoRepository extends Repository<Evento, Integer>{

	void save(@Valid Evento evento) throws DataAccessException;

	
	@Query("SELECT evento FROM Evento evento WHERE evento.director.id =:directorId")
	public Collection<Evento> findEventosByDirector (@Param("directorId") int directorId);

	@Query("SELECT evento FROM Evento evento WHERE evento.id=:eventoId")
	public Optional<Evento> findEventoById(@Param("eventoId")int eventoId);

	@Query("SELECT evento FROM Evento evento join evento.duenos d WHERE d.id =:duenoAdoptivoid")
	public Collection<Evento> findEventosByDuenoAdoptivo(@Param("duenoAdoptivoid")int duenoAdoptivoid);

	@Query("SELECT evento FROM Evento evento WHERE size(evento.cuidadores)>0 and evento.fecha>:now")
	public Collection<Evento> findEventosDisponibles(@Param("now")LocalDate now);


}
