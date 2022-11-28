package edu.ucla.lab1.vigilancia.model;

import java.util.Objects;

public class Cliente extends Model {
	private Integer id;
	private String nombre;
	private String localidad;
	private String direccion;
	private String nombreContac;
	private String telfContac;
	private Integer status = 0;
	private TipoCliente tipoCliente = new TipoCliente();

	public Cliente() {

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

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreContac() {
		return nombreContac;
	}

	public void setNombreContac(String nombreContac) {
		this.nombreContac = nombreContac;
	}

	public String getTelfContac() {
		return telfContac;
	}

	public void setTelfContac(String telfContac) {
		this.telfContac = telfContac;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente entity = (Cliente) obj;
		return Objects.equals(id, entity.getId());
	}

	@Override
	public String toString() {
		return nombre + " [" + getNombreContac() + "]";
	}

	@Override
	public Object[] toRowTable() {
		return new Object[] { this.getId(), this.getNombre(), this.getNombreContac(), this.getTelfContac() };
	}

}
