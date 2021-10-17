package mundoMarvel;

import java.util.List;

public class PromocionAxB extends Promocion {
private double precio;
	public PromocionAxB(List<String[]> promociones) {
		super(promociones);
		
	}

	@Override
	protected double getDescuento() {
		return 0;
		
	}



}
