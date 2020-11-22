package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class RequisitosDeAdopcion {

	private Boolean licencia;
	private Boolean seguro;
	
	public RequisitosDeAdopcion() {
		super();
	}
	
	public RequisitosDeAdopcion(Boolean licencia, Boolean seguro) {
		this.licencia = licencia;
		this.seguro = seguro;
	}

	public Boolean getLicencia() {
		return licencia;
	}

	public Boolean getSeguro() {
		return seguro;
	}
	
	
}
