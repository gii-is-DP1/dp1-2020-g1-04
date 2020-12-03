package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Visita;
import org.springframework.samples.petclinic.service.VisitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitas")
public class VisitaController {
	
	private static final String VIEWS_VISITA_CREATE_OR_UPDATE_FORM="/visitas/createOrUpdateVisitaForm";
	private static final String VISITAS_LISTING="/visitas/listadoVisitas";
	
	private final VisitaService visitaService;
	
	@Autowired
	public VisitaController(VisitaService visitaService) {
		this.visitaService=visitaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("id");
	}

	@GetMapping(value="/misVisitas")
	public String listadoVisistasByPrincipal(ModelMap model) {
		Collection<Visita> visitas=visitaService.findVisitasByPrincipal();
		model.addAttribute("visitas",visitas);
		return VISITAS_LISTING;
	}
	
}
