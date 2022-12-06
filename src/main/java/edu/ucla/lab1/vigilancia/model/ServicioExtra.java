package edu.ucla.lab1.vigilancia.model;

import java.util.Objects;

public class ServicioExtra extends Model {
	private Integer id;
	private Integer cant;
	private Servicio servicio = new Servicio();
	private TipoAlquiler tipoAlquiler = new TipoAlquiler();

	public ServicioExtra() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}
	
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public TipoAlquiler getTipoAlquiler() {
		return tipoAlquiler;
	}

	public void setTipoAlquiler(TipoAlquiler tipoAlquiler) {
		this.tipoAlquiler = tipoAlquiler;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ServicioExtra))
			return false;
		ServicioExtra entity = (ServicioExtra) obj;
		return Objects.equals(id, entity.getId());
	}

	@Override
	public String toString() {
		return id + " [" + getCant() + "]";
	}

	@Override
	public Object[] toRowTable() {
		return new Object[] {  
				this.getServicio().getId(),
				this.getTipoAlquiler().getId(),
				this.getTipoAlquiler().getNombre(),
				this.getCant(), 
				};
	}

}
