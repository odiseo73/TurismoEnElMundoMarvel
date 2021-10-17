package mundoMarvel;

public class Atraccion {

	private String nombre;
	private int precio;
	private double duracionEnHoras;
	private int cupoInicial;
	private int cupoDisponible;

	public Atraccion(String nombre, int precio, double duracionEnHoras, int cupoInicial) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.duracionEnHoras = duracionEnHoras;
		this.cupoInicial = cupoInicial;
	}
	

	public String getNombre() {
		return nombre;
	}

	public int getCosto() {
		return precio;
	}

	public double getDuracionEnHoras() {
		return duracionEnHoras;
	}

	public int getCupoInicial() {
		return cupoInicial;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}
	

	protected void restarCupo() {
		
	}

protected void setPrecioConPromocion(int descuento) {
	this.precio -= descuento;
}
}
