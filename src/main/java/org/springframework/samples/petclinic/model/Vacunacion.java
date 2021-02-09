package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="vacunaciones")
public class Vacunacion extends BaseEntity {
	
	@NotBlank
	private String nombre;
	
	private String comentario;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	private LocalDate fecha;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "animal_id")
	private Animal animal;

}
