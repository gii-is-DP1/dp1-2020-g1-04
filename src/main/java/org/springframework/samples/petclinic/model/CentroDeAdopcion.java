package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="centroDeAdopcion")
public class CentroDeAdopcion extends NamedEntity {
	
	@NotEmpty
	@Column(name="nombre")
	private String nombre;
	
	@NotEmpty
	@Column(name="direccion")
	private String direccion;
	
	@NotEmpty
	@Column(name="cantidadMax")
	private Integer cantidadMax;
	
	
	//cantidad actual -> nº animales en este centro que no estén adoptados
	
	
	//HACE FALTA ENTIDAD ANIMAL
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "centro", fetch = FetchType.EAGER)
	//@OneToMany(targetEntity = Animal.class)
	//@JoinColumn(name = "animales_id")
	//private List<Animal> animales;

	/*@OneToMany(targetEntity = Cuidador.class)
	@JoinColumn(name = "cuidadores_id")
	private List<Cuidador> cuidadores;*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCantidadMax() {
		return cantidadMax;
	}

	public void setCantidadMax(Integer cantidadMax) {
		this.cantidadMax = cantidadMax;
	}

//	public List<Animal> getAnimales() {
//		return animales;
//	}
//
//	public void setAnimales(List<Animal> animales) {
//		this.animales = animales;
//	}
//	
//	public List<Cuidador> getCuidadores(){
//		return cuidadores;
//	}
//	
//	public void setCuidadores(List<Cuidador> cuidadores) {
//		this.cuidadores = cuidadores;
//	}
	
	
	
	

}
