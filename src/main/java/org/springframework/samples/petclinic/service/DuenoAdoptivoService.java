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
package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.DuenoAdoptivoRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class DuenoAdoptivoService {

	private DuenoAdoptivoRepository duenoAdoptivoRepository;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public DuenoAdoptivoService(DuenoAdoptivoRepository duenoAdoptivoRepository) {
		this.duenoAdoptivoRepository = duenoAdoptivoRepository;
	}	

	@Transactional(readOnly = true)
	public DuenoAdoptivo findDuenoAdoptivoById(int id) throws DataAccessException {
		return duenoAdoptivoRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Set<DuenoAdoptivo> findDuenoAdoptivoByApellidos(String apellidos) throws DataAccessException {
		return duenoAdoptivoRepository.findByApellidos(apellidos);
	}


	@Transactional
	public void saveDuenoAdoptivo(DuenoAdoptivo duenoAdoptivo) throws DataAccessException {
		//creating duenoAdoptivo
		duenoAdoptivoRepository.save(duenoAdoptivo);	
		//creating user
		userService.saveUser(duenoAdoptivo.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(duenoAdoptivo.getUser().getUsername(), "duenoadoptivo");
	}
	
	@Transactional
	public Set<DuenoAdoptivo> findAllDuenosAdoptivos() {
		Set<DuenoAdoptivo> result;
		result=duenoAdoptivoRepository.findAll();
		
		return result;
	}

	@Transactional
	public DuenoAdoptivo findDuenoAdoptivoByUserName(String duenoAdoptivoUserName) {
		DuenoAdoptivo result;
		result=duenoAdoptivoRepository.findByUserName(duenoAdoptivoUserName);
		return result;
	}
	
	
		

}
