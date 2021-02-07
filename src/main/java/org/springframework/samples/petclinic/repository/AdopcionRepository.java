package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Estado;

public interface AdopcionRepository extends Repository<Adopcion, Integer>{
	
	

	//Listado de Adopciones
	Collection<Adopcion> findAll() throws DataAccessException;
	
	//Encontrar adopcion
	@Query("SELECT adopcion FROM  Adopcion adopcion WHERE adopcion.id =:id")
	public Optional<Adopcion> findById(@Param("id") int id);
	
	//Guardar adopcion
	void save(Adopcion adopcion) throws DataAccessException;

	@Query("SELECT adopcion FROM  Adopcion adopcion WHERE adopcion.dueno.id=:duenoAdoptivoId")
	public Collection<Adopcion> findAllByDuenoAdoptivo(@Param("duenoAdoptivoId") int duenoAdoptivoId);
	
	//Listado de Adopciones relacionadaas con X animal-- > Implementar más adelante
	//List<Adopcion> findByAnimalId(Integer animalId);
	@Query("SELECT adopcion FROM  Adopcion adopcion WHERE adopcion.dueno.id=:duenoAdoptivoId and adopcion.estado=0")
	public Collection<Adopcion>findSolicitadasByDuenoAdoptivo(@Param("duenoAdoptivoId") int duenoAdoptivoId);

	@Query("SELECT adopcion FROM  Adopcion adopcion WHERE adopcion.dueno.id=:duenoAdoptivoId and adopcion.estado=1")
	public Collection<Adopcion>findAceptadasByDuenoAdoptivo(@Param("duenoAdoptivoId") int duenoAdoptivoId);
	
	@Query("SELECT adopcion FROM  Adopcion adopcion WHERE adopcion.dueno.id=:duenoAdoptivoId and adopcion.estado=2")
	public Collection<Adopcion>findDenegadasByDuenoAdoptivo(@Param("duenoAdoptivoId") int duenoAdoptivoId);
	
	
	@Query("SELECT adopcion FROM  Adopcion adopcion WHERE adopcion.estado=0")
	public Collection<Adopcion>findAllSolicitadas();
	
	@Query("SELECT adopcion FROM  Adopcion adopcion WHERE adopcion.dueno.id=:duenoId and adopcion.estado=:estado")
	public Collection<Adopcion> findAdopcionEstadoByDuenoAdoptivo(@Param("estado")Estado estado, @Param("duenoId")Integer duenoId);
	
}
