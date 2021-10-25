package TurismoMarvel;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private int descuentoPorcentual;
	private double precio;

	public PromocionPorcentual(String nombre, int descuento, List<Atraccion> atracciones) {
		super(nombre, atracciones);
		this.descuentoPorcentual = descuento;
	}

	
	@Override
	protected double getPrecioConDescuento() {
		double precio = 0;
		precio = super.getPrecio() * descuentoPorcentual / 100;
		return precio;

	}

}
