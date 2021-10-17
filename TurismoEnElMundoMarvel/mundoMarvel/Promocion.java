package mundoMarvel;

import java.util.List;

public abstract class Promocion {

	private String nombre;
	private List<String[]> promociones;

	public Promocion(List<String[]> promociones) {
		super();
		
		this.promociones = promociones;
	}

	public String getNombre() {
		return nombre;
	}

	protected abstract double getDescuento();
	protected double tiempoRequerido() {
		return 0;
	}
}
