package org.springframework.samples.petclinic.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "cuidador")
public class Cuidador extends Person {
	
	//Constructor-----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------
	
	//Atributos-------------------------------------------------------------------------------------------------------
	@Column(name = "dni")
	@NotEmpty
	private String dni;
	//----------------------------------------------------------------------------------------------------------------

	//Relaciones------------------------------------------------------------------------------------------------------
/*
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
*/
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cuidador")
	private Set<Animal> animales;
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="centro_de_adopcion_id")
	private CentroDeAdopcion centroDeAdopcion;
	

	@ManyToMany(mappedBy = "cuidadores")
	private Set<Evento> eventos;
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cuidador")
	private Collection<Curso> cursos ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cuidador")
	private Set<Visita> visitas;
	
	//----------------------------------------------------------------------------------------------------------------
	
	//Métodos---------------------------------------------------------------------------------------------------------
	public String getDni() {
		return this.dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	*/
	public Set<Animal> getAnimales(){
		return this.animales;
	}
	
	public void setAnimales(Set<Animal> animales) {
		this.animales = animales;
	}
	
	public CentroDeAdopcion getCentroDeAdopcion() {
		return centroDeAdopcion;
	}

	public void setCentroDeAdopcion(CentroDeAdopcion centroDeAdopcion) {
		this.centroDeAdopcion = centroDeAdopcion;
	}
	
	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

	public Collection<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Collection<Curso> cursos) {
		this.cursos = cursos;
	}

	public Set<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(Set<Visita> visitas) {
		this.visitas = visitas;
	}
	//----------------------------------------------------------------------------------------------------------------
	
	//ToString--------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("apellidos", this.getApellidos())
				.append("nombre", this.getNombre()).append("dni", this.dni).append("",this.getUser().getAuthorities()).toString();
	}
	//-----------------------------------------------------------------------------------------------------------------

	

	

	

}