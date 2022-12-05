package edu.ucla.lab1.vigilancia.model;

import java.time.LocalDate;

public class Servicio extends Model {

	private Integer id;
    private LocalDate fechaIn;
    private LocalDate fechaFin;
    private String descr;
	private Double costo;
	private Integer status = 0;
	private Cliente cliente = new Cliente();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFechaIn() {
		return fechaIn;
	}

	public void setFechaIn(LocalDate fechaIn) {
		this.fechaIn = fechaIn;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toRowTable() {
		return new Object[]{
				this.getId(),
	    	    this.getCliente().getNombre(),
	    	    this.getFechaIn(),
	    	    this.getFechaFin(),
	    	    this.getDescr(),
	    	    this.getCosto()
		};
	}

}
