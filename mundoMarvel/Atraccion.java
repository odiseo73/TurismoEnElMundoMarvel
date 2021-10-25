package mundoMarvel;

import java.util.Objects;

public class Atraccion implements Ofertable {

	private String nombre;
	private int precio;
	private double duracionEnHoras;
	private final int CUPO_INICIAL;
	private int cupoDisponible;

	public Atraccion(String nombre, int precio, double duracionEnHoras, int cupo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.duracionEnHoras = duracionEnHoras;
		this.CUPO_INICIAL = cupo;
		this.cupoDisponible = cupo;
	}

	public String getNombre() {
		return nombre;
	}
@Override
	public double getTiempoEnHoras() {
		return duracionEnHoras;
	}

	public int getCupoInicial() {
		return CUPO_INICIAL;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public void restarCupo() {
		this.cupoDisponible -= 1;
	}

	public boolean hayCupo() {
		return this.cupoDisponible > 0;
	}

	@Override
	public String toString() {
		return "Atraccion [nombre:" + nombre + "]" + "\n" + "puntos Marvel: "  + precio + "\n" + "Duracion:"
				+ duracionEnHoras + " horas" + "\n";
	}

	@Override
	public int getPrecio() {

		return precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CUPO_INICIAL, cupoDisponible, duracionEnHoras, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return CUPO_INICIAL == other.CUPO_INICIAL && cupoDisponible == other.cupoDisponible
				&& Double.doubleToLongBits(duracionEnHoras) == Double.doubleToLongBits(other.duracionEnHoras)
				&& Objects.equals(nombre, other.nombre) && precio == other.precio;
	}

}
