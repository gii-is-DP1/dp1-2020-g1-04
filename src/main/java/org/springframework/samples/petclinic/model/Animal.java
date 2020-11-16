package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "animales")
public class Animal extends NamedEntity{
	
	@Column(name= "nombre")
	@NotEmpty
	private String nombre;
	

	@NotEmpty
	@Embedded
	private Peligrosidad peligrosidad;
	
	@NotEmpty
	@Embedded
	private GradoDeAtencion atencion;
	
	@NotEmpty
	@Embedded
	private RequisitosDeAdopcion requisitos;
	
	@Column(name ="nacimiento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate nacimiento;
	
	@Column(name ="chip")
	@NotEmpty
	private String chip;
	
	@Column(name ="numeroRegistro")
	@NotEmpty
	private String numeroRegistro;
	
	@Column(name = "adoptado")
	private boolean adoptado;
	
	@Column(name = "tamano")
	private String tamano;
	
	@Column(name = "edad")
	private String edad;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "primera_incorporacion")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaPrimeraIncorporacion;
	
	@Column(name = "ultima_incorporacion")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaUltimaIncorporacion;
	
	@ManyToOne
	@JoinColumn(name ="cuidador_id")
	private Cuidador cuidador;
	
	@ManyToOne
	@JoinColumn(name ="visita_id")
	private Visita visita;
	
	@ManyToOne(targetEntity = Enfermedad.class)
	@JoinColumn(name = "enfermedad_id")
	private List<Enfermedad> enfermedades;
	
	@ManyToOne(targetEntity = Revision.class)
	@JoinColumn(name = "revision_id")
	private List<Revision> revisiones;
	
	// private Categoria categoria; 
	
	@ManyToOne
	@JoinColumn(name="centro_id")
	private CentroDeAdopcion centro;
}
