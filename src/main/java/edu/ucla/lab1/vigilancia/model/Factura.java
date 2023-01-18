package edu.ucla.lab1.vigilancia.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura extends Model {
	private Integer id;
	private LocalDate fechaPago;
	private String desc;
	private Double iva;
	private Integer status;
	private Double subtotal;
	private Double montoTotal;
	private Servicio servicio = new Servicio();
	private ArrayList<ServicioExtra> servExtra = new ArrayList<ServicioExtra>();
	private Cliente cliente = new Cliente();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(LocalDate fecha) {
		this.fechaPago = fecha;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public ServicioExtra getServicioExtra(int i) {
		return servExtra.get(i);
	}
	public void setServicioExtra(ServicioExtra servExtra) {
		this.servExtra.add(servExtra);
	}
	public void setAllServicioExtra(ArrayList<ServicioExtra> servExtra) {
		this.servExtra.addAll(servExtra);
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Double getMontoServicio() {
		Double costo_servicio = servicio.getCosto();
		int cantTurnos = servicio.cantidadTurnos();
		
		return costo_servicio * cantTurnos;
	}
	
	public Double getMontoAlquiler() {
		Double costo_alquiler = 0.0;
		int cantTurnos = servicio.cantidadTurnos();
		
		for (ServicioExtra se : servExtra) {
			if(se != null) {
				costo_alquiler += se.getTipoAlquiler().getCostoUso() + se.getTipoAlquiler().getCostoMant();
			}
		}
		
		return costo_alquiler * cantTurnos;
	}
	
	public void calcMontos() {
		subtotal = getMontoServicio() + getMontoAlquiler();
	}
	
	public void calcMontoTotal() {
		Double aum = subtotal * (iva/100);
		montoTotal = subtotal + aum;
	}
	
    public String toString() {
    	return id.toString();
    };

    public Object[] toRowTable() {
    	return new Object[]{
    	    	this.getId(),
    	        this.getFechaPago(),
    	        this.getServicio().getId(),
    	        this.getIva(),
    	        this.getSubtotal(),
    	        this.getMontoTotal()
            };
    };
	
}
