package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class RequisitosDeAdopcion {

	private Boolean licenciarequerida;
	private Boolean seguro;
	
	public RequisitosDeAdopcion() {
		super();
	}
	
	public RequisitosDeAdopcion(Boolean licenciarequerida, Boolean seguro) {
		this.licenciarequerida = licenciarequerida;
		this.seguro = seguro;
	}

	public Boolean getLicencia() {
		return licenciarequerida;
	}

	public Boolean getSeguro() {
		return seguro;
	}
	
	
}
