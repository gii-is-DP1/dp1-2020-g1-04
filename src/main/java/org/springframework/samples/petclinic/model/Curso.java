package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "cursos")
@Data
public class Curso extends BaseEntity {
	
	@NotBlank
	@Column(name = "titulo")
	private String titulo;
	
	@NotBlank
	@Column(name = "animal")
	private String animal;
	
	@ManyToOne
	@JoinColumn(name="cuidador_id")
	private Cuidador cuidador;

}
