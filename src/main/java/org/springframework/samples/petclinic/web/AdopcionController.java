package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.service.AdopcionService;
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
	
	private static final String VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM="adopcion/createOrUpdateadopcionForm";
	
	private final AdopcionService adopcionService;
	
	private final UserService userService;
	
	@Autowired
	public AdopcionController(AdopcionService adopcionService, UserService userService) {
		this.adopcionService = adopcionService;
		this.userService=userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("id");
	}
	
	//Crear nuevo formulario de adopcion
	@GetMapping(value="/adopcion/new")
	public String initCreationForm(Map<String, Object> model) {
		Adopcion adopcion = new Adopcion();
		model.put("adopcion", adopcion);
		return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value="/adopcion/new")
	public String processCreationForm(@Valid Adopcion adopcion, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
		}
		else {
		
			this.adopcionService.saveAdopcion(adopcion);
			
			return "redirect:/adopcion/show/"+ adopcion.getId();
		}
	}
	
	//Mostrar adopcion
	@GetMapping(value = "/adopcion/{adopcionId}")
	public ModelAndView showAdopcion(@PathVariable("adopcionId") int adopcionId) {
		ModelAndView mav = new ModelAndView("adopcion/showAdopcion");
		mav.addObject(this.adopcionService.findAdopcionById(adopcionId));
		return mav;
	}
	
	//Listar adopcion
	@GetMapping(value = "/adopcion/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("adopcion", new Adopcion());
		return "adopcion/findAdopciones";
	}
	
	@GetMapping(value = "/adopcion/findAll")
	public String findAll(Map<String, Object> model) {
		Collection<Adopcion> results;
		results=adopcionService.findAll();
		model.put("adopciones", results);
		return "adopcion/adopcionList";
	}
	
	
	//Editar adopcion
	@GetMapping(value = "/adopcion/{adopcionId}/edit")
	public String initUpdateDuenoAdoptivoForm(@PathVariable("adopcionId") int adopcionId, Model model) {
		Adopcion adopcion = this.adopcionService.findAdopcionById(adopcionId);
		model.addAttribute(adopcion);
		if(!userService.findPrincipal().getAuthorities().toString().contentEquals("director")) {
			return "/403";
		}
		return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/adopcion/{adopcionId}/edit")
	public String processUpdateDuenoAdoptivoForm(@Valid Adopcion adopcion, BindingResult result,
			@PathVariable("adopcionId") int adopcionId) {
		if (result.hasErrors()) {
			return VIEWS_ADOPCION_CREATE_OR_UPDATE_FORM;
		}
		else {
			adopcion.setId(adopcionId);

			return "redirect:/adopcion/{adopcionId}";
		}
	}
	

	
}
