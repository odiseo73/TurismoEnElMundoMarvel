package mundoMarvel;

import java.util.List;

public class PromocionAbsoluta extends Promocion {
	private double precio;
	public PromocionAbsoluta(List<String[]> promociones) {
		super(promociones);
		
	}

	@Override
	protected double getDescuento() {
		return 0;
		
		
	}



}
