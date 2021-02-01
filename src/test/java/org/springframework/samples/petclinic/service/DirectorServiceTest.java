package org.springframework.samples.petclinic.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.DirectorRepository;

import javassist.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {
	
	@Autowired
	protected DirectorService directorService;

	@Mock
	private DirectorRepository directorRepository;
	
	@Mock
	private UserService userService;
//
//	@BeforeEach
//	void setup() {
//	     directorService = new DirectorService(directorRepository);
//	}
	
	private Director directorNuevo() {
		Director director = new Director();
		director.setNombre("Juan");
		director.setApellidos("perez");
		director.setTelefono("666777888");
		director.setEmail("fsfd@dfs.com");
		director.setId(13);
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		Authorities authorities = new Authorities();
		authorities.setAuthority("director");
		Set<Authorities> a = new HashSet<Authorities>();
		a.add(authorities);
		user.setAuthorities(a);
		director.setUser(user);
		return director;
	}
	
//	private BDDMyOngoingStubbing<Director> directorMock(){ 
//		
//		Director director =  directorNuevo();
//	  	return given(directorService.findDirectorByPrincipal()).willReturn(director);
//	  }
//	
//	private BDDMyOngoingStubbing<String> directorStringMock() {
//
//		return given(userService.principalAuthorityString()).willReturn("director");
//	}
	
	
	//Añadir Director
	@Test
	void addDirectorTest() {
		Director d= directorNuevo();
		
		directorService.saveDirector(d);
		
		
		assertThat(directorService.findDirectorById(13).getId()).isEqualTo(d.getId());
		
	}
	
	@Test
	void shouldNotAddDirectorTest() {
		Director dummy = null;
		List<Director> directores = new ArrayList<Director>();
		
		assertThrows(Exception.class, () -> directores.add(dummy));

	}

	//Encontrar director
	@Test
	void findDirectorByIdTest() {
		Director d = mock(Director.class);
		
		when(directorRepository.findById(1)).thenReturn(d);
		
		assertThat(directorRepository.findById(d.getId())).isEqualTo(d.getId());
	}
	
	@Test
	void findDirectorByUserNameTest() {
		Director d = mock(Director.class);
		
		when(directorRepository.findByUserName("Juan")).thenReturn(d);
		
		assertThat(directorRepository.findByUserName("Juan")).isEqualTo(d);
		
	}
	

}
