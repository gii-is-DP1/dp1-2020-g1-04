package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Visita;

public interface VisitaRepository extends Repository<Visita, Integer>{

	@Query("SELECT visita FROM Visita visita WHERE visita.dueno.id =:duenoAdoptivoId")
	public Collection<Visita> findVisitaByDuenoAdoptivoId(@Param("duenoAdoptivoId")int duenoAdoptivoId);

	@Query("SELECT visita FROM Visita visita WHERE visita.dueno.id =:duenoAdoptivoId and visita.momento>=:now")
	public Collection<Visita> findVisitaProximasByDuenoAdoptivoId(@Param("duenoAdoptivoId")int duenoAdoptivoId, @Param("now")LocalDate now);

	@Query("SELECT visita FROM Visita visita WHERE visita.dueno.id =:duenoAdoptivoId and visita.momento<:now")
	public Collection<Visita> findVisitaPasadasByDuenoAdoptivoId(@Param("duenoAdoptivoId")int duenoAdoptivoId, @Param("now")LocalDate now);

	@Query("SELECT visita FROM Visita visita WHERE visita.id =:visitaId")
	public Visita findVisitaById(@Param("visitaId")int visitaId);

	@Query("SELECT visita FROM Visita visita WHERE visita.cuidador.id =:cuidadorId and visita.momento>=:now")
	public Collection<Visita> findVisitaProximasByCuidadorId(@Param("cuidadorId")int cuidadorId, @Param("now")LocalDate now);

	@Query("SELECT visita FROM Visita visita WHERE visita.cuidador.id =:cuidadorId and visita.momento<:now")
	public Collection<Visita> findVisitaPasadasByCuidadorId(@Param("cuidadorId")int cuidadorId, @Param("now")LocalDate now);
}
