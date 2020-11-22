package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class RequisitosDeAdopcion {

	private Boolean licenciaAdopcion;
	private Boolean seguro;
	
	public RequisitosDeAdopcion() {
		super();
	}
	
	public RequisitosDeAdopcion(Boolean licenciaAdopcion, Boolean seguro) {
		this.licenciaAdopcion = licenciaAdopcion;
		this.seguro = seguro;
	}

	public Boolean getLicenciaAdopcion() {
		return licenciaAdopcion;
	}

	public Boolean getSeguro() {
		return seguro;
	}
	
	
}