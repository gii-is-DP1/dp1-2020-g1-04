package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.service.CentroDeAdopcionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/centros")
public class CentroDeAdopcionController {

	private final CentroDeAdopcionService centroDeAdopcionService;

	@Autowired
	public CentroDeAdopcionController(CentroDeAdopcionService centroDeAdopcionService) {
		this.centroDeAdopcionService= centroDeAdopcionService;
	}
	// ESTO se haría si queremos mostrar 1 solo centro
	/*
	 * @GetMapping(value = "/find") public String initFindForm(Map<String, Object>
	 * model) { model.put("centro", new CentroDeAdopcion()); return
	 * "centrosDeAdopcion/showCentro"; }
	 */

	// LISTADO DE CENTROS DE ADOPCION
	@GetMapping(value = "")
	public String findAll(Map<String, Object> model) {
		Collection<CentroDeAdopcion> results;
		results = centroDeAdopcionService.findAll();
		model.put("centros", results);
		return "centrosDeAdopcion/centrosListing";
	}
}
