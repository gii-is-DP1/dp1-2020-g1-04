package org.springframework.samples.petclinic.repository;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;

public interface CuidadorRepository extends Repository<Cuidador, Integer>{
	
	void save(DuenoAdoptivo dueñoAdoptivo) throws DataAccessException;

	@Query("SELECT c FROM Cuidador c")
	public Set<Cuidador> findAll();

	@Query("SELECT cuidador FROM Cuidador cuidador WHERE cuidador.id =:cuidadorId")
	public Cuidador findById(@Param("cuidadorId") int cuidadorId);
	
	
	//No disponible hasta crear entidad centroAdopcion
		/*
	@Query("SELECT cuidador FROM Cuidador cuidador WHERE cuidador.centroAdoptivo.id =:centroId")
	public Set<Cuidador> findAllCuidadoresPorCentro(@Param("centroId") int centroId);
		 */
}
