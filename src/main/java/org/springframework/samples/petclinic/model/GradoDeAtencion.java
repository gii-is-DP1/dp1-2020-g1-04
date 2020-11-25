package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

@Embeddable
public class GradoDeAtencion {

	private Integer dificultad;
	
	private Integer atencion;
	
	public GradoDeAtencion() {
		super();
	}
	
	public GradoDeAtencion(Integer dificultad, Integer atencion) {
		this.dificultad = dificultad;
		this.atencion = atencion;
	}

	public Integer getDificultad() {
		return dificultad;
	}

	public Integer getAtencion() {
		return atencion;
	}

	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}

	public void setAtencion(Integer atencion) {
		this.atencion = atencion;
	}
	
	
}
