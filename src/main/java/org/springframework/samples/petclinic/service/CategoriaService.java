package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

	CategoriaRepository categoriaRepository;

	@Transactional
	public void save(Categoria categoria) {
		categoriaRepository.save(categoria);

	}

}
