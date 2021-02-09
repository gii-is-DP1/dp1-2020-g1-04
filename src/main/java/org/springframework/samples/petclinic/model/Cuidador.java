package org.springframework.samples.petclinic.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cuidador extends Person {

	// Atributos-------------------------------------------------------------------------------------------------------
	@NotEmpty
	private String dni;
	// ----------------------------------------------------------------------------------------------------------------

	@ManyToOne(optional = false)
	@JoinColumn(name = "centro_de_adopcion_id")
	private CentroDeAdopcion centroDeAdopcion;

	@ManyToMany(mappedBy = "cuidadores")
	private Set<Evento> eventos;

	// ToString--------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("apellidos", this.getApellidos())
				.append("nombre", this.getNombre()).append("dni", this.dni).append("", this.getUser().getAuthorities())
				.toString();
	}
	// -----------------------------------------------------------------------------------------------------------------

}