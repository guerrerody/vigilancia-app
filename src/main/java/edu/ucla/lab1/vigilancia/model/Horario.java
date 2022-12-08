package edu.ucla.lab1.vigilancia.model;

import java.time.LocalDate;
import java.util.Objects;

public class Horario extends Model {
	
	private Integer id;
	private Vigilante vigilante = new Vigilante();
	private LocalDate fechaEn;
	private LocalDate fechaSal;
	
	public Horario(){
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFechaEn() {
		return fechaEn;
	}
	public void setFechaEn(LocalDate fechaEn) {
		this.fechaEn = fechaEn;
	}
	public LocalDate getFechaSal() {
		return fechaSal;
	}
	public void setFechaSal(LocalDate fechaSal) {
		this.fechaSal = fechaSal;
	}
	
	public Vigilante getVigilante() {
		return vigilante;
	}
	public void setVigilante(Vigilante vigilante) {
		this.vigilante = vigilante;
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
				this.getFechaEn(),
				this.getFechaSal(),
				this.getVigilante().getId()
			};
	}
	
}