
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	@Column(name = "numeroRegistro")
	private String numeroRegistro;

	@Column(name = "adoptado")
	private Boolean adoptado;

	@NotBlank
	@Column(name = "tamanyo")
	private String tamanyo;

	
	@NotBlank
	@Column(name = "sexo")
	private String sexo;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "primeraIncorporacion")
	private LocalDate fechaPrimeraIncorporacion;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "ultimaIncorporacion")
	private LocalDate fechaUltimaIncorporacion;

	@URL
	private String foto;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cuidador_id")
	private Cuidador cuidador;

	@ManyToOne(optional = false)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToOne(optional = false)
	@JoinColumn(name = "centro_id")
	private CentroDeAdopcion centroDeAdopcion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
	private Collection<Adopcion> adopciones;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
	private Collection<Visita> visitas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
	private Collection<Enfermedad> enfermedades;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
	private Collection<Revision> revisiones;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
	private Collection<Vacunacion> vacunaciones;

	public int getEdad() {
		LocalDate now = LocalDate.now();
		Period periodo = Period.between(this.fechaNacimiento, now);
		
		return periodo.getYears();
	}
}
