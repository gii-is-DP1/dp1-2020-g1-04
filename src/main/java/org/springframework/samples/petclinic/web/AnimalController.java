package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
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

	@Autowired
	AnimalService animalService;
	@Autowired
	CuidadorService cuidadorService;
	@Autowired
	CentroDeAdopcionService centroDeAdopcionService;
	@Autowired
	private final CategoriaService categoriaService;

	public AnimalController(AnimalService animalService, CategoriaService categoriaService) {
		this.animalService = animalService;
		this.categoriaService = categoriaService;
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
		if (animal.isPresent()) {
			ArrayList <Integer> auxiliar=animalService.listaAuxiliar();
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
	public ModelAndView editAnimal(@PathVariable("animalId") int animalId, @Valid Animal modifiedAnimal,
			BindingResult binding, ModelMap model) {
		ModelAndView mav;
		if (binding.hasErrors()) {

			mav = new ModelAndView(ANIMAL_FORM);
			return mav;
		} else {
			Optional<Animal> animal = animalService.findAnimalById(animalId);
			//Optional<Cuidador> c = cuidadorService.findCuidadorById(modifiedAnimal.getCuidador().getId());
			//CentroDeAdopcion cda = centroDeAdopcionService.findById(modifiedAnimal.getCentroDeAdopcion().getId());
		//	modifiedAnimal.setId(animalId);
			//modifiedAnimal.setCuidador(c.get());
			//modifiedAnimal.setCentroDeAdopcion(cda);
			modifiedAnimal.setNumeroRegistro(animal.get().getNumeroRegistro());
			modifiedAnimal.setCategoria(animal.get().getCategoria());
			modifiedAnimal.setFechaPrimeraIncorporacion(animal.get().getFechaPrimeraIncorporacion());
			modifiedAnimal.setFechaUltimaIncorporacion(animal.get().getFechaUltimaIncorporacion());
			// animalService.save(modifiedAnimal);
			try {
				animalService.comprobarRatioCuidador(modifiedAnimal);
			} catch (RatioAnimalesPorCuidadorSuperadoException e) {
				// TODO Auto-generated catch block

				mav = new ModelAndView("/403");
				mav.addObject("exceptionMessage", e.getMessage());
				return mav;
			} catch (AforoCentroCompletadoException e) {
				// TODO Auto-generated catch block
				mav = new ModelAndView("/403");
				mav.addObject("exceptionMessage", e.getMessage());
				return mav;
			}
			model.addAttribute("message", "Animal actualizado con exito!");
			mav = new ModelAndView("redirect:/animales/show/" + modifiedAnimal.getId());
			return mav;
		}
	}
	
	@GetMapping("/nuevo/{categoriaId}")
	public String nuevoAnimal(@PathVariable("categoriaId") int categoriaId, ModelMap model) {
		ArrayList <Integer> auxiliar=animalService.listaAuxiliar();
		Animal animal=new Animal();
		LocalDate now=LocalDate.now();
		Categoria categoria=categoriaService.findCategoriaById(categoriaId).get();
		animal.setCategoria(categoria);
		animal.setAdoptado(false);
		animal.setFechaPrimeraIncorporacion(now);
		animal.setFechaUltimaIncorporacion(now);
		String nRgistro=animalService.nuevoNRegistro(categoria.getTipo().toString());
		animal.setNumeroRegistro(nRgistro);
		model.put("auxiliar", auxiliar);
		model.put("animal", animal);
		model.put("cuidadores", cuidadorService.findAllCuidadores());
		model.put("centros", centroDeAdopcionService.findAllNoEstenLlenos());
		return ANIMAL_FORM;
	}

	@PostMapping(value = "/nuevo/{categoriaId}")
	public String nuevoAnimal(@PathVariable("categoriaId") int categoriaId,@Valid Animal animal, BindingResult result) throws AforoCentroCompletadoException {
		Categoria categoria=categoriaService.findCategoriaById(categoriaId).get();
		animal.setCategoria(categoria);
		LocalDate now=LocalDate.now();
		//String nRgistro=animalService.nuevoNRegistro(categoria.getTipo().toString());
		//animal.setNumeroRegistro(nRgistro);
		animal.setFechaPrimeraIncorporacion(now);
		animal.setFechaUltimaIncorporacion(now);
		if (result.hasErrors()) {
			return ANIMAL_FORM;
		} else {
			this.animalService.save(animal);

			return "redirect:/animales/show/" + animal.getId();
		}
	}
	
	@GetMapping("/reincorporar/{animalId}")
	public String reincorporarAnimal(@PathVariable("animalId") int animalId, ModelMap model) throws RatioAnimalesPorCuidadorSuperadoException, AforoCentroCompletadoException {

		Optional<Animal> animal=animalService.findAnimalById(animalId);
		if (animal.isPresent()) {
			animal.get().setAdoptado(false);
			LocalDate now=LocalDate.now();
			animal.get().setFechaUltimaIncorporacion(now);
			animalService.comprobarRatioCuidador(animal.get());
			return "redirect:/animales/show/" + animalId;
		}else {
			model.addAttribute("message", "No se encuentra el animal que quiere editar!");
			return listAnimales(model);
	}
	}
	@GetMapping(value = "/listaAsignados")
	public String listAnimalesAsignados(ModelMap model) {
		Cuidador principal=cuidadorService.findCuidadorByPrincipal();
		int cuidadorId= principal.getId();
		Collection<Animal> animalesCanino;
		Collection<Animal> animalesFelino;
		Collection<Animal> animalesReptil;
		Collection<Animal> animalesAve;
		animalesCanino=animalService.findAnimalAsignadoCanino(cuidadorId);
		animalesFelino=animalService.findAnimalAsignadoFelino(cuidadorId);
		animalesReptil=animalService.findAnimalAsignadoReptil(cuidadorId);
		animalesAve=animalService.findAnimalAsignadoAve(cuidadorId);
		model.addAttribute("animalesCanino", animalesCanino);
		model.addAttribute("animalesFelino", animalesFelino);
		model.addAttribute("animalesReptil", animalesReptil);
		model.addAttribute("animalesAve", animalesAve);
		return ANIMAL_LISTING_CATEGORIA;
	}
	
	
	}
