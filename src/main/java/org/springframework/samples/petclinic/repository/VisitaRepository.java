package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Visita;

public interface VisitaRepository extends Repository<Visita, Integer>{

	@Query("SELECT visita FROM Visita visita WHERE visita.dueno.id =:duenoAdoptivoId")
	public Collection<Visita> findVisitaByDuenoAdoptivoId(@Param("duenoAdoptivoId")int duenoAdoptivoId);

}
