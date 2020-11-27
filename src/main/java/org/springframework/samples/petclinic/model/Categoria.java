package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria extends BaseEntity {
	
	/* ATRIBUTOS */
	@JoinColumn(name = "tipo", referencedColumnName = "tipo")
	private Tipo tipo;
	
	@Column(name = "raza")
	@NotEmpty
	private String raza;
	
	/* CONSTRUCTORES */
	public Categoria() {}

	public Categoria(Tipo tipo, String raza) {
		this.tipo = tipo;
		this.raza = raza;
	}

	// RELACIÓN
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
	private Set<Animal> animales;
	
	/* MÉTODOS */
	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String getRaza() {
		return this.raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}
	
	/* TOSTRING */
	@Override
	public String toString() {
		return "Id: "        + this.getId() + 
			   "Categoria: " + this.tipo.name() + 
			   "Raza: "      + this.getRaza();
	}	
}
