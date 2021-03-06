package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Enfermedad;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.EnfermedadService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enfermedad")
public class EnfermedadController {

	private static final String VIEWS_ENFERMEDAD_CREATE_OR_UPDATE_FORM = "enfermedad/createOrUpdateEnfermedadForm";
	private static final String ENFERMEDADES_SHOW = "enfermedad/showEnfermedad";
	private static final String ENFERMEDADES_LIST = "enfermedad/listadoEnfermedades";

	private final EnfermedadService enfermedadService;
	private final AnimalService animalService;
	private final CuidadorService cuidadorService;

	@Autowired
	public EnfermedadController(EnfermedadService enfermedadService, AnimalService animalService,
			CuidadorService cuidadorService) {
		this.enfermedadService = enfermedadService;
		this.animalService = animalService;
		this.cuidadorService = cuidadorService;

	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/nuevo/{animalId}")
	public String initCreationForm(Map<String, Object> model, @PathVariable("animalId") int animalId) {
		Enfermedad enfermedad = new Enfermedad();
		Optional<Animal> animal = animalService.findAnimalById(animalId);
		enfermedad.setAnimal(animal.get());
		Cuidador principal = cuidadorService.findCuidadorByPrincipal();
		if(!(principal.getId()== animal.get().getCuidador().getId())) {
			return "error-403";
		}
		model.put("enfermedad", enfermedad);
		return VIEWS_ENFERMEDAD_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/nuevo/{animalId}")
	public String processCreationForm(@Valid Enfermedad enfermedad, BindingResult result,
			@PathVariable("animalId") int animalId, Map<String, Object> model) {
		String errorFecha = "";
		if (result.hasErrors()) {
			return VIEWS_ENFERMEDAD_CREATE_OR_UPDATE_FORM;
		} else {
			if (enfermedad.getFin() != null) {
				if (enfermedad.getFin().isBefore(enfermedad.getComienzo())) {
					errorFecha = "La fecha de fin tiene que ser después de la de inicio";
					model.put("errorFecha", errorFecha);
					return VIEWS_ENFERMEDAD_CREATE_OR_UPDATE_FORM;
				}
			}
			Optional<Animal> animal = animalService.findAnimalById(animalId);
			enfermedad.setAnimal(animal.get());
			Cuidador principal = cuidadorService.findCuidadorByPrincipal();
			if(!(principal.getId()== animal.get().getCuidador().getId())) {
				return "error-403";
			}
			this.enfermedadService.saveEnfermedad(enfermedad);
			model.put("errorFecha", errorFecha);
			return "redirect:/enfermedad/show/" + enfermedad.getId();
		}
	}

	@GetMapping(value = "/show/{enfermedadId}")
	public String showEnfermedad(Map<String, Object> model, @PathVariable("enfermedadId") int enfermedadId) {
		Optional<Enfermedad> enfermedad = enfermedadService.findEnfermedadById(enfermedadId);
		model.put("enfermedad", enfermedad.get());
		return ENFERMEDADES_SHOW;
	}

	@GetMapping(value = "/{animalId}")
	public String listadoEnfermedad(Map<String, Object> model, @PathVariable("animalId") int animalId) {
		Collection<Enfermedad> enfermedades = enfermedadService.findAllEnfermedadByAnimalId(animalId);
		model.put("enfermedades", enfermedades);
		return ENFERMEDADES_LIST;
	}

	@GetMapping(value = "/cuidador")
	public String listadoEnfermedadAnimalByCuidador(Map<String, Object> model) {
		Cuidador principal = cuidadorService.findCuidadorByPrincipal();
		Collection<Enfermedad> enfermedades = enfermedadService.findAllEnfermedadAnimalByCuidadorId(principal.getId());
		model.put("enfermedades", enfermedades);
		return ENFERMEDADES_LIST;
	}

}
