package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "comentarios")
public class Comentario extends NamedEntity{
	
	@Column(name= "comentario")
	@NotEmpty
	private String comentario;
	
	@Column(name = "momento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate momento;

	@ManyToOne
	@JoinColumn(name = "visita_id")
	private Visita visita;

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDate getMomento() {
		return this.momento;
	}

	public void setMomento(LocalDate momento) {
		this.momento = momento;
	}

	public Visita getVisita() {
		return this.visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}
}
