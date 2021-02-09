package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;

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
public class Comentario extends BaseEntity {

	@NotEmpty
	private String comentario;

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

}
