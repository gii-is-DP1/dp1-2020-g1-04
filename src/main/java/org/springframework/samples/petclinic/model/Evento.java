package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "eventos")
@Data
public class Evento extends BaseEntity {
	
	@NotBlank
	@Column(name = "titulo")
	private String titulo;
	
	@NotBlank
	@Column(name = "direccion")
	private String direccion;
	
	@Future
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha")
	private LocalDate fecha;
	
	@NotNull
	@Column(name = "aforo")
	private Integer aforo;
	
	@NotBlank
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "director_id")
	private Director director;
	
	@JoinTable(
	        name = "rel_eventos_cuidadores",
	        joinColumns = @JoinColumn(name = "evento_id", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="cuidador_id", nullable = false)
	    )
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Cuidador> cuidadores;
	
	@JoinTable(
	        name = "rel_eventos_duenos",
	        joinColumns = @JoinColumn(name = "evento_id", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="dueno_id", nullable = false)
	    )
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<DuenoAdoptivo> duenos;
		

}
