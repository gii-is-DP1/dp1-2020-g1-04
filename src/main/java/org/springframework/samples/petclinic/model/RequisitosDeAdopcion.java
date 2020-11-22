package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class RequisitosDeAdopcion {

	private Boolean licenciaadopcion;
	private Boolean seguro;
	
	public RequisitosDeAdopcion() {
		super();
	}
	
	public RequisitosDeAdopcion(Boolean licenciaadopcion, Boolean seguro) {
		this.licenciaadopcion = licenciaadopcion;
		this.seguro = seguro;
	}

	public Boolean getLicencia() {
		return licenciaadopcion;
	}

	public Boolean getSeguro() {
		return seguro;
	}
	
	
}
