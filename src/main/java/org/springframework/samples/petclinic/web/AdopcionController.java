package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.Estado;
import org.springframework.samples.petclinic.service.AdopcionService;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdopcionController {

	private static final String VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM = "adopcion/createOrUpdateAdopcionForm";
	private static final String VIEWS_ADOPCION_ACTUALIZAR_ESTADO_FORM = "adopcion/actualizarEstadoForm";

	private final AdopcionService adopcionService;

	private final UserService userService;

	private final DuenoAdoptivoService duenoAdoptivoService;

	private final AnimalService animalService;

	@Autowired
	public AdopcionController(AdopcionService adopcionService, UserService userService,
			DuenoAdoptivoService duenoAdoptivoService, AnimalService animalService) {
		this.adopcionService = adopcionService;
		this.userService = userService;
		this.duenoAdoptivoService = duenoAdoptivoService;
		this.animalService = animalService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	// Crear nuevo formulario de adopcion
	@GetMapping(value = "/adopcion/new/{animalId}")
	public String initCreationForm(@PathVariable("animalId") int animalId, Map<String, Object> model) {
		Adopcion adopcion = new Adopcion();
		adopcion.setEstado(Estado.PENDIENTE);
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		adopcion.setDueno(dueno);
		Optional<Animal> animal = animalService.findAnimalById(animalId);
		adopcion.setAnimal(animal.get());
		model.put("adopcion", adopcion);
		return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/adopcion/new/{animalId}")
	public String processCreationForm(@PathVariable("animalId") int animalId, @Valid Adopcion adopcion,
			BindingResult result) {
		adopcion.setEstado(Estado.PENDIENTE);
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		adopcion.setDueno(dueno);
		Optional<Animal> animal = animalService.findAnimalById(animalId);
		adopcion.setAnimal(animal.get());
		if (result.hasErrors()) {
			return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
		} else {

			this.adopcionService.saveAdopcion(adopcion);

			return "redirect:/adopcion/show/" + adopcion.getId();
		}
	}

	// Mostrar adopcion
	@GetMapping(value = "/adopcion/{adopcionId}")
	public ModelAndView showAdopcion(@PathVariable("adopcionId") int adopcionId) {
		ModelAndView mav = new ModelAndView("adopcion/showAdopcion");
		mav.addObject(this.adopcionService.findAdopcionById(adopcionId));
		return mav;
	}

	// Listar adopcion
	@GetMapping(value = "/adopcion/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("adopcion", new Adopcion());
		return "adopcion/findAdopciones";
	}

	@GetMapping(value = "/adopcion")
	public String findAll(Map<String, Object> model) {
		Collection<Adopcion> results;
		results = adopcionService.findAll();
		model.put("adopciones", results);
		return "adopcion/adopcionList";
	}

	// Editar adopcion
	@GetMapping(value = "/adopcion/edit/{adopcionId}")
	public String initUpdateDuenoAdoptivoForm(@PathVariable("adopcionId") int adopcionId, Model model) {
		Optional<Adopcion> adopcion = this.adopcionService.findAdopcionById(adopcionId);
		model.addAttribute(adopcion.get());
		if (!userService.findPrincipal().getAuthorities().toString().contentEquals("director")) {
			return "redirect: /403";
		}
		return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/adopcion/edit/{adopcionId}")
	public String processUpdateDuenoAdoptivoForm(@Valid Adopcion adopcion, BindingResult result,
			@PathVariable("adopcionId") int adopcionId) {
		if (result.hasErrors()) {
			return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
		} else {
			adopcion.setId(adopcionId);
			this.adopcionService.saveAdopcion(adopcion);
			return "redirect:/adopcion/{adopcionId}";
		}
	}

	@GetMapping(value = "/adopcion/findAllByDuenoAdoptivo/{duenoAdoptivoId}")
	public String findAll(@PathVariable("duenoAdoptivoId") int duenoAdoptivoId, Map<String, Object> model) {
		Collection<Adopcion> results;
		results = adopcionService.findAllByDuenoAdoptivo(duenoAdoptivoId);
		model.put("adopciones", results);
		return "adopcion/adopcionList";
	}

	@GetMapping(value = "/adopcion/misSolicitudesDeAdopcion")
	public String findAllByDuenoAdoptivo(Map<String, Object> model) {
		DuenoAdoptivo dueno = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		Collection<Adopcion> solicitadas = adopcionService.findAdopcionEstadoByDuenoAdoptivo(Estado.PENDIENTE,
				dueno.getId());
		Collection<Adopcion> aceptadas = adopcionService.findAdopcionEstadoByDuenoAdoptivo(Estado.ACEPTADA,
				dueno.getId());
		Collection<Adopcion> denegadas = adopcionService.findAdopcionEstadoByDuenoAdoptivo(Estado.DENEGADA,
				dueno.getId());
		model.put("solicitadas", solicitadas);
		model.put("aceptadas", aceptadas);
		model.put("denegadas", denegadas);
		return "adopcion/adopcionList2";
	}

	@GetMapping(value = "/adopcion/pendientes")
	public String findAllPendientes(Map<String, Object> model) {
		Collection<Adopcion> pendientes = adopcionService.findAllSolicitadas();
		model.put("adopciones", pendientes);
		return "adopcion/adopcionList";
	}

	@GetMapping(value = "/adopcion/show/{adopcionId}")
	public String showAdopcion(@PathVariable("adopcionId") int adopcionId, Map<String, Object> model) {
		Optional<Adopcion> adopcionOpt = adopcionService.findAdopcionById(adopcionId);
			Adopcion adopcion= adopcionOpt.get();
			DuenoAdoptivo principal=duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
			if(!(adopcion.getDueno().getId()==principal.getId())) {
			return "error-403";
			}
			model.put("adopcion", adopcion);
			return "adopcion/showAdopcion";		
	}

	// Actualizar adopcion
	@GetMapping(value = "/adopcion/actualizarEstado/{adopcionId}")
	public String actualizarEstado(@PathVariable("adopcionId") int adopcionId, Model model) {
		Optional<Adopcion> adopcion = this.adopcionService.findAdopcionById(adopcionId);
		model.addAttribute("adopcion", adopcion.get());
		model.addAttribute("estados", Estado.values());
		if (!userService.principalAuthorityString().contains("director")) {
			return "redirect: /403";
		}
		return VIEWS_ADOPCION_ACTUALIZAR_ESTADO_FORM;
	}

	@PostMapping(value = "/adopcion/actualizarEstado/{adopcionId}")
	public String actualizarEstado(@Valid Adopcion adopcion, BindingResult result,
			@PathVariable("adopcionId") int adopcionId, Model model) {
		Optional<Adopcion> adopcionAux = this.adopcionService.findAdopcionById(adopcionId);
		Adopcion aux = adopcionAux.get();
		adopcion.setAnimal(aux.getAnimal());
		adopcion.setDueno(aux.getDueno());
		adopcion.setFechaDecision(LocalDate.now());
		adopcion.setLeidoRequisitos(aux.getLeidoRequisitos());
		adopcion.setMayoresDeEdad(aux.getMayoresDeEdad());
		adopcion.setMotivo(aux.getMotivo());
		adopcion.setOtrosAnimales(aux.getOtrosAnimales());
		adopcion.setPermisoComunidadVecinos(aux.getPermisoComunidadVecinos());
		adopcion.setUnidadFamiliar(aux.getUnidadFamiliar());
		adopcion.setId(adopcionId);
		if (!userService.principalAuthorityString().contains("director")) {
			return "redirect: /403";
		}
		if (result.hasErrors()) {
			return VIEWS_ADOPCION_ACTUALIZAR_ESTADO_FORM;
		} else {
			if(adopcion.getAnimal().getAdoptado() && adopcion.getEstado() == Estado.ACEPTADA) {
				model.addAttribute("estados", Estado.values()); 
				result.rejectValue("motivoDecision", "Adoptado", "Este animal ya ha sido adoptado");
				 return VIEWS_ADOPCION_ACTUALIZAR_ESTADO_FORM;
			}
			adopcionService.saveAdopcion(adopcion);
			if (adopcion.getEstado() == Estado.ACEPTADA) {
				animalService.adoptado(adopcion.getAnimal());
			}

			return "redirect:/adopcion/show/{adopcionId}";
		}
	}

}
