package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Director;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.DirectorService;
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
public class DirectorController {

	private static final String VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM = "director/createOrUpdateOwnerForm";

	private final DirectorService directorService;
	
	@Autowired
	public DirectorController(DirectorService directorService, UserService userService, AuthoritiesService authoritiesService) {
		this.directorService = directorService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/director/new")
	public String initCreationForm(Map<String, Object> model) {
		Director director = new Director();
		model.put("director", director);
		return VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/director/new")
	public String processCreationForm(@Valid Director director, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating director, user and authorities
			this.directorService.saveDirector(director);
			
			return "redirect:/owners/" + director.getId();
		}
	}
	
	
	@GetMapping("/director/{directorId}")
	public ModelAndView showDirector(@PathVariable("directorId") int directorId) {
		ModelAndView mav = new ModelAndView("director/directorDetails");
		mav.addObject(this.directorService.findDirectorById(directorId));
		return mav;
	}
}
