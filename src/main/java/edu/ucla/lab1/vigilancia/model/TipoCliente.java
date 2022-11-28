package edu.ucla.lab1.vigilancia.model;

import java.util.Objects;

public class TipoCliente extends Model {
	private Integer id;
	private String nombre;
	
	public TipoCliente() {
		
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof TipoCliente)) return false;
		TipoCliente entity = (TipoCliente) obj;
        return Objects.equals(id, entity.getId());
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public Object[] toRowTable() {
		return new Object[] { 
			this.getId(), 
			this.getNombre()
		};
	}

}
