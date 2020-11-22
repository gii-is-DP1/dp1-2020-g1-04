package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cuidador;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.CuidadorService;
import org.springframework.samples.petclinic.service.UserService;

import org.springframework.stereotype.Controller;
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
	
	

	@Autowired
	public CuidadorController(CuidadorService cuidadorService, UserService userService, AuthoritiesService authoritiesService) {
		this.cuidadorService = cuidadorService;
		this.userService=userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
	@GetMapping(value = "/cuidadores/findAll")
	public String findAll(Map<String, Object> model) {
	
		org.springframework.security.core.userdetails.User user = userService.findPrincipal();
	if(user==null)	{
		return "403";
	}
		String r=user.getAuthorities().toString();
		Boolean res= r.contains("admin");
		
	 if(res==true){
		Set<Cuidador> results;
		results=cuidadorService.findAllCuidadores();
		model.put("selections", results);
		return "cuidadores/listadoCuidadores";
	 	}else {
	 		return "403";
	 	}
	}
	
	@GetMapping("/cuidadores/{cuidadorId}")
	public ModelAndView showDuenoAdoptivo(@PathVariable("cuidadorId") int cuidadorId) {
		org.springframework.security.core.userdetails.User user = userService.findPrincipal();
		if(user==null)	{
			ModelAndView mav = new ModelAndView("403");
			return mav;
		}
			String r=user.getAuthorities().toString();
			Boolean res= r.contains("director") || r.contains("cuidador");
			
		 if(res==true){
		ModelAndView mav = new ModelAndView("cuidadores/detallesCuidador");
		Cuidador cuidador=this.cuidadorService.findDuenoAdoptivoById(cuidadorId);
		mav.addObject(cuidador);
		return mav;
	}else {
		ModelAndView mav = new ModelAndView("403");
		return mav;
	}
	}
	
	
	@GetMapping(value = "/cuidadores/findAllByCentro/{centroId}")
	public ModelAndView findAllCuidadoresPorCentro(@PathVariable("centroId") int centroId) {
		Set<Cuidador> results;
		results=cuidadorService.findAllCuidadoresPorCentro(centroId);
		ModelAndView mav = new ModelAndView("cuidadores/listadoCuidadores");
		mav.addObject(results);
		return mav;
	}
	

	
	@GetMapping(value = "/cuidador/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Cuidador cuidador = new Cuidador();
		model.put("cuidador", cuidador);
		return VIEWS_CUIDADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/cuidador/nuevo")
	public String processCreationForm(@Valid Cuidador cuidador, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CUIDADOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating cuidador, user and authorities
			this.cuidadorService.saveCuidador(cuidador);
			
			return "redirect:/cuidadores/" + cuidador.getId();
		}
	}
	
}
