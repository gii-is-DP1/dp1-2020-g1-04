package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;


public interface DirectorRepository extends Repository<Director, Integer>{
	
	void save(Director director) throws DataAccessException;
	
	@Query("SELECT director FROM Director director WHERE director.id =:id")
	public Director findById(@Param("id") int id);

	@Query("SELECT d FROM Director d WHERE d.user.username=:directorUserName")
	Director findByUserName(@Param("directorUserName")String directorUserName);

}
