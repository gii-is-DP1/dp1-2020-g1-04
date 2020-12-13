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
@Table(name="enfermedades")
public class Enfermedad extends BaseEntity{

	@NotBlank
	@Column(name="nombre")
	private String nombre;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "comienzo")
	private LocalDate comienzo;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fin")
	private LocalDate fin;
	
	@Column(name= "curado")
	private Boolean curado;
	
	@Column(name="comentario")
	private String comentario;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "animal_id")
	private Animal animal;
}
