package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "centros")
public class CentroDeAdopcion extends BaseEntity {

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String direccion;

	@NotEmpty
	private Integer cantidadMax;

	// Relacion Centro-Director
	@ManyToOne
	@JoinColumn(name = "director_id")
	private Director director;

	// HACE FALTA ENTIDAD ANIMAL
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "centroDeAdopcion")
	private Collection<Animal> animales;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "centroDeAdopcion")
	private Collection<Cuidador> cuidadores;

}
