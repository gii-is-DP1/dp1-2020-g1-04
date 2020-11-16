package org.springframework.samples.petclinic.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "director")
public class Director extends Person {
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User	user;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="centros_director")
	private Set<CentroDeAdopcion> centros;
	
	//HACER METODO LISTA DE CENTROS
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
