package org.springframework.samples.petclinic.web;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Comentario;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.service.ComentarioService;
import org.springframework.samples.petclinic.service.VisitaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {

	private static final String VIEWS_COMENTARIO_CREATE_OR_UPDATE_FORM = "comentario/createOrUpdateComentarioForm";

	private ComentarioService comentarioService;
	private VisitaService visitaService;

	@Autowired
	public ComentarioController(ComentarioService comentarioService, VisitaService visitaService) {
		this.comentarioService = comentarioService;
		this.visitaService = visitaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/new/{visitaId}")
	public String crearComentarioDueno(Map<String, Object> model, @PathVariable("visitaId") int visitaId) {
		Visita visita = visitaService.findVisitaById(visitaId);
		Comentario comentario=comentarioService.inicializarComentario(visita);
		model.put("comentario", comentario);
		return VIEWS_COMENTARIO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new/{visitaId}")
	public String processCreationForm(@Valid Comentario comentario, BindingResult result,
			@PathVariable("visitaId") int visitaId) {
		if (result.hasErrors()) {
			return VIEWS_COMENTARIO_CREATE_OR_UPDATE_FORM;
		} else {
			Visita visita = visitaService.findVisitaById(visitaId);
			Comentario comentarioAux=comentarioService.inicializarComentario(visita);
			comentario.setVisita(comentarioAux.getVisita());
			comentario.setMomento(comentarioAux.getMomento());
			comentario.setCuidador(comentarioAux.getCuidador());
			comentario.setDirector(comentarioAux.getDirector());
			comentario.setDueno(comentarioAux.getDueno());
			this.comentarioService.saveComentario(comentario);

			return "redirect:/visitas/show/" + visita.getId();
		}
	}

}
