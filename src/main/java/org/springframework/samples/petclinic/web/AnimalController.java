package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Animal;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.samples.petclinic.service.AnimalService;
import org.springframework.samples.petclinic.service.CategoriaService;
import org.springframework.samples.petclinic.service.CentroDeAdopcionService;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.exceptions.AforoCentroCompletadoException;
import org.springframework.samples.petclinic.service.exceptions.RatioAnimalesPorCuidadorSuperadoException;
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
	public static final String ANIMAL_LISTING_CATEGORIA = "animales/AnimalListingCategoria";
	public static final String ANIMAL_FORM = "animales/createOrUpdateAnimal";

	private final AnimalService animalService;

	private final CuidadorService cuidadorService;

	private final CentroDeAdopcionService centroDeAdopcionService;

	private final CategoriaService categoriaService;
	
	@Autowired
	public AnimalController(AnimalService animalService, CuidadorService cuidadorService,
			CentroDeAdopcionService centroDeAdopcionService, CategoriaService categoriaService) {
		this.animalService = animalService;
		this.categoriaService = categoriaService;
		this.centroDeAdopcionService = centroDeAdopcionService;
		this.cuidadorService = cuidadorService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/todos")
	public String listAnimales(ModelMap model) {
		model.addAttribute("animales", animalService.findAll());
		return ANIMAL_LISTING;
	}

	@GetMapping(value = "")
	public String listAnimalesDisponibles(ModelMap model) {
		model.addAttribute("animales", animalService.findAllNoAdoptados());
		return ANIMAL_LISTING;
	}

	@GetMapping(value = "/show/{animalId}")
	public ModelAndView showAnimal(@PathVariable("animalId") int animalId) {
		ModelAndView mav = new ModelAndView("animales/showAnimal");
		mav.addObject(this.animalService.findAnimalById(animalId).get());
		return mav;
	}

	@GetMapping("/edit/{animalId}")
	public String editAnimal(@PathVariable("animalId") int animalId, ModelMap model) {
		Optional<Animal> animal = animalService.findAnimalById(animalId);
//		Director principal = directorService.findDirectorByPrincipal();
//		Integer directorCentroAnimal = animal.get().getCentroDeAdopcion().getDirector().getId();
//		if(!(principal.getId() == directorCentroAnimal)) {
//			return "error-403";
//		}
		if (animal.isPresent()) {
			ArrayList<Integer> auxiliar = animalService.listaAuxiliar();
			model.put("auxiliar", auxiliar);
			model.addAttribute("animal", animal.get());
			model.addAttribute("tipos", Tipo.values());
			model.addAttribute("cuidadores", cuidadorService.findAllCuidadores());
			model.addAttribute("centros", centroDeAdopcionService.findAllNoEstenLlenos());
			return ANIMAL_FORM;
		} else {
			model.addAttribute("message", "No se encuentra el animal que quiere editar!");
			return listAnimales(model);
		}
	}

	@PostMapping("/edit/{animalId}")
	public String editAnimal(@PathVariable("animalId") int animalId, @Valid Animal modifiedAnimal,
			BindingResult result, ModelMap model) {
		ArrayList<Integer> auxiliar = animalService.listaAuxiliar();
//		Director principal = directorService.findDirectorByPrincipal();
//		Integer directorCentroAnimal = modifiedAnimal.getCentroDeAdopcion().getDirector().getId();
//		if(!(principal.getId() == directorCentroAnimal)) {
//			return "error-403";
//		}
		if (result.hasErrors()) {
			model.put("auxiliar", auxiliar);
			model.put("animal", modifiedAnimal);
			model.put("cuidadores", cuidadorService.findAllCuidadores());
			model.put("centros", centroDeAdopcionService.findAllNoEstenLlenos());
			return ANIMAL_FORM;
		} else {
			Optional<Animal> animalOpt = animalService.findAnimalById(animalId);
			Animal animal=animalOpt.get();
			modifiedAnimal.setId(animalId);
			modifiedAnimal.setEnfermedades(animal.getEnfermedades());
			modifiedAnimal.setNumeroRegistro(animal.getNumeroRegistro());
			modifiedAnimal.setCategoria(animal.getCategoria());
			modifiedAnimal.setFechaPrimeraIncorporacion(animal.getFechaPrimeraIncorporacion());
			modifiedAnimal.setFechaUltimaIncorporacion(animal.getFechaUltimaIncorporacion());
			try {
				animalService.save(modifiedAnimal);
			} catch (RatioAnimalesPorCuidadorSuperadoException e) {
				result.rejectValue("centroDeAdopcion", "Aforo", "Aforo del centro al máximo");
				return ANIMAL_FORM;
			} catch (AforoCentroCompletadoException e) {
				result.rejectValue("centroDeAdopcion", "Aforo", "Aforo del centro al máximo");
				return ANIMAL_FORM;
			}
			model.addAttribute("message", "Animal actualizado con exito!");
			//mav = new ModelAndView("redirect:/animales/show/" + modifiedAnimal.getId());
			return "redirect:/animales/show/" + modifiedAnimal.getId();
		}
	}

	@GetMapping("/nuevo/{categoriaId}")
	public String nuevoAnimal(@PathVariable("categoriaId") int categoriaId, ModelMap model) {
		ArrayList<Integer> auxiliar = animalService.listaAuxiliar();
		Categoria categoria = categoriaService.findCategoriaById(categoriaId).get();
		Animal animal=new Animal();
		Animal animalModificado= animal;
		animalModificado=animalService.inicializarAnimal(categoria, animal);
		model.put("auxiliar", auxiliar);
		model.put("animal", animalModificado);
		model.put("cuidadores", cuidadorService.findAllCuidadores());
		model.put("centros", centroDeAdopcionService.findAllNoEstenLlenos());
		return ANIMAL_FORM;
	}

	@PostMapping(value = "/nuevo/{categoriaId}")
	public String nuevoAnimal(@PathVariable("categoriaId") int categoriaId, @Valid Animal animal, BindingResult result, ModelMap model)
			throws AforoCentroCompletadoException {
		Categoria categoria = categoriaService.findCategoriaById(categoriaId).get();		
		Animal animalModificado=	animalService.inicializarAnimal(categoria, animal);
		ArrayList<Integer> auxiliar = animalService.listaAuxiliar();
		if (result.hasErrors()) {
			model.put("auxiliar", auxiliar);
			model.put("animal", animalModificado);
			model.put("cuidadores", cuidadorService.findAllCuidadores());
			model.put("centros", centroDeAdopcionService.findAllNoEstenLlenos());
			return ANIMAL_FORM;
		} else {
			try {
				animalService.save(animalModificado);
			} catch (RatioAnimalesPorCuidadorSuperadoException e) {
				 result.rejectValue("centroDeAdopcion", "Ratio", "Supera el Ratio de Cuidadores");
				 return ANIMAL_FORM;
			} catch (AforoCentroCompletadoException e) {
				result.rejectValue("centroDeAdopcion", "Aforo", "Aforo del centro al máximo");
				return ANIMAL_FORM;
			}
			
			return "redirect:/animales/show/" + animal.getId();
		}
	}

	@GetMapping("/reincorporar/{animalId}")
	public String reincorporarAnimal(@PathVariable("animalId") int animalId, ModelMap model)
			throws RatioAnimalesPorCuidadorSuperadoException, AforoCentroCompletadoException {

		Optional<Animal> animal = animalService.findAnimalById(animalId);
		if (animal.isPresent()) {
			Animal animalModificado=animalService.reincorporarAnimal(animal.get());
			animalService.save(animalModificado);
			return "redirect:/animales/show/" + animalId;
		} else {
			model.addAttribute("message", "No se encuentra el animal que quiere editar!");
			return listAnimales(model);
		}
	}

	@GetMapping(value = "/listaAsignados")
	public String listAnimalesAsignados(ModelMap model) {
		Cuidador principal = cuidadorService.findCuidadorByPrincipal();
		int cuidadorId = principal.getId();
		Collection<Animal> animalesCanino;
		Collection<Animal> animalesFelino;
		Collection<Animal> animalesReptil;
		Collection<Animal> animalesAve;
		animalesCanino = animalService.findAnimalAsignadoTipo(Tipo.CANINO, cuidadorId);
		animalesFelino = animalService.findAnimalAsignadoTipo(Tipo.FELINO, cuidadorId);
		animalesReptil = animalService.findAnimalAsignadoTipo(Tipo.REPTIL, cuidadorId);
		animalesAve = animalService.findAnimalAsignadoTipo(Tipo.AVE, cuidadorId);
		model.addAttribute("animalesCanino", animalesCanino);
		model.addAttribute("animalesFelino", animalesFelino);
		model.addAttribute("animalesReptil", animalesReptil);
		model.addAttribute("animalesAve", animalesAve);
		return ANIMAL_LISTING_CATEGORIA;
	}
	
	@GetMapping(value = "listAnimales/{centroId}")
	public String listAnimalesDisponibles(@PathVariable("centroId") int centroId,ModelMap model) {
		model.addAttribute("animales", animalService.findAllNoAdoptedByCentro(centroId));
		return ANIMAL_LISTING;
	}

}
