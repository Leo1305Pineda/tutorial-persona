package com.personal.app.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="personas")
public class Persona {
	

	private Long id;
	private String identidad;
	private String nombres;
	private List<Asistencia> asistencias = new ArrayList<Asistencia>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column()
	public String getIdentidad() {
		return identidad;
	}
	public void setIdentidad(String identidad) {
		this.identidad = identidad;
	}

	@Column()
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/*	
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.grupo", cascade=CascadeType.ALL)
	 * @Fetch(FetchMode.SUBSELECT)
	 * */
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinColumn(name="persona_id")
	public List<Asistencia> getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	@Transient
	public Object getFullNombre() {
		// TODO Auto-generated method stub
		return identidad+" - "+nombres;
	}
	
	
	
}
