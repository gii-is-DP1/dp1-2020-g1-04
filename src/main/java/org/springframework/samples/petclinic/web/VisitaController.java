package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.VisitaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitas")
public class VisitaController {
	
	private static final String VIEWS_VISITA_CREATE_OR_UPDATE_FORM="/createOrUpdateVisitaForm";
	
	private final VisitaService visitaService;
	
	@Autowired
	public VisitaController(VisitaService visitaService) {
		this.visitaService=visitaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("id");
	}

}
