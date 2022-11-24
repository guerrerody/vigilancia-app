package edu.ucla.lab1.vigilancia.model;

import java.time.LocalDate;
import java.util.Objects;

public class Vigilante {
	private Integer id;
	private String cedula;
	private String nombre;
	private String apellido;
	private LocalDate fecNac;
	private String correo;
	private String telf;
	private LocalDate fecIng = LocalDate.now();
	private Integer status = 0;
	
	public Vigilante() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFecNac() {
		return fecNac;
	}

	public void setFecNac(LocalDate fecNac) {
		this.fecNac = fecNac;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public LocalDate getFecIng() {
		return fecIng;
	}

	public void setFecIng(LocalDate fecIng) {
		this.fecIng = fecIng;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getNombreCompleto() {
        StringBuilder fullname = new StringBuilder();
        fullname.append(nombre);
        if (apellido != null) {
        	fullname.append(" ");
        	fullname.append(apellido);
        }
        return fullname.toString();
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(cedula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Vigilante)) return false;
		Vigilante entity = (Vigilante) obj;
        return Objects.equals(cedula, entity.getCedula());
	}

	@Override
	public String toString() {
		return cedula + " [" + getNombreCompleto() + "]";
	}
}
