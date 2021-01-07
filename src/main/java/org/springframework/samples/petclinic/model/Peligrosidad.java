package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Range;

@Embeddable
public class Peligrosidad {
	
	private Integer grado;
	
	private Boolean licencia;

	
	public Peligrosidad() {
		super();
	}
	
	public Peligrosidad(Integer grado, Boolean licencia) {
		this.grado = grado;
		this.licencia = licencia;
		
	}

	@Range(min=1,max=10)
	public Integer getGrado() {
		return grado;
	}

	public Boolean getLicencia() {
		return licencia;
	}

	public void setGrado(Integer grado) {
		this.grado = grado;
	}

	public void setLicencia(Boolean licencia) {
		this.licencia = licencia;
	}
	
	
}
