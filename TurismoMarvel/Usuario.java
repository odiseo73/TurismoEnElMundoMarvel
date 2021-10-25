package TurismoMarvel;

public class Usuario {

	private String nombre;
	private double tiempoDisponible;
	private int dinero;
	
	public Usuario(String nombre, int dinero, double tiempoEnHoras) {
		this.nombre = nombre;
		this.tiempoDisponible = tiempoEnHoras;
		this.dinero = dinero;
	}
	public String getNombre() {
		return nombre;
	}
	public double getTiempoEnHoras() {
		return tiempoDisponible;
	}
	public double getDinero() {
		return dinero;
	}
	
	protected void comprarOfertable(Ofertable o) {
			this.dinero -= o.getPrecio();
			this.tiempoDisponible -= o.getTiempoEnHoras();
	}
	@Override
	public String toString() {
		return "Usuario [Nombre=" + nombre + ", TiempoEnHoras=" + tiempoDisponible + ", Dinero=" + dinero + "]" + "\n";
	}
	
	//tieneDineroSuficiente
	//tieneTiempoSuficiente

}
	   
