package productos;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private int descuentoPorcentual;
	private double precio;

	public PromocionPorcentual(String nombre, int descuento, List<Atraccion> atracciones) {
		super(nombre, atracciones);
		this.descuentoPorcentual = descuento;
	}

	
	@Override
	public double getPrecioConDescuento() {
		this.precio = super.getPrecio();
		precio -= super.getPrecio() * descuentoPorcentual / 100;
		return precio;

	}


	@Override
	public String getAtraccionesIndividuales() {
		// TODO Auto-generated method stub
		return null;
	}

}
