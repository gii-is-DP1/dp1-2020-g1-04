package org.springframework.samples.petclinic.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Comentario;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.repository.ComentarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioService {

	private ComentarioRepository comentarioRepository;

	private final UserService userService;

	private final DuenoAdoptivoService duenoAdoptivoService;

	private final CuidadorService cuidadorService;

	private final DirectorService directorService;

	@Autowired
	public ComentarioService(ComentarioRepository comentarioRepository, UserService userService,
			DuenoAdoptivoService duenoAdoptivoService, CuidadorService cuidadorService,
			DirectorService directorService) {
		this.comentarioRepository = comentarioRepository;
		this.userService = userService;
		this.duenoAdoptivoService = duenoAdoptivoService;
		this.cuidadorService = cuidadorService;
		this.directorService = directorService;
	}

	@Transactional
	public void saveComentario(Comentario comentario) {
		comentarioRepository.save(comentario);

	}

	@Transactional
	public Comentario inicializarComentario(Visita visita) {
	Comentario comentario =new Comentario();
	comentario.setVisita(visita);
	LocalDateTime now = LocalDateTime.now();
	comentario.setMomento(now);
		String authority = userService.principalAuthorityString();
		switch (authority) {
		case "duenoadoptivo":
			DuenoAdoptivo d = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
			comentario.setDueno(d);
			break;
		case "cuidador":
			Cuidador c = cuidadorService.findCuidadorByPrincipal();
			comentario.setCuidador(c);
			break;
		case "director":
			Director di = directorService.findDirectorByPrincipal();
			comentario.setDirector(di);
			break;
		default:
			authority = "null";
			break;
		}
		
		return comentario;
	}

}
