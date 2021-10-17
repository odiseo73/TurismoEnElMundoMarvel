package mundoMarvel;

public class Usuario {

	private String nombre;
	private int tiempoEnHoras;
	private double dinero;
	
	public Usuario(String nombre, int tiempoEnHoras, double dinero) {
		this.nombre = nombre;
		this.tiempoEnHoras = tiempoEnHoras;
		this.dinero = dinero;
	}
	public String getNombre() {
		return nombre;
	}
	public double getTiempoEnHoras() {
		return tiempoEnHoras;
	}
	public double getDinero() {
		return dinero;
	}
	
	protected void comprarAtraccion() {
		//restar tiempo y dinero
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", tiempoEnHoras=" + tiempoEnHoras + ", dinero=" + dinero + "]";
	}
	
	//tieneDineroSuficiente
	//tieneTiempoSuficiente

}
	   
