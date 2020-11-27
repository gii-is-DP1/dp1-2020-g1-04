
package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Cuidador;

public interface AnimalRepository extends Repository<Animal, Integer> {
	
	@Query("SELECT animal FROM Animal animal JOIN animal.cuidador c WHERE animal.id =:id")
	public Optional<Animal> findById(@Param("id") int id);
	
	@Query("SELECT a FROM Animal a")
	public Collection<Animal> findAll();
	
	void save(Animal animal) throws DataAccessException; 
	
	@Query("SELECT animal FROM Animal animal WHERE animal.centroDeAdopcion.id =:centroId")
	public Set<Animal> findAllAnimalesPorCentro(@Param("centroId") int centroId);

}

