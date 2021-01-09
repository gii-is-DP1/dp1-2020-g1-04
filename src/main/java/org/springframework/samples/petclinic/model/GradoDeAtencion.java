package org.springframework.samples.petclinic.model;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Range;

@Embeddable
public class GradoDeAtencion {

	@Range(min=0,max=10)
	private Integer dificultad;
	@Range(min=0,max=10)
	private Integer atencion;
		
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
