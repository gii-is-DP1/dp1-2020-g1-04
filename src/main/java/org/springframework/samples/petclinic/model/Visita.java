package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visitas")
public class Visita extends BaseEntity {

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate momento;

	@NotBlank
	private String lugar;

	@ManyToOne
	@JoinColumn(name = "dueno_id")
	private DuenoAdoptivo dueno;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cuidador_id")
	private Cuidador cuidador;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "visita", fetch = FetchType.EAGER)
	private Set<Comentario> comentarios;

	@ManyToOne(optional = false)
	@JoinColumn(name = "animal_id")
	private Animal animal;

	public LocalDate getMomento() {
		return momento;
	}

	public void setMomento(LocalDate momento) {
		this.momento = momento;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public DuenoAdoptivo getDueno() {
		return dueno;
	}

	public void setDueno(DuenoAdoptivo dueno) {
		this.dueno = dueno;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

}
