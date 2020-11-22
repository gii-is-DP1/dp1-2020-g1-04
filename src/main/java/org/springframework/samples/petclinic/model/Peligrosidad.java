package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class Peligrosidad {
	
	private Integer grado;
	
	private Boolean licenciaPeligrosidad;

	
	public Peligrosidad() {
		super();
	}
	
	public Peligrosidad(Integer grado, Boolean licenciaPeligrosidad) {
		this.grado = grado;
		this.licenciaPeligrosidad = licenciaPeligrosidad;
		
	}

	public Integer getGrado() {
		return grado;
	}

	public Boolean getLicenciaPeligrosidad() {
		return licenciaPeligrosidad;
	}
	
	
}