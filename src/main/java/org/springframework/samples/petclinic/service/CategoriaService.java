package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

	
	private CategoriaRepository categoriaRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
		
	}
	
	@Transactional
	public void saveCategoria(@Valid Categoria categoria) {
		categoriaRepository.save(categoria);
		
	}

	@Transactional
	public Optional<Categoria> findCategoriaById(int categoriaId) {
		Optional<Categoria> result;
		result =categoriaRepository.findCategoriaById(categoriaId);
		return result;
	}

}
