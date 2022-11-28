package edu.ucla.lab1.vigilancia.model;

import java.util.Objects;

public class TipoAlquiler extends Model {
	private Integer id;
	private String nombre;
	private Double costoUso;
	private Double costoMant;

	public TipoAlquiler() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCostoUso() {
		return costoUso;
	}

	public void setCostoUso(Double costoUso) {
		this.costoUso = costoUso;
	}

	public Double getCostoMant() {
		return costoMant;
	}

	public void setCostoMant(Double costoMant) {
		this.costoMant = costoMant;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TipoAlquiler))
			return false;
		TipoAlquiler entity = (TipoAlquiler) obj;
		return Objects.equals(id, entity.getId());
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public Object[] toRowTable() {
		return new Object[] { this.getId(), this.getNombre(), this.getCostoUso(), this.getCostoMant() };
	}

}
