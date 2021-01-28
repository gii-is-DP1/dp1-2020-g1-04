package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Tipo;
import org.springframework.samples.petclinic.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria")
public class CategoriaContoller {

	private final CategoriaService categoriaService;
	private static final String VIEWS_CATEGORIA_CREATE_OR_UPDATE_FORM = "categoria/createOrUpdateCategoriaForm";

	@Autowired
	public CategoriaContoller(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Categoria categoria = new Categoria();
		model.put("tipos", Tipo.values());
		model.put("categoria", categoria);
		return VIEWS_CATEGORIA_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/nuevo")
	public String processCreationForm(@Valid Categoria categoria, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CATEGORIA_CREATE_OR_UPDATE_FORM;
		} else {
			this.categoriaService.saveCategoria(categoria);

			return "redirect:/animales/nuevo/" + categoria.getId();
		}
	}

}
