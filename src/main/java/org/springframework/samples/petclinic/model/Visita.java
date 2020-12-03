package org.springframework.samples.petclinic.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "visitas")
public class Visita extends NamedEntity{
	
	@Column(name = "momento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate momento;
	
	@NotBlank
	@Column(name ="lugar")
	private String lugar;
	
	@ManyToOne
	@JoinColumn(name = "dueno_id")
	private DuenoAdoptivo dueno;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "cuidador_id")
	private Cuidador cuidador;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "visita", fetch = FetchType.EAGER)
	private Set<Comentario> comentarios;
	
	@ManyToOne(optional=false)
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
	
	protected Set<Comentario> getComentariosInternal() {
		if (this.comentarios == null) {
			this.comentarios = new HashSet<>();
		}
		return this.comentarios;
	}

	protected void setComentariosInternal(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Comentario> getComentarios() {
		List<Comentario> sortedComentarios = new ArrayList<>(getComentariosInternal());
		PropertyComparator.sort(sortedComentarios, new MutableSortDefinition("date", false, false));
		return Collections.unmodifiableList(sortedComentarios);
	}

	public void addComentario(Comentario comentario) {
		getComentariosInternal().add(comentario);
		comentario.setVisita(this);
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
}
