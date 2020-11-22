package org.springframework.samples.petclinic.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="centros")
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
	
	//Propiedad Derivada -> Cantidad actual de animales
//	public List<Animal> getCantidadActual(CentroDeAdopcion c){
//		List<Animal> r = new ArrayList<Animal>();
//		
//		return r
//	}
	
	//cantidad actual -> nº animales en este centro que no estén adoptados
	
	//Relacion Centro-Director
	@ManyToOne
	@JoinColumn(name="director_id")
	private Director director;
	
	//HACE FALTA ENTIDAD ANIMAL
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "centro", fetch = FetchType.EAGER)
	//@OneToMany(targetEntity = Animal.class)
	//@JoinColumn(name = "animales_id")
	//private List<Animal> animales;

//    @JoinTable(
//            name = "rel_centro_cuidador",
//            joinColumns = @JoinColumn(name = "centro_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name="cuidador_id", nullable = false)
//        )
//    @ManyToMany(cascade = CascadeType.ALL)
//	private Collection<Cuidador> cuidadores;
	
	//	MÉTODOS GET-SET
	///////////
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
	
	public Director getDirector() {
		return director;
	}
	
	public void setDirector(Director director) {
		this.director=director;
	}

//	public List<Animal> getAnimales() {
//		return animales;
//	}
//	
//	public void setAnimales(List<Animal> animales) {
//		this.animales = animales;
//	}
	
//	public Collection<Cuidador> getCuidadores(){
//		return cuidadores;
//	}
//	
//	public void setCuidadores(Collection<Cuidador> cuidadores) {
//		this.cuidadores = cuidadores;
//	}
//	
	
	
	

}
