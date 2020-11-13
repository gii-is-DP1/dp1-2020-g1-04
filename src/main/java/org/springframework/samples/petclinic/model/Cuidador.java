package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	//----------------------------------------------------------------------------------------------------------------
	
	//Métodos---------------------------------------------------------------------------------------------------------
	public String getDni() {
		return this.dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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