package mundoMarvel;

import java.util.List;

public class PromocionPorcentual extends Promocion{
	 private int descuentoPorcentual; 
	 private double precio;
	public PromocionPorcentual(String nombre, int descuento, List<Atraccion> atracciones) {
		super(nombre, atracciones);
		this.descuentoPorcentual = descuento;
	}


	@Override
	public String toString() {
		return "PromocionPorcentual [descuentoPorcentual=" + descuentoPorcentual + ", precio=" + precio + "]";
	}


	@Override
	protected double getDescuento() {
		return 0;
		
		
	}


}
