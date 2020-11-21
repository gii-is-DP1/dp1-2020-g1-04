package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Categoria;

public interface CategoryRepository extends CrudRepository<Categoria, Integer> {}
