package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	//Test Positivo con Tipo.valueOf("x")
	@Test
	@Transactional
	public void tiposCategoriaNoError(){
	
		Categoria categoria=new Categoria();
		Optional<Categoria> aux = categoriaService.findCategoriaById(1);
		categoria.setRaza(aux.get().getRaza());
		categoria.setTipo(Tipo.valueOf("FELINO"));
		categoria.setTipo(Tipo.valueOf("CANINO"));
		categoria.setTipo(Tipo.valueOf("REPTIL"));
		categoria.setTipo(Tipo.valueOf("AVE"));
		categoriaService.saveCategoria(categoria);

	}
	
	@Test
	@Transactional
	public void tiposCategoriaHasErrors(){
	
		Categoria categoria=new Categoria();
		categoria.setRaza("Caniche");
		
		assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			categoriaService.saveCategoria(categoria);
			;
		});

	}
	
	//Test Negativo con Tipo.valueOf("x") x= otro valor diferente a CANINO,FELINO,REPTIL,AVE
	@Test
	@Transactional
	public void tiposCategoriaHasErrorsOtro(){
	
		Categoria categoria=new Categoria();
		Optional<Categoria> aux = categoriaService.findCategoriaById(1);
		categoria.setRaza(aux.get().getRaza());
		assertThrows(java.lang.IllegalArgumentException.class, () -> {
			categoria.setTipo(Tipo.valueOf("FELINO"));
			categoria.setTipo(Tipo.valueOf("CANINO"));
			categoria.setTipo(Tipo.valueOf("REPTIL"));
			categoria.setTipo(Tipo.valueOf("AVE"));
			categoria.setTipo(Tipo.valueOf("EQUINO"));
			categoriaService.saveCategoria(categoria);
			;
		});

	}

	
}
