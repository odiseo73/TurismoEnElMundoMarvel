package usuario;

import java.util.List;

import productos.Atraccion;
import productos.Ofertable;

public class Usuario {

	private String nombre;
	private double tiempoDisponible;
	private int dinero;
	private List<Atraccion> atraccionesCompradas;
	
	public Usuario(String nombre, int dinero, double tiempoEnHoras) {
		this.nombre = nombre;
		this.tiempoDisponible = tiempoEnHoras;
		this.dinero = dinero;
	}
	public String getNombre() {
		return nombre;
	}
	public double getTiempoEnHoras() {
		return tiempoDisponible;
	}
	public double getDinero() {
		return dinero;
	}
	

	protected void comprarOfertable(Ofertable o) {
			this.dinero -= o.getPrecio();
			this.tiempoDisponible -= o.getTiempoEnHoras();
			
			if (o.esPromocion()) {
				for (Atraccion atraccion : o.getAtracciones()) {
					this.atraccionesCompradas.add(atraccion);
				}
			}
			if (!o.esPromocion()) {
				atraccionesCompradas.add((Atraccion) o);
			}
	}
	

	@Override
	public String toString() {
		return "Usuario [Nombre=" + nombre + ", TiempoEnHoras=" + tiempoDisponible + ", Dinero=" + dinero + "]" + "\n";
	}
	
	//tieneDineroSuficiente
	//tieneTiempoSuficiente

}
	   
