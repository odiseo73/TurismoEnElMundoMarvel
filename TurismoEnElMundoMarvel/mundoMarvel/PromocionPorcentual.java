package mundoMarvel;

import java.util.List;

public class PromocionPorcentual extends Promocion{
	public PromocionPorcentual(String nombre, List<Atraccion> atracciones) {
		super(nombre, atracciones);
	
	}


	private double precio;
	

	@Override
	protected double getDescuento() {
		return 0;
		
		
	}


}
