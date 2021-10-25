package TurismoMarvel;

import java.util.Comparator;

public class OrdenadorDeAtraccionesPorPrecio implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
	
		return	Integer.compare(o1.getPrecio(), o2.getPrecio());
		
	}

}
