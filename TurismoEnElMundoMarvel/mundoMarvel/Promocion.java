package mundoMarvel;

import java.util.List;

public abstract class Promocion implements Ofertable {

	private String nombre;
	private List <Atraccion> atracciones;

	public Promocion(String nombre, List<Atraccion> atracciones) {
		super();
		this.nombre = nombre;
		this.atracciones = atracciones;
	}
	protected abstract double getDescuento();

	public String getNombre() {
		return nombre;
	}
	public int getPrecio() {
		return 0;
	}
	public List<Atraccion> getAtracciones(){
		return atracciones;
	}
    public void restarCupo() {
    	for (Atraccion atracciones : atracciones) {
    		atracciones.restarCupo();
    	}
    }

	
	@Override
	public double getTiempoRequerido() {
		return 0;
	}

	@Override
	public String toString() {
		return "Promocion [nombre=" + nombre + ", atracciones=" + atracciones + "]";
	}
}
