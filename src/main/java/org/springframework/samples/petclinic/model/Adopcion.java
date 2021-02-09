package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="adopciones")
public class Adopcion extends BaseEntity{
	
	//Atributos----------------------------------------------------------------------
	@NotNull
	private Integer unidadFamiliar;
	
	@NotNull
	private Integer mayoresDeEdad;
	
	@NotNull
	private boolean leidoRequisitos;
	
	@NotNull
	private boolean permisoComunidadVecinos;
	//private Vivienda vivienda;
	
	@NotNull
	private boolean otrosAnimales;
	
	@NotBlank
	private String motivo;
	
	private Estado estado;
	
	private String motivoDecision;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaDecision;

	//Relación Adopción-DueñoAdoptivo
	
	@ManyToOne
	@JoinColumn(name="dueno_id")
	private DuenoAdoptivo dueno;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="animal_id")
	private Animal animal;
	
	//Métodos Getters y Setters---------------------------------------------------------------------
	
	public DuenoAdoptivo getDueno() {
		return dueno;
	}
	public void setDueno(DuenoAdoptivo dueno) {
		this.dueno=dueno;
	}
	public Integer getUnidadFamiliar() {
		return unidadFamiliar;
	}

	public void setUnidadFamiliar(Integer unidadFamiliar) {
		this.unidadFamiliar = unidadFamiliar;
	}

	public Integer getMayoresDeEdad() {
		return mayoresDeEdad;
	}

	public void setMayoresDeEdad(Integer mayoresDeEdad) {
		this.mayoresDeEdad = mayoresDeEdad;
	}

	public boolean getLeidoRequisitos() {
		return leidoRequisitos;
	}

	public void setLeidoRequisitos(boolean leidoRequisitos) {
		this.leidoRequisitos = leidoRequisitos;
	}

	public boolean getPermisoComunidadVecinos() {
		return permisoComunidadVecinos;
	}

	public void setPermisoComunidadVecinos(boolean permisoComunidadVecinos) {
		this.permisoComunidadVecinos = permisoComunidadVecinos;
	}

	public boolean getOtrosAnimales() {
		return otrosAnimales;
	}

	public void setOtrosAnimales(boolean otrosAnimales) {
		this.otrosAnimales = otrosAnimales;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getMotivoDecision() {
		return motivoDecision;
	}

	public void setMotivoDecision(String motivoDecision) {
		this.motivoDecision = motivoDecision;
	}

	public LocalDate getFechaDecision() {
		return fechaDecision;
	}

	public void setFechaDecision(LocalDate fechaDecision) {
		this.fechaDecision = fechaDecision;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	

}
