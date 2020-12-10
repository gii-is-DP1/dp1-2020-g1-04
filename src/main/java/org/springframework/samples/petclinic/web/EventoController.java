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
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.DirectorService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping(value="/director/misEventos")
	public String listadoEventosDirector(ModelMap model) {
		Collection<Evento> eventos=eventoService.findEventosByDirector();
		model.addAttribute("eventos",eventos);
		return EVENTOS_LISTING;
	}

	//Muestra el evento al detalle
	@GetMapping(value="/show/{eventoId}")
	public ModelAndView showEvento(@PathVariable("eventoId") int eventoId) {
		ModelAndView mav = new ModelAndView("eventos/showEvento");
		Collection<Cuidador>cuidadores=cuidadorService.findAllCuidadores();
		Evento evento=this.eventoService.findEventoById(eventoId).get();
		Collection<DuenoAdoptivo>duenos=evento.getDuenos();
		Integer inscrito=0;
		if(duenos.contains(duenoAdoptivoService.findDuenoAdoptivoByPrincipal())){
			inscrito=1;
		}
		Integer plazasDisponibles=evento.getAforo()-evento.getDuenos().size();
		cuidadores.removeAll(evento.getCuidadores());
		mav.addObject("plazasDisponibles", plazasDisponibles);
		mav.addObject(evento);
		mav.addObject("inscrito",inscrito);
		mav.addObject("cuidadores",cuidadores);
		return mav;
	}
	
	//Quita un cuidador x al evento y
	@GetMapping(value="/{eventoId}/quitarCuidador/{cuidadorId}")
	public String quitarCuidadorEvento(@PathVariable("eventoId") int eventoId, @PathVariable("cuidadorId") int cuidadorId, ModelMap model) {
		eventoService.quitarCuidadorEvento(eventoId, cuidadorId);
		return "redirect:/eventos/show/"+ eventoId;
	}
	
	//Añade un cuidador x al evento y
	@GetMapping(value="/{eventoId}/añadirCuidador/{cuidadorId}")
	public String añadirCuidadorEvento(@PathVariable("eventoId") int eventoId, @PathVariable("cuidadorId") int cuidadorId, ModelMap model) {
		eventoService.añadirCuidadorEvento(eventoId, cuidadorId);
		return "redirect:/eventos/show/"+ eventoId;
	}
	
	
	//Carga los eventos del principal(duenoAdoptivo)
	@GetMapping(value="/misEventos")
	public String listadoEventosDueno(ModelMap model) {
		Collection<Evento> eventos=eventoService.findEventosByDueno();
		model.addAttribute("eventos",eventos);
		return EVENTOS_LISTING;
	}
	
	
	//Carga todos los eventos que tienen asignado algún Cuidador
	@GetMapping(value="")
	public String listadoEventosDisponibles(ModelMap model) {
		Collection<Evento> eventos=eventoService.findEventosDisponibles();
		model.addAttribute("eventos",eventos);
		return EVENTOS_LISTING;
	}
	
	//Añade al principal(duenoAdoptivo) al evento x
	@GetMapping(value="/{eventoId}/inscribirse")
	public String añadirDuenoAdoptivoEvento(@PathVariable("eventoId") int eventoId,  ModelMap model) {
		eventoService.añadirDuenoAdoptivoEvento(eventoId);
		return "redirect:/eventos/show/"+ eventoId;
	}
		
	//Quita al principal(duenoAdoptivo) al evento x
	@GetMapping(value="/{eventoId}/borrarse")
	public String quitarDuenoAdoptivoEvento(@PathVariable("eventoId") int eventoId,  ModelMap model) {
		eventoService.quitarDuenoAdoptivoEvento(eventoId);
		return "redirect:/eventos/show/"+ eventoId;
		}
	
}
