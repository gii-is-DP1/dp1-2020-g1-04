package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Categoria extends BaseEntity {
	
	/* ATRIBUTOS */
	private Tipo tipo;
	
	@NotEmpty
	private String raza;
	
	/*// RELACIÓN
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
	private Set<Animal> animales;
*/
		
	/* TOSTRING */
	@Override
	public String toString() {
		return "Id: "        + this.getId() + 
			   "Categoria: " + this.tipo.name() + 
			   "Raza: "      + this.getRaza();
	}	
}
