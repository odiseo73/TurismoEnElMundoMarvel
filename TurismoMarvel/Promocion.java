package TurismoMarvel;

import java.util.List;

public abstract class Promocion implements Ofertable {

	private String nombre;
	private List <Atraccion> atracciones;

	public Promocion(String nombre, List<Atraccion> atracciones) {
		super();
		this.nombre = nombre;
		this.atracciones = atracciones;
	}
	protected abstract double getPrecioConDescuento();

	public String getNombre() {
		return nombre;
	}
	public int getPrecio() {
		int precio = 0;
		for (Atraccion atraccion : atracciones) {
			precio += atraccion.getPrecio();
		}
		return precio;
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
	public double getTiempoEnHoras() {
		double tiempo = 0;
		for (Atraccion atraccion : atracciones) {
			tiempo += atraccion.getTiempoEnHoras();
		}
		return tiempo;
	}
	@Override
	public String toString() {
		return "Promocion: " + "\n" + "[Nombre: " + nombre + "\n"+ "Atracciones incluidas: " 
				+ atracciones + "\n" + "Duracion: " + getTiempoEnHoras() + "\n"
				+ "Precio Original:" + getPrecio() + "\n" + "Precio con descuento:" + getPrecioConDescuento() + "\n";
	}


}
