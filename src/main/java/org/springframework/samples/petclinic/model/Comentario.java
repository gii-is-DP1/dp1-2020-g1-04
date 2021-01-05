package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comentarios")
public class Comentario extends BaseEntity{
	
	@Column(name= "comentario")
	@NotEmpty
	private String comentario;
	
	@Column(name = "momento")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime momento;

	@ManyToOne
	@JoinColumn(name = "visita_id")
	private Visita visita;
	
	@ManyToOne
	@JoinColumn(name = "director_id")
	private Director director;
	
	@ManyToOne
	@JoinColumn(name = "cuidador_id")
	private Cuidador cuidador;
	
	@ManyToOne
	@JoinColumn(name = "dueno_id")
	private DuenoAdoptivo dueno;

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getMomento() {
		return this.momento;
	}

	public void setMomento(LocalDateTime momento) {
		this.momento = momento;
	}

	public Visita getVisita() {
		return this.visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public DuenoAdoptivo getDueno() {
		return dueno;
	}

	public void setDueno(DuenoAdoptivo dueno) {
		this.dueno = dueno;
	}

	
	
}
