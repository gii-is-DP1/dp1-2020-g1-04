package org.springframework.samples.petclinic.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cuidador;

public interface CuidadorRepository extends Repository<Cuidador, Integer> {

	void save(Cuidador cuidador) throws DataAccessException;

	@Query("SELECT c FROM Cuidador c")
	public Set<Cuidador> findAll();

	@Query("SELECT cuidador FROM Cuidador cuidador WHERE cuidador.id =:cuidadorId")
	public Optional<Cuidador> findById(@Param("cuidadorId") int cuidadorId);

	@Query("SELECT cuidador FROM Cuidador cuidador WHERE cuidador.centroDeAdopcion.id =:centroId")
	public Set<Cuidador> findAllCuidadoresPorCentro(@Param("centroId") int centroId);

}
