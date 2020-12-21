/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;

/**
 * Spring Data JPA DueñoAdoptivoRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface DuenoAdoptivoRepository extends Repository<DuenoAdoptivo, Integer> {

	/**
	 * Save an <code>DueñoAdoptivo</code> to the data store, either inserting or
	 * updating it.
	 * 
	 * @param dueñoAdoptivo the <code>DueñoAdoptivo</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(DuenoAdoptivo dueñoAdoptivo) throws DataAccessException;

	/**
	 * Retrieve an <code>DueñoAdoptivo</code> from the data store by id.
	 * 
	 * @param id the id to search for
	 * @return the <code>DueñoAdoptivo</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
	@Query("SELECT duenoAdoptivo FROM DuenoAdoptivo duenoAdoptivo WHERE duenoAdoptivo.id =:id")
	public Optional<DuenoAdoptivo> findById(@Param("id") int id);

	@Query("SELECT duenoAdoptivo FROM DuenoAdoptivo duenoAdoptivo WHERE duenoAdoptivo.apellidos =:apellidos")
	public Set<DuenoAdoptivo> findByApellidos(@Param("apellidos") String apellidos);

	@Query("SELECT d FROM DuenoAdoptivo d")
	public Set<DuenoAdoptivo> findAll();

	@Query("SELECT d FROM DuenoAdoptivo d WHERE d.user.username=:duenoAdoptivoUserName")
	DuenoAdoptivo findByUserName(@Param("duenoAdoptivoUserName") String duenoAdoptivoUserName);

}