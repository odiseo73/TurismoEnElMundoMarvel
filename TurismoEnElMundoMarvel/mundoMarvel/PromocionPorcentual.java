package mundoMarvel;

import java.util.List;

public class PromocionPorcentual extends Promocion{
	private double precio;
	public PromocionPorcentual(List<String[]> promociones) {
		super(promociones);
		
	}

	@Override
	protected double getDescuento() {
		return 0;
		
		
	}


}
