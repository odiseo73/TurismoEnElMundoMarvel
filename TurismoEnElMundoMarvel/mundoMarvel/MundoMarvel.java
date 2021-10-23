package mundoMarvel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MundoMarvel {

	private List<Usuario> usuarios;
	private Map<String, Atraccion> atracciones;
	private List<Promocion> promociones;
	private List<Atraccion> atraccionesUsadas;
private List<String[]> promos;
	public MundoMarvel(String archivo, String archivo2, String archivo3) throws FileNotFoundException {
		setUsuarios(archivo);
		setAtracciones(archivo2);
		setPromociones(archivo3);
		// ofrecerProductos();

	}

	private void setUsuarios(String archivo) throws FileNotFoundException {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();

			usuarios = new ArrayList<Usuario>();

			while (linea != null) {
				String[] datos = linea.split(",");
				String nombre = datos[0];
				int dinero = Integer.parseInt(datos[1]);
				double tiempo = Double.parseDouble(datos[2]);
				Usuario u = new Usuario(nombre, dinero, tiempo);
				usuarios.add(u);
				linea = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<String[]> getPromociones() {
		return promos;
	}

	private void setAtracciones(String archivo2) throws FileNotFoundException {
		atracciones = new HashMap<String, Atraccion>();

		Scanner sc = new Scanner(new File(archivo2));
		while (sc.hasNext()) {
			String[] datos = sc.nextLine().split(",");
			String nombre = datos[0];
			int precio = Integer.parseInt(datos[1]);
			double tiempoEnHoras = Double.parseDouble(datos[2]);
			int cupo = Integer.parseInt(datos[3]);
			Atraccion a = new Atraccion(nombre, precio, tiempoEnHoras, cupo);

			atracciones.put(nombre, a);
		}
		sc.close();
	}

	private void setPromociones(String archivo3) throws FileNotFoundException {
		// En el archivo de entrada de las promociones,
		// tienen el nombre del paquete en el primer lugar, luego el tipo de promocion
		// y despues los nombres de las demás atracciones

		Scanner sc = new Scanner(new File(archivo3));
		promociones = new ArrayList<Promocion>();
		promos = new ArrayList<String[]>();
		ArrayList<Atraccion> atr = new ArrayList<Atraccion>();
		while (sc.hasNext()) {
			String[] datos = sc.nextLine().split(",");
			String nombre = datos[0];
			String tipo = datos[1];
			int descuento = Integer.parseInt(datos[2]);
			
			for (int i = 3; i < datos.length; i++) {
				if (atracciones.containsKey(datos[i])) {
					atr.add(atracciones.get(datos[i]));
				}
			}
			promos.add(datos);

			if (tipo == "porcentual") {
				Promocion por = new PromocionPorcentual(nombre,descuento,atr);
				promociones.add(por);
			}
			
			if (tipo == "absoluta") {
				Promocion abs = new PromocionAbsoluta(nombre,descuento,atr);
				promociones.add(abs);
			}
			if (tipo == "axb") {
				Promocion axb = new PromocionAxB(nombre, atr);
				promociones.add(axb);
			}

		}
		sc.close();
	}

	private void generarItinerario(Usuario usuario) throws FileNotFoundException {

		// se crea un archivo de salida para cada usuario
		PrintWriter salida = new PrintWriter(new File("itinerario_Usuario_" + usuario.getNombre() + ".txt"));

		salida.close();

	}

	public Map<String, Atraccion> getAtracciones() {
		return atracciones;
	}

	private int compararPrecio(Usuario o, Ofertable p) {
		return Double.compare(o.getDinero(), p.getPrecio());
	}

	private int compararTiempo(Usuario o, Ofertable p) {
		return Double.compare(o.getTiempoEnHoras(), p.getTiempoRequerido());
	}

	private void ofrecerProductos() throws FileNotFoundException {
		for (Usuario usuario : usuarios) {
			System.out.println("Bienvenido/a a Mundo Marvel");
			System.out.println("Nombre de Visitante" + usuario.getNombre());

			for (Promocion promocion : promociones)
				if (compararPrecio(usuario, promocion) >= 0 && (compararTiempo(usuario, promocion) >= 0)) {
					// falta añadir en el if de arriba un comparador de atracciones que ya hayan
					// sido incluidas
					// mostrar por pantalla su promocion
					// aqui hace falta hacer el ToString que muestre en pantalla

					ofrecerSugerencias(usuario, promocion);

				}
			generarItinerario(usuario);
		}
		// Misma logica para las atracciones que se ofertan individualmente

	}

	void ofrecerSugerencias(Usuario usuario, Promocion promocion) {
		promocion.toString();
		System.out.println("Acepta la sugerencia?" + " Ingrese S o N");
		Scanner sc = new Scanner(System.in);
		String respuesta = null;
		atraccionesUsadas = new ArrayList<Atraccion>();
		List<Atraccion> atraccionesDePromocion = new ArrayList<Atraccion>();
		while (respuesta != "S" && respuesta != "N") {
			respuesta = sc.nextLine();
			if (respuesta == "S") {
				// si acepta lo compra sino pasa al siguiente
				usuario.comprarOfertable(promocion);

				for (Atraccion atraccion : atraccionesDePromocion) {

					atraccionesUsadas.add(atraccion);
					// luego las atracciones incluidas en la promocion deben guadarse en otra lista
					// atraccionesUsadas.add((Atraccion) promocion.getAtracciones());
				}
			}

		}
		sc.close();
	}
}
