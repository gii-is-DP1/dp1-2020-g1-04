package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="enfermedades")
public class Enfermedad extends BaseEntity{

	@NotBlank
	private String nombre;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	@NotNull
	private LocalDate comienzo;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	private LocalDate fin;
	
	private Boolean curado;
	
	private String comentario;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "animal_id")
	private Animal animal;
}
