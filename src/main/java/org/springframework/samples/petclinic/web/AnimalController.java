
package org.springframework.samples.petclinic.web;


import java.util.HashSet;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.CentroDeAdopcionService;
import org.springframework.samples.petclinic.service.CuidadorService;
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
@RequestMapping("/animales")
public class AnimalController {
	
	
	public static final String ANIMAL_LISTING = "animales/AnimalListing";
	public static final String ANIMAL_FORM = "animales/createOrUpdateAnimal";

	

	@Autowired
	AnimalService animalService;
	@Autowired
	CuidadorService cuidadorService;
	@Autowired
	CentroDeAdopcionService centroDeAdopcionService;
	
	
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	
	@GetMapping(value="")
	public String listAnimales(ModelMap model) {
		model.addAttribute("animales",animalService.findAll());
		return ANIMAL_LISTING;
	}
	

	@GetMapping(value="/show/{animalId}")
	public ModelAndView showAnimal(@PathVariable("animalId") int animalId) {
		ModelAndView mav = new ModelAndView("animales/showAnimal");
		mav.addObject(this.animalService.findAnimalById(animalId).get());
		return mav;
	}
	
	@GetMapping("/edit/{animalId}")
	public String editAnimal(@PathVariable("animalId") int animalId,ModelMap model) {
		Optional<Animal> animal=animalService.findAnimalById(animalId);
		if(animal.isPresent()) {
			model.addAttribute("animal",animal.get());
			model.addAttribute("tipos",Tipo.values());
			model.addAttribute("cuidadores", cuidadorService.findAllCuidadores());
			model.addAttribute("centros",centroDeAdopcionService.findAllNoEstenLlenos());
			return ANIMAL_FORM;
		}else {
			model.addAttribute("message","No se encuentra el animal que quiere editar!");
			return listAnimales(model);
		}
	}
	
	@PostMapping("/edit/{animalId}")
	public String editAnimal(@PathVariable("animalId") int animalId, @Valid Animal modifiedAnimal, BindingResult binding, ModelMap model) {
	
			if(binding.hasErrors()) {			
		
			return ANIMAL_FORM;
		}else {			
			Optional<Animal> animal=animalService.findAnimalById(animalId);
			Optional<Cuidador> c=cuidadorService.findCuidadorById(modifiedAnimal.getCuidador().getId());
			CentroDeAdopcion cda=centroDeAdopcionService.findById(modifiedAnimal.getCentroDeAdopcion().getId());
			modifiedAnimal.setId(animalId);
			modifiedAnimal.setCuidador(c.get());
			modifiedAnimal.setCentroDeAdopcion(cda);
			modifiedAnimal.getCategoria().setId(animal.get().getCategoria().getId());
			//animalService.save(modifiedAnimal);
			animalService.comprobarRatioCuidador(modifiedAnimal);
			model.addAttribute("message","Animal actualizado con exito!");
			return "redirect:/animales/show/"+modifiedAnimal.getId();
		}
	}
}

