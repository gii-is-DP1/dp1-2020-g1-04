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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitas")
public class VisitaController {

	private static final String VIEWS_VISITA_CREATE_OR_UPDATE_FORM = "/visitas/createOrUpdateVisitaForm";
	private static final String VISITAS_LISTING = "/visitas/listadoVisitas";
	private static final String VISITAS_DETALLES = "/visitas/showVisita";

	private final VisitaService visitaService;

	@Autowired
	public VisitaController(VisitaService visitaService) {
		this.visitaService = visitaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/misVisitas")
	public String listadoVisistasByPrincipal(ModelMap model) {
		Collection<Visita> visitas = visitaService.findVisitasByPrincipalProximas();
		Collection<Visita> visitasPasadas=visitaService.findVisitasByPrincipalPasadas();
		model.addAttribute("visitas", visitas);
		model.addAttribute("visitasPasadas",visitasPasadas);
		return VISITAS_LISTING;
	}
	@GetMapping(value = "/cuidador/misVisitas")
	public String listadoVisistasByPrincipalCuidador(ModelMap model) {
		Collection<Visita> visitas = visitaService.findVisitasByCuidadorPrincipalProximas();
		Collection<Visita> visitasPasadas=visitaService.findVisitasByCuidadorPrincipalPasadas();
		model.addAttribute("visitas", visitas);
		model.addAttribute("visitasPasadas",visitasPasadas);
		return VISITAS_LISTING;
	}
	
	@GetMapping(value = "/show/{visitaId}")
	public String DetalleVisita(ModelMap model, @PathVariable("visitaId") int visitaId) {
		Visita visita = visitaService.findVisitaById(visitaId);
		model.addAttribute("visita", visita);
		model.addAttribute("comentarios", visita.getComentarios());
		return VISITAS_DETALLES;
	}

	
}
