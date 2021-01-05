package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Comentario;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.repository.ComentarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

	private ComentarioRepository comentarioRepository;

	@Autowired
	private UserService userService;
	@Autowired
	private DuenoAdoptivoService duenoAdoptivoService;
	@Autowired
	private CuidadorService cuidadorService;
	@Autowired
	private DirectorService directorService;

	public void saveComentario(@Valid Comentario comentario) {
		String authority=userService.principalAuthorityString();
		String a=authority;
		switch (authority) 
	        {
	        	case "duenoadoptivo":
	            DuenoAdoptivo d=duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
	            comentario.setDueno(d);         
	            break;
	            case "cuidador":
	            Cuidador c=cuidadorService.findCuidadorByPrincipal();
	            comentario.setCuidador(c);       
	            break;
	            case "director":
	            Director di=directorService.findDirectorByPrincipal();
	            comentario.setDirector(di);      
	            break;
	            default: authority = "null";
	                     break;
	        }
		
		comentarioRepository.save(comentario);
		
	}

}
