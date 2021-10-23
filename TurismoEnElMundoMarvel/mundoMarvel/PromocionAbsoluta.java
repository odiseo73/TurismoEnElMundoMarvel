package mundoMarvel;

import java.util.List;

public class PromocionAbsoluta extends Promocion {
	public PromocionAbsoluta(String nombre, List<Atraccion> atracciones) {
		super(nombre, atracciones);
		
	}


	private double precio;
	

	@Override
	protected double getDescuento() {
		return 0;
		
		
	}



}
