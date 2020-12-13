package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="vacunaciones")
public class Vacunacion extends BaseEntity {
	
	@NotBlank
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="comentario")
	private String comentario;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha")
	private LocalDate fecha;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "animal_id")
	private Animal animal;

}
