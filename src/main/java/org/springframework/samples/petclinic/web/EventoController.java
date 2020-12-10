package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.DirectorService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	
	private static final String VIEWS_EVENTO_CREATE_OR_UPDATE_FORM="eventos/createOrUpdateEventoForm";
	private static final String EVENTOS_LISTING="eventos/listadoEventos";
	
	private final EventoService eventoService;
	private final CuidadorService cuidadorService;
	private final DirectorService directorService;
	private final DuenoAdoptivoService duenoAdoptivoService;
	
	@Autowired
	public EventoController(EventoService eventoService, CuidadorService cuidadorService, DirectorService directorService, DuenoAdoptivoService duenoAdoptivoService) {
		this.eventoService=eventoService;
		this.cuidadorService=cuidadorService;
		this.directorService=directorService;
		this.duenoAdoptivoService=duenoAdoptivoService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
	@GetMapping(value = "/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Evento evento = new Evento();
		Collection<Cuidador> cuidadores;
		cuidadores=cuidadorService.findAllCuidadores();
		model.put("evento", evento);
		model.put("cuidadores", cuidadores);
		return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/nuevo")
	public String processCreationForm(@Valid Evento evento, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.eventoService.saveEvento(evento);
			
			return "redirect:/eventos/show/" + evento.getId();
		}
	}

	
	
}
