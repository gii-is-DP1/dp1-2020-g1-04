package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="revisiones")
public class Revision extends BaseEntity{
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "momento")
	private LocalDate momento;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="estado")
	private String estado;

	@ManyToOne(optional=false)
	@JoinColumn(name = "animal_id")
	private Animal animal;
}
