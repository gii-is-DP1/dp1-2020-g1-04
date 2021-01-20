/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.DuenoAdoptivo;
import org.springframework.samples.petclinic.service.DuenoAdoptivoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class DuenoAdoptivoController {

	private static final String VIEWS_DUENOADOPTIVO_CREATE_OR_UPDATE_FORM = "duenosAdoptivos/createOrUpdateduenoAdoptivoForm";

	private final DuenoAdoptivoService duenoAdoptivoService;

	@Autowired
	public DuenoAdoptivoController(DuenoAdoptivoService duenoAdoptivoService) {
		this.duenoAdoptivoService = duenoAdoptivoService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/duenoAdoptivo/new")
	public String initCreationForm(Map<String, Object> model) {
		DuenoAdoptivo duenoAdoptivo = new DuenoAdoptivo();
		model.put("duenoAdoptivo", duenoAdoptivo);
		return VIEWS_DUENOADOPTIVO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/duenoAdoptivo/new")
	public String processCreationForm(@Valid DuenoAdoptivo duenoAdoptivo, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_DUENOADOPTIVO_CREATE_OR_UPDATE_FORM;
		} else {
			// creating duenoAdoptivo, user and authorities
			this.duenoAdoptivoService.saveDuenoAdoptivo(duenoAdoptivo);

			return "redirect:/duenosAdoptivos/" + duenoAdoptivo.getId();
		}
	}

	@GetMapping(value = "/duenosAdoptivos/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("duenoAdoptivo", new DuenoAdoptivo());
		return "duenosAdoptivos/findDuenosAdoptivos";
	}

	@GetMapping(value = "/duenosAdoptivos")
	public String findAll(Map<String, Object> model) {
		Set<DuenoAdoptivo> results;
		results = duenoAdoptivoService.findAllDuenosAdoptivos();
		model.put("selections", results);
		return "duenosAdoptivos/duenosAdoptivosList";
	}

	@GetMapping(value = "/duenosAdoptivos/busqueda")
	public String processFindForm(DuenoAdoptivo duenoAdoptivo, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /duenosAdoptivos to return all records
		if (duenoAdoptivo.getApellidos() == null) {
			duenoAdoptivo.setApellidos(""); // empty string signifies broadest possible search
		}

		// find duenosAdoptivos by last name
		Collection<DuenoAdoptivo> results = this.duenoAdoptivoService
				.findDuenoAdoptivoByApellidos(duenoAdoptivo.getApellidos());
		if (results.isEmpty()) {
			// no duenosAdoptivos found
			result.rejectValue("apellidos", "notFound", "not found");
			return "duenosAdoptivos/findDuenosAdoptivos";
		} else if (results.size() == 1) {
			// 1 duenoAdoptivo found
			duenoAdoptivo = results.iterator().next();
			return "redirect:/duenosAdoptivos/" + duenoAdoptivo.getId();
		} else {
			// multiple duenosAdoptivos found
			model.put("selections", results);
			return "duenosAdoptivos/duenosAdoptivosList";
		}
	}

	@GetMapping(value = "/duenosAdoptivos/edit/{duenoAdoptivoId}")
	public String initUpdateDuenoAdoptivoForm(@PathVariable("duenoAdoptivoId") int duenoAdoptivoId, Model model) {
		Optional<DuenoAdoptivo> duenoAdoptivo = this.duenoAdoptivoService.findDuenoAdoptivoById(duenoAdoptivoId);
		model.addAttribute("duenoAdoptivo", duenoAdoptivo.get());
		return VIEWS_DUENOADOPTIVO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/duenosAdoptivos/edit/{duenoAdoptivoId}")
	public String processUpdateDuenoAdoptivoForm(@Valid DuenoAdoptivo duenoAdoptivo, BindingResult result,
			@PathVariable("duenoAdoptivoId") int duenoAdoptivoId) {
		if (result.hasErrors()) {
			return VIEWS_DUENOADOPTIVO_CREATE_OR_UPDATE_FORM;
		} else {
			duenoAdoptivo.setId(duenoAdoptivoId);
			Optional<DuenoAdoptivo> aux = duenoAdoptivoService.findDuenoAdoptivoById(duenoAdoptivoId);
			// duenoAdoptivo.getUser().setAuthorities(aux.get().getUser().getAuthorities());
			this.duenoAdoptivoService.saveDuenoAdoptivo(duenoAdoptivo);
			return "redirect:/duenosAdoptivos/{duenoAdoptivoId}";
		}
	}

	/**
	 * Custom handler for displaying an duenoAdoptivo.
	 * 
	 * @param duenoAdoptivoId the ID of the duenoAdoptivo to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/duenosAdoptivos/{duenoAdoptivoId}")
	public ModelAndView showDuenoAdoptivo(@PathVariable("duenoAdoptivoId") int duenoAdoptivoId) {
		ModelAndView mav = new ModelAndView("duenosAdoptivos/duenoAdoptivoDetails");
		mav.addObject("duenoAdoptivo", this.duenoAdoptivoService.findDuenoAdoptivoById(duenoAdoptivoId).get());
		return mav;
	}

	@GetMapping("/duenosAdoptivos/show")
	public ModelAndView showDuenoAdoptivo2() {
		ModelAndView mav = new ModelAndView("duenosAdoptivos/duenoAdoptivoDetails");
		DuenoAdoptivo duenoAdoptivo = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		mav.addObject("duenoAdoptivo", duenoAdoptivo);
		return mav;
	}

	// Eitar dueño adoptivo desde el username(esto se debe que desde el Menú no
	// tiene la posibilidad de utilizar el id)
	@GetMapping(value = "/duenosAdoptivos/editByName")
	public String initUpdateDuenoAdoptivoForm2(Model model) {
		DuenoAdoptivo duenoAdoptivo = this.duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
		model.addAttribute(duenoAdoptivo);
		return VIEWS_DUENOADOPTIVO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/duenosAdoptivos/editByName")
	public String processUpdateDuenoAdoptivoForm2(@Valid DuenoAdoptivo duenoAdoptivo, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_DUENOADOPTIVO_CREATE_OR_UPDATE_FORM;
		} else {
			DuenoAdoptivo aux = duenoAdoptivoService.findDuenoAdoptivoByPrincipal();
			duenoAdoptivo.setId(aux.getId());
			// duenoAdoptivo.getUser().setAuthorities(aux.getUser().getAuthorities());
			this.duenoAdoptivoService.saveDuenoAdoptivo(duenoAdoptivo);
			int duenoAdoptivoId = duenoAdoptivo.getId();
			return "redirect:/duenosAdoptivos/" + duenoAdoptivoId;
		}
	}

}
