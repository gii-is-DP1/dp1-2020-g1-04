package org.springframework.samples.petclinic.model;


import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "director")
public class Director extends Person {
	/*
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User	user;
*/
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="centros_director")
	private Set<CentroDeAdopcion> centros;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Adopcion> adopciones;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="director")
	private Set<Evento> eventos;
	
	//HACER METODO LISTA DE CENTROS
	
	public Set<CentroDeAdopcion> getCentros(){
		return centros;
	}
	public void setCentros(Set<CentroDeAdopcion> centros) {
		this.centros =centros;
	}
	/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	*/
	public Set<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}
	public Collection<Adopcion> getAdopciones() {
		return adopciones;
	}
	public void setAdopciones(Collection<Adopcion> adopciones) {
		this.adopciones = adopciones;
	}
	

}
