package mundoMarvel;

import java.util.List;

public class PromocionAxB extends Promocion {
public PromocionAxB(String nombre, List<Atraccion> atracciones) {
		super(nombre, atracciones);
		
	}


private double precio;
	

	@Override
	protected double getDescuento() {
		return 0;
		
	}



}
