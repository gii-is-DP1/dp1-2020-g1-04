package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "animales")
public class Animal extends BaseEntity {

	@NotBlank
	@Column(name = "nombre")
	private String nombre;

	@Embedded
	private Peligrosidad peligrosidad;

	@Embedded
	private GradoDeAtencion atencion;

	@Embedded
	private RequisitosDeAdopcion requisitos;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fechaNacimiento")
	private LocalDate fechaNacimiento;

	@Column(name = "chip")
	private String chip;

	@NotBlank
	@Column(name = "numeroRegistro")
	private String numeroRegistro;

	@Column(name = "adoptado")
	private Boolean adoptado;

	@NotBlank
	@Column(name = "tamanyo")
	private String tamanyo;

	@Column(name = "edad")
	private Integer edad;

	@NotBlank
	@Column(name = "sexo")
	private String sexo;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "primeraIncorporacion")
	private LocalDate fechaPrimeraIncorporacion;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "ultimaIncorporacion")
	private LocalDate fechaUltimaIncorporacion;

	@ManyToOne
	@JoinColumn(name ="cuidador_id")
	private Cuidador cuidador;

	@OneToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;


}