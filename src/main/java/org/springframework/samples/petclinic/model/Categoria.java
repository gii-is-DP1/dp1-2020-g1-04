package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Categoria extends BaseEntity {
	
	/* ATRIBUTOS */
	@NotNull
	private Tipo tipo;
	
	@NotEmpty
	private String raza;
	
	
		
	/* TOSTRING */
	@Override
	public String toString() {
		return "Id: "        + this.getId() + 
			   "Categoria: " + this.tipo.name() + 
			   "Raza: "      + this.getRaza();
	}	
}
