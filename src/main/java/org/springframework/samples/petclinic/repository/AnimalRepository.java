
package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Tipo;

public interface AnimalRepository extends Repository<Animal, Integer> {
	
	@Query("SELECT animal FROM Animal animal JOIN animal.cuidador c WHERE animal.id =:id")
	public Optional<Animal> findById(@Param("id") int id);
	
	@Query("SELECT a FROM Animal a")
	public Collection<Animal> findAll();
	
	@Query("SELECT a FROM Animal a WHERE a.adoptado = false")
	public Collection<Animal> findAllNoAdopted();
	
	void save(Animal animal) throws DataAccessException;

	@Query("SELECT animal FROM Animal animal JOIN animal.centroDeAdopcion c WHERE c.id =:centroId and animal.adoptado=false")
	public Collection<Animal> findAllNoAdoptedByCentro(@Param("centroId") int centroId);

	@Query("SELECT animal FROM Animal animal JOIN animal.cuidador c WHERE c.id =:cuidadorId and animal.adoptado=false and animal.categoria.tipo=0")
	public Collection<Animal> findAnimalAsignadoCanino(@Param("cuidadorId")int cuidadorId); 
	
	@Query("SELECT animal FROM Animal animal JOIN animal.cuidador c WHERE c.id =:cuidadorId and animal.adoptado=false and animal.categoria.tipo=1")
	public Collection<Animal> findAnimalAsignadoFelino(@Param("cuidadorId")int cuidadorId); 
	
	@Query("SELECT animal FROM Animal animal JOIN animal.cuidador c WHERE c.id =:cuidadorId and animal.adoptado=false and animal.categoria.tipo=2")
	public Collection<Animal> findAnimalAsignadoReptil(@Param("cuidadorId")int cuidadorId); 
	
	@Query("SELECT animal FROM Animal animal JOIN animal.cuidador c WHERE c.id =:cuidadorId and animal.adoptado=false and animal.categoria.tipo=3")
	public Collection<Animal> findAnimalAsignadoAve(@Param("cuidadorId")int cuidadorId); 

	@Query("SELECT COUNT(a) FROM Animal a JOIN a.centroDeAdopcion c WHERE c.id=:centroId and a.adoptado=false")
	public int cantidadDeAnimalesActualEnCentro(@Param("centroId")int centroId);

	@Query("SELECT COUNT(*) FROM Animal a WHERE a.id=:animalId and a.adoptado=false and a.centroDeAdopcion.id=:centroId")
	public int comprobarColeccion(@Param("centroId")Integer centroId,@Param("animalId") Integer animalId);

	@Query("SELECT animal FROM Animal animal JOIN animal.cuidador c WHERE c.id =:cuidadorId and animal.adoptado=false and animal.categoria.tipo=:tipo")
	public Collection<Animal> findAnimalAsignadoTipo(@Param("tipo")Tipo tipo,@Param("cuidadorId") int cuidadorId);
	
}

