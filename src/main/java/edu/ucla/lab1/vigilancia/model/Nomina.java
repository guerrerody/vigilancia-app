package edu.ucla.lab1.vigilancia.model;

import java.time.LocalDate;

public class Nomina extends Model {

	private Integer id;
	private LocalDate fecha;
	private String desc;
	private Integer diasTrab;
	private Integer horasExtra;
	private Integer diasFalta;
	private Double sueldoBase;
	private Double pagoExtra;
	private Double deduccion;
	private Vigilante vigilante = new Vigilante();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getDiasTrab() {
		return diasTrab;
	}
	public void setDiasTrab(Integer diasTrab) {
		this.diasTrab = diasTrab;
	}
	public Integer getHorasExtra() {
		return horasExtra;
	}
	public void setHorasExtra(Integer horasExtra) {
		this.horasExtra = horasExtra;
	}
	public Integer getDiasFalta() {
		return diasFalta;
	}
	public void setDiasFalta(Integer diasFalta) {
		this.diasFalta = diasFalta;
	}
	public Double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(Double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	public Double getPagoExtra() {
		return pagoExtra;
	}
	public void setPagoExtra(Double pagoExtra) {
		this.pagoExtra = pagoExtra;
	}
	public Double getDeduccion() {
		return deduccion;
	}
	public void setDeduccion(Double deduccion) {
		this.deduccion = deduccion;
	}
	public void setVigilante(Vigilante vigilante) {
		this.vigilante = vigilante;
	}
	public Vigilante getVigilante() {
		return vigilante;
	}
	public String toString() {
		return null;
	};

    public Object[] toRowTable() {
    	return new Object[]{
    		this.getId(),
    		this.getVigilante().toString(),
    	    this.getFecha(),
    	    this.getDiasTrab(),
    	    this.getHorasExtra(),
    	    this.getDiasFalta(),
    	    this.getSueldoBase(),
    	    this.getPagoExtra(),
    	    this.getDeduccion()
            };
    };
	
	public Double calcHorasExtras() {
		Double total = 0.0;
		Double sueldoBasexHora = sueldoBase/7;
		total = sueldoBasexHora * horasExtra;
		
		return total;
	};
	
	public Double calcDiasFalta() {
		Double total = sueldoBase * diasFalta;

		return total;
	}
	
	public void calcPagoExtra() {
		pagoExtra =  calcHorasExtras() - calcDiasFalta();
	}
	
	public Double calcPagoBaseTotal() {
		return sueldoBase * diasTrab;
	}
	
	public void calcDeduccion() {
		calcPagoExtra();
		deduccion  = (calcPagoBaseTotal()) + (pagoExtra);
	}
	
}



