package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.service.CentroDeAdopcionService;
import org.springframework.samples.petclinic.service.CuidadorService;
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
public class CuidadorController {

	private static final String VIEWS_CUIDADOR_CREATE_OR_UPDATE_FORM = "cuidadores/crearOActualizarCuidador";

	private final CuidadorService cuidadorService;
	private final UserService userService;
	private final CentroDeAdopcionService centroDeAdopcionService;

	@Autowired
	public CuidadorController(CuidadorService cuidadorService, UserService userService,
			CentroDeAdopcionService centroDeAdopcionService) {
		this.cuidadorService = cuidadorService;
		this.userService = userService;
		this.centroDeAdopcionService = centroDeAdopcionService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/cuidadores")
	public String findAll(Map<String, Object> model) {

		org.springframework.security.core.userdetails.User user = userService.findPrincipal();
		if (user == null) {
			return "403";
		}
		String r = user.getAuthorities().toString();
		Boolean res = r.contains("director");

		if (res == true) {
			Set<Cuidador> results;
			results = cuidadorService.findAllCuidadores();
			model.put("selections", results);
			return "cuidadores/listadoCuidadores";
		} else {
			return "403";
		}
	}

	@GetMapping("/cuidadores/show/{cuidadorId}")
	public ModelAndView showDuenoAdoptivo(@PathVariable("cuidadorId") int cuidadorId) {
		ModelAndView mav = new ModelAndView("cuidadores/detallesCuidador");
		Optional<Cuidador> cuidador = this.cuidadorService.findCuidadorById(cuidadorId);
		mav.addObject(cuidador.get());
		return mav;
		
	}

	@GetMapping("/cuidadores/show")
	public ModelAndView showCuidadorPrincipal() {

		ModelAndView mav = new ModelAndView("cuidadores/detallesCuidador");
		Cuidador cuidador = cuidadorService.findCuidadorByPrincipal();
		mav.addObject(cuidador);
		return mav;
	}

	@GetMapping(value = "/cuidadores/findAllByCentro/{centroId}")
	public String findAllCuidadoresPorCentro(@PathVariable("centroId") int centroId, Map<String, Object> model) {
		Set<Cuidador> selections;
		selections = cuidadorService.findAllCuidadoresPorCentro(centroId);
		model.put("selections", selections);
		return "cuidadores/listadoCuidadores";
	}

	@GetMapping(value = "/cuidador/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Cuidador cuidador = new Cuidador();
		Collection<CentroDeAdopcion> centros = centroDeAdopcionService.findAll();
		model.put("centros", centros);
		model.put("cuidador", cuidador);
		return VIEWS_CUIDADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/cuidador/nuevo")
	public String processCreationForm(@Valid Cuidador cuidador, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CUIDADOR_CREATE_OR_UPDATE_FORM;
		} else {
			// creating cuidador, user and authorities
			this.cuidadorService.saveCuidador(cuidador);

			return "redirect:/cuidadores/show/" + cuidador.getId();
		}
	}

	@GetMapping(value = "/cuidadores/{cuidadorId}/directorEdit")
	public String processUpdateCuidadorForm(@PathVariable("cuidadorId") int cuidadorId, Model model) {
		Optional<Cuidador> cuidador = this.cuidadorService.findCuidadorById(cuidadorId);
		model.addAttribute(cuidador.get());

		Collection<CentroDeAdopcion> centros = centroDeAdopcionService.findAll();
		model.addAttribute("centros", centros);

		return VIEWS_CUIDADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/cuidadores/{cuidadorId}/directorEdit")
	public String processUpdateCuidadorForm(@Valid Cuidador cuidador, BindingResult result,
			@PathVariable("cuidadorId") int cuidadorId, Model model) {
		if (result.hasErrors()) {
			Collection<CentroDeAdopcion> centros = centroDeAdopcionService.findAll();
			model.addAttribute("centros", centros);
			return VIEWS_CUIDADOR_CREATE_OR_UPDATE_FORM;
		} else {
			Optional<Cuidador> aux = cuidadorService.findCuidadorById(cuidadorId);
			cuidador.setEventos(aux.get().getEventos());
			cuidador.setId(cuidadorId);

			this.cuidadorService.saveCuidador(cuidador);

			return "redirect:/cuidadores/show/{cuidadorId}";
		}
	}

}
