package mundoMarvel;

import java.util.Comparator;

public class ComparadorDeAtracciones implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
	
		return	Integer.compare(0,  o1.getNombre().compareTo(o2.getNombre()));
	}

}
