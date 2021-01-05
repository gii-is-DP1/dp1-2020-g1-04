/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
@Getter
@Setter
@Entity
@Table(name = "duenos")
public class DuenoAdoptivo extends Person {

	@Column(name = "direccion")
	@NotEmpty
	private String direccion;

	@Column(name = "dni")
	@NotEmpty
	private String dni;
	
	/*
	//
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	//
	*/
	
	//Relacion DuenoAdoptivo-Adopcion
	@OneToMany(cascade=CascadeType.ALL, mappedBy="dueno")
	private Collection<Adopcion> adopciones ;
	
	//Relación DuenoAdoptivo-Visita
	@OneToMany(cascade=CascadeType.ALL, mappedBy="dueno")
	private Collection<Visita> vistitas;
	
	//Relacion DuenoAdoptivo-Evento
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "duenos_eventos",
				joinColumns = @JoinColumn(name = "dueno_id"),
				inverseJoinColumns = @JoinColumn(name = "evento_id"))
	private Set<Evento> eventos;
	
	
	//getters and setters
	
	
	public Collection<Adopcion> getAdopciones(){
		return adopciones;
	}
	
	public void setAdopciones(Collection<Adopcion> adopciones) {
		this.adopciones = adopciones;
	}
	public String getDireccion() {
		return this.direccion;
	}
/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("apellidos", this.getApellidos())
				.append("nombre", this.getNombre()).append("direccion", this.direccion).append("dni", this.dni)
				.append("",this.getUser().getAuthorities()).toString();
	}

	public Collection<Visita> getVistitas() {
		return vistitas;
	}

	public void setVistitas(Collection<Visita> vistitas) {
		this.vistitas = vistitas;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

}
