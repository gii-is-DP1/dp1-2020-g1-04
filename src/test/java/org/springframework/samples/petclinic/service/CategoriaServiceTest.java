package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.samples.petclinic.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CategoriaServiceTest {
	
	@Autowired
	protected CategoriaService categoriaService;	
	
	

	//Regla de Negocio 1 - Comprobar unica categoria
	@Test
	@Transactional
	public void tiposCategoria() throws Exception{
		
		Categoria categoria=new Categoria();
		categoria.setRaza("Agua");
		categoria.setId(2);
		//categoria AVE
		categoria.setTipo(Tipo.CANINO);
		categoriaService.saveCategoria(categoria);
		Categoria aux= categoriaService.findCategoriaById(2).get();
		System.out.println("CATEGORIA: " + aux);
		assertThat(aux.getTipo().equals(Tipo.CANINO));
		
		//categoria CANINO
		categoria.setTipo(Tipo.AVE);
		categoriaService.saveCategoria(categoria);
		aux=categoriaService.findCategoriaById(2).get();
		System.out.println("CATEGORIA: " + aux);
		assertThat(aux.getTipo().equals(Tipo.AVE));
		
		//categoria REPTIL
		categoria.setTipo(Tipo.REPTIL);
		categoriaService.saveCategoria(categoria);
		aux=categoriaService.findCategoriaById(2).get();
		System.out.println("CATEGORIA: " + aux);
		assertThat(aux.getTipo().equals(Tipo.REPTIL));
		
		//categoria FELINO
		categoria.setTipo(Tipo.FELINO);
		categoriaService.saveCategoria(categoria);
		aux=categoriaService.findCategoriaById(2).get();
		System.out.println("CATEGORIA: " + aux);
		assertThat(aux.getTipo().equals(Tipo.FELINO));	
		
	}
	
	
	@Test
	@Transactional
	public void tiposCategoriaHasErrors() throws NullPointerException{
	
		Categoria categoria=new Categoria();
		Optional<Categoria> aux = categoriaService.findCategoriaById(1);
		categoria = aux.get();
		categoria.setTipo(null);
		categoriaService.saveCategoria(categoria);
		
//		assertThrows(NullPointerException.class, 
//				() -> categoriaService.saveCategoria(categoria));

	}
//		
//		//categoria CANINO
//		categoria.setTipo(Tipo.AVE);
//		categoriaService.saveCategoria(categoria);
//		aux=categoriaService.findCategoriaById(2).get();
//		assertThat(aux.getTipo().equals(Tipo.AVE));
//		
//		//categoria REPTIL
//		categoria.setTipo(Tipo.REPTIL);
//		categoriaService.saveCategoria(categoria);
//		aux=categoriaService.findCategoriaById(2).get();
//		assertThat(aux.getTipo().equals(Tipo.REPTIL));
//		
//		//categoria FELINO
//		categoria.setTipo(Tipo.FELINO);
//		categoriaService.saveCategoria(categoria);
//		aux=categoriaService.findCategoriaById(2).get();
//		assertThat(aux.getTipo().equals(Tipo.FELINO));	

	
}
