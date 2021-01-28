package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Enfermedad;

public interface EnfermedadRepository extends Repository<Enfermedad, Integer>{

	void save(@Valid Enfermedad enfermedad) throws DataAccessException;

	@Query("SELECT enfermedad FROM Enfermedad enfermedad WHERE enfermedad.id=:enfermedadId")
	public Optional<Enfermedad> findEnfermedadById(@Param("enfermedadId")int enfermedadId);

	@Query("SELECT enfermedad FROM Enfermedad enfermedad WHERE enfermedad.animal.id=:animalId")
	public Collection<Enfermedad> findAllEnfermedadeByAnimalId(@Param("animalId")int animalId);
	
	@Query("SELECT enfermedad FROM Enfermedad enfermedad join enfermedad.animal c WHERE c.cuidador.id=:cuidadorId")
	public Collection<Enfermedad> findAllEnfermedadeAnimalByCuidador(@Param("cuidadorId")int cuidadorId);
	
}
