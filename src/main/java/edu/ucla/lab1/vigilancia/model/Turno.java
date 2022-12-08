package edu.ucla.lab1.vigilancia.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Turno extends Model {
	
	private Integer id;
	private LocalDate fec_in;
	private LocalTime hor_in;
	private LocalDate fec_fin;
	private LocalTime hor_fin;
	private Boolean falta;
	private String just;
	private Vigilante vigilante = new Vigilante();
	private Servicio servicio = new Servicio();
	
	public Turno(){
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFec_in() {
		return fec_in;
	}
	public void setFec_in(LocalDate fec_in) {
		this.fec_in = fec_in;
	}
	public LocalTime getHor_in() {
		return hor_in;
	}
	public void setHor_in(LocalTime hor_in) {
		this.hor_in = hor_in;
	}
	public LocalDate getFec_fin() {
		return fec_fin;
	}
	public void setFec_fin(LocalDate fec_fin) {
		this.fec_fin = fec_fin;
	}
	public LocalTime getHor_fin() {
		return hor_fin;
	}
	public void setHor_fin(LocalTime hor_fin) {
		this.hor_fin = hor_fin;
	}
	public Boolean getFalta() {
		return falta;
	}
	public void setFalta(Boolean falta) {
		this.falta = falta;
	}
	public String getJust() {
		return just;
	}
	public void setJust(String just) {
		this.just = just;
	}
	public Vigilante getVigilante() {
		return vigilante;
	}
	public void setVigilante(Vigilante vigilante) {
		this.vigilante = vigilante;
	}
	
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
	@Override
	public Object[] toRowTable() {
		return new Object [] {
				this.getId(),
				this.getFec_in(),
				this.getFec_fin(),
				this.getFalta(),
				this.getJust(),
				this.getVigilante().getId(),
				this.getServicio().getId()
			};
	}
	
}