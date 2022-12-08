package edu.ucla.lab1.vigilancia.model;

public class DetalleServicio extends Model {
	
	private String resumen;
	private Vigilante vigilante = new Vigilante();
	private Servicio servicio = new Servicio();
	private Turno turno = new Turno();
	
	
	public DetalleServicio() {
	}
	
	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	public Vigilante getVigilante() {
		return vigilante;
	}

	public void setVigilante(Vigilante vigilante) {
		this.vigilante = vigilante;
	}
	
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public Object[] toRowTable() {
		return new Object [] {
			this.getResumen(),
			this.getServicio().getId(),
			this.getVigilante().getId(),
			this.getTurno().getId()
		};
	}
	
}

