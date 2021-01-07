package org.springframework.samples.petclinic.service;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

	CategoriaRepository categoriaRepository;

	
	@Transactional
	public void saveCategoria(@Valid Categoria categoria) {
		categoriaRepository.save(categoria);
		
	}


	public Categoria findCategoriaById(int categoriaId) {
		Categoria result;
		result =categoriaRepository.findCategoriaById(categoriaId);
		return result;
	}

}
