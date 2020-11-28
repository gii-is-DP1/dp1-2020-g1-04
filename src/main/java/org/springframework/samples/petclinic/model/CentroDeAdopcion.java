package org.springframework.samples.petclinic.model;



import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	

	//Relacion Centro-Director
	@ManyToOne
	@JoinColumn(name="director_id")
	private Director director;
	
	//HACE FALTA ENTIDAD ANIMAL
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "centroDeAdopcion")
	private Collection<Animal> animales;


	@OneToMany(cascade=CascadeType.ALL, mappedBy="centroDeAdopcion")
	private Collection<Cuidador> cuidadores;
	
	//	MÉTODOS GET-SET
	

	////////
	
	
	public int getCantidadActual(){
		int cantidadActual=0;
		for(Animal a:this.animales) {
			if(a.getAdoptado()==false) {
				cantidadActual++;
			}
		}
		return cantidadActual;
	}
	

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

	public Collection<Animal> getAnimales() {
		return animales;
	}
	
	public void setAnimales(Collection<Animal> animales) {
		this.animales = animales;
	}
	
	public Collection<Cuidador> getCuidadores(){
		return cuidadores;
	}
	
	public void setCuidadores(Collection<Cuidador> cuidadores) {
		this.cuidadores = cuidadores;
	}
	
	
	
	

}
