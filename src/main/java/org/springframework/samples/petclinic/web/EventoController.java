package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.BusquedaVaciaException;
import org.springframework.samples.petclinic.service.exceptions.EventoSinCuidadoresAsignadosException;
import org.springframework.samples.petclinic.service.exceptions.ExcedidoAforoEventoException;
import org.springframework.samples.petclinic.service.exceptions.SinPermisoException;
import org.springframework.security.core.userdetails.User;
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

	private static final String VIEWS_EVENTO_CREATE_OR_UPDATE_FORM = "eventos/createOrUpdateEventoForm";
	private static final String EVENTOS_LISTING = "eventos/listadoEventos";

	private final EventoService eventoService;
	private final DirectorService directorService;
	private final CuidadorService cuidadorService;
	private final DuenoAdoptivoService duenoAdoptivoService;
	private final UserService userService;
	
	private final static LocalDate FECHA_HOY=LocalDate.now();

	@Autowired
	public EventoController(EventoService eventoService, CuidadorService cuidadorService,
			DirectorService directorService, DuenoAdoptivoService duenoAdoptivoService, UserService userService) {
		this.eventoService = eventoService;
		this.cuidadorService = cuidadorService;
		this.directorService = directorService;
		this.duenoAdoptivoService = duenoAdoptivoService;
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Evento evento = new Evento();
		Collection<Cuidador> cuidadores;
		cuidadores = cuidadorService.findAllCuidadores();
		model.put("evento", evento);
		model.put("cuidadores", cuidadores);
		return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/nuevo")
	public String processCreationForm(@Valid Evento evento, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
		} else {
			if(evento.getFecha().isBefore(FECHA_HOY) || evento.getFecha().isEqual(FECHA_HOY)) {
				result.rejectValue("fecha", "Futuro", "La Fecha debe ser futuro");
				return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
			}
			this.eventoService.saveEvento(evento);

			return "redirect:/eventos/show/" + evento.getId();
		}
	}

	@GetMapping(value = "/director/misEventos")
	public String listadoEventosDirector(ModelMap model) {
		Collection<Evento> eventos = eventoService.findEventosByDirector();
		model.addAttribute("eventos", eventos);
		return EVENTOS_LISTING;
	}

	// Muestra el evento al detalle
	@GetMapping(value = "/show/{eventoId}")
	public ModelAndView showEvento(@PathVariable("eventoId") int eventoId) {

		ModelAndView mav = new ModelAndView("eventos/showEvento");
		Collection<Cuidador> cuidadores = cuidadorService.findAllCuidadores();
		Evento evento = this.eventoService.findEventoById(eventoId).get();
		Collection<DuenoAdoptivo> duenos = evento.getDuenos();
		Integer inscrito = 0;
		if (duenos.contains(duenoAdoptivoService.findDuenoAdoptivoByPrincipal())) {
			inscrito = 1;
		}
		User user = userService.findPrincipal();
		Integer plazasDisponibles = evento.getAforo() - evento.getDuenos().size();
		cuidadores.removeAll(evento.getCuidadores());
		String role = user.getAuthorities().toString();
		mav.addObject("role", role);
		mav.addObject("plazasDisponibles", plazasDisponibles);
		mav.addObject(evento);
		mav.addObject("inscrito", inscrito);
		mav.addObject("cuidadores", cuidadores);
		return mav;
	}

	@GetMapping("/edit/{eventoId}")
	public String editEvento(@PathVariable("eventoId") int eventoId, ModelMap model) {
		Optional<Evento> evento = eventoService.findEventoById(eventoId);
		if (evento.isPresent()) {
			model.addAttribute("evento", evento.get());
			model.addAttribute("duenos", duenoAdoptivoService.findAllDuenosAdoptivos());
			model.addAttribute("cuidadores", cuidadorService.findAllCuidadores());
			return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("message", "No se encuentra el evento que quiere editar!");
			return listadoEventosDirector(model);
		}
	}

	@PostMapping("/edit/{eventoId}")
	public ModelAndView editEvento(@PathVariable("eventoId") int eventoId, @Valid Evento modifiedEvento,
			BindingResult binding, ModelMap model) {
		ModelAndView mav;
		if (binding.hasErrors()) {
			mav = new ModelAndView(VIEWS_EVENTO_CREATE_OR_UPDATE_FORM);
			return mav;
		} else {
			
			if(modifiedEvento.getFecha().isBefore(FECHA_HOY) || modifiedEvento.getFecha().isEqual(FECHA_HOY)) {
				binding.rejectValue("fecha", "Futuro", "La Fecha debe ser futuro");
				return new ModelAndView(VIEWS_EVENTO_CREATE_OR_UPDATE_FORM);
			}else {
			Optional<Evento> evento = eventoService.findEventoById(eventoId);
			modifiedEvento.setId(eventoId);
			modifiedEvento.setCuidadores(evento.get().getCuidadores());
			modifiedEvento.setDirector(evento.get().getDirector());
			modifiedEvento.setDuenos(evento.get().getDuenos());
			eventoService.saveEvento(modifiedEvento);
			model.addAttribute("message", "Evento actualizado con éxito");
			mav = new ModelAndView("redirect:/eventos/show/" + modifiedEvento.getId());
			
			return mav;
			}
			}
	}

	// Quita un cuidador x al evento y
	@GetMapping(value = "/{eventoId}/quitarCuidador/{cuidadorId}")
	public String quitarCuidadorEvento(@PathVariable("eventoId") int eventoId,
			@PathVariable("cuidadorId") int cuidadorId, ModelMap model) {
		try {
			Optional<Evento> even = eventoService.findEventoById(eventoId);
			Boolean b = even.isPresent();
			if (!b) {
				throw new BusquedaVaciaException("No Existe");
			}
			Evento evento = even.get();
			Optional<Cuidador> c = cuidadorService.findCuidadorById(cuidadorId);
			Boolean c1 = c.isPresent();
			if (!c1) {
				throw new BusquedaVaciaException("No Existe");
			}
			evento.getCuidadores().remove(c.get());
			Cuidador cuidador = c.get();
			eventoService.quitarCuidadorEvento(evento, cuidador);
		} catch (BusquedaVaciaException | EventoSinCuidadoresAsignadosException | SinPermisoException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return "redirect:/eventos/show/" + eventoId;
	}

	// Añade un cuidador x al evento y
	@GetMapping(value = "/{eventoId}/añadirCuidador/{cuidadorId}")
	public String añadirCuidadorEvento(@PathVariable("eventoId") int eventoId,
			@PathVariable("cuidadorId") int cuidadorId, ModelMap model) {
		try {
			Optional<Evento> even = eventoService.findEventoById(eventoId);
			Boolean b = even.isPresent();
			if (!b) {
				throw new BusquedaVaciaException("No existe");
			}
			Evento evento = even.get();
			Optional<Cuidador> c = cuidadorService.findCuidadorById(cuidadorId);
			Boolean c1 = c.isPresent();
			if (!c1) {
				throw new BusquedaVaciaException("No existe");
			}
			Cuidador cuidador = c.get();
			eventoService.añadirCuidadorEvento(evento, cuidador);
		} catch (SinPermisoException | BusquedaVaciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/eventos/show/" + eventoId;
	}

	// Carga los eventos del principal(duenoAdoptivo)
	@GetMapping(value = "/misEventos")
	public String listadoEventosDueno(ModelMap model) {
		Collection<Evento> eventos = eventoService.findEventosByDueno();
		model.addAttribute("eventos", eventos);
		return EVENTOS_LISTING;
	}

	@GetMapping(value = "/cuidador/misEventos")
	public String listadoEventosCuidador(ModelMap model) {
		Collection<Evento> eventos = eventoService.findEventosByCuidador();
		model.addAttribute("eventos", eventos);
		return EVENTOS_LISTING;
	}

	// Carga todos los eventos que tienen asignado algún Cuidador
	@GetMapping(value = "")
	public String listadoEventosDisponibles(ModelMap model) {
		Collection<Evento> eventos = eventoService.findEventosDisponibles();
		model.addAttribute("eventos", eventos);
		return EVENTOS_LISTING;
	}

	// Añade al principal(duenoAdoptivo) al evento x
	@GetMapping(value = "/{eventoId}/inscribirse")
	public ModelAndView añadirDuenoAdoptivoEvento(@PathVariable("eventoId") int eventoId, ModelMap model)
			throws ExcedidoAforoEventoException, EventoSinCuidadoresAsignadosException, BusquedaVaciaException {
		ModelAndView mav;
		try {
			Optional<Evento> e = eventoService.findEventoById(eventoId);
			// Comprueba que existe un evento con esa id
			Boolean b = e.isPresent();
			if (!b) {
				throw new BusquedaVaciaException("No existe");
			}
			Evento evento = e.get();
			eventoService.añadirDuenoAdoptivoEvento(evento);

		} catch (Exception ex) {
			mav = new ModelAndView("/403");
			mav.addObject("exceptionMessage", ex.getMessage());
			return mav;

		}
		mav = new ModelAndView("redirect:/eventos/show/" + eventoId);

		return mav;

	}

	// Quita al principal(duenoAdoptivo) al evento x
	@GetMapping(value = "/{eventoId}/borrarse")
	public ModelAndView quitarDuenoAdoptivoEvento(@PathVariable("eventoId") int eventoId, ModelMap model) {
		ModelAndView mav;
		try {
			Optional<Evento> e = eventoService.findEventoById(eventoId);
			Boolean b = e.isPresent();
			if (!b) {
				throw new BusquedaVaciaException("No existe");
			}
			Evento evento = e.get();
			eventoService.quitarDuenoAdoptivoEvento(evento);
		} catch (Exception ex) {
			mav = new ModelAndView("/403");
			mav.addObject("exceptionMessage", ex.getMessage());
			return mav;

		}
		mav = new ModelAndView("redirect:/eventos/show/" + eventoId);
		return mav;
	}

	// Eliminar evento
	@GetMapping(value = "/delete/{eventoId}")
	public ModelAndView eliminarEvento(@PathVariable("eventoId") int eventoId, ModelMap model) {
		ModelAndView mav;
		Director principal = directorService.findDirectorByPrincipal();
		try {
			Optional<Evento> e = eventoService.findEventoById(eventoId);
			Boolean b = e.isPresent();
			if (!b) {
				throw new BusquedaVaciaException("No existe");
			}
			Evento evento = e.get(); 
			if(!(principal.getId() == evento.getDirector().getId())) {	
				mav = new ModelAndView("redirect:/error-403");
				return mav;
			}
			eventoService.deleteEvento(evento);
		} catch (Exception ex) {
			mav = new ModelAndView("/403");
			mav.addObject("exceptionMessage", ex.getMessage());
			return mav;

		}
		mav = new ModelAndView("redirect:/eventos/director/misEventos");
		return mav;
	}

}
