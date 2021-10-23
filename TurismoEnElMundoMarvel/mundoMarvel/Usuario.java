package mundoMarvel;

public class Usuario {

	private String nombre;
	private int tiempoDisponible;
	private double dinero;
	
	public Usuario(String nombre, int tiempoEnHoras, double dinero) {
		this.nombre = nombre;
		this.tiempoDisponible = tiempoEnHoras;
		this.dinero = dinero;
	}
	public String getNombre() {
		return nombre;
	}
	public int getTiempoEnHoras() {
		return tiempoDisponible;
	}
	public double getDinero() {
		return dinero;
	}
	
	protected void comprarOfertable(Ofertable o) {
			this.dinero -= o.getPrecio();
			this.tiempoDisponible -= o.getTiempoRequerido();
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", tiempoEnHoras=" + tiempoDisponible + ", dinero=" + dinero + "]";
	}
	
	//tieneDineroSuficiente
	//tieneTiempoSuficiente

}
	   
