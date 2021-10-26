package mundoMarvel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
	private List<Atraccion> atr;

	public MundoMarvel(String archivo, String archivo2, String archivo3) throws FileNotFoundException {
		setUsuarios(archivo);
		setAtracciones(archivo2);
		setPromociones(archivo3);
		ofrecerProductos();

	}

	public List<Atraccion> getAtracciones() {
		return atraccionesUsadas;
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

	private void setAtracciones(String archivo2) throws FileNotFoundException {
		atracciones = new HashMap<String, Atraccion>();
		atr = new ArrayList<Atraccion>();

		Scanner sc = new Scanner(new File(archivo2));
		while (sc.hasNext()) {
			String[] datos = sc.nextLine().split(",");
			String nombre = datos[0];
			int precio = Integer.parseInt(datos[1]);
			double tiempoEnHoras = Double.parseDouble(datos[2]);
			int cupo = Integer.parseInt(datos[3]);
			Atraccion a = new Atraccion(nombre, precio, tiempoEnHoras, cupo);

			atracciones.put(nombre, a);
			atr.add(a);
		}
		sc.close();
	}

	private void setPromociones(String archivo3) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(archivo3));
		promociones = new ArrayList<Promocion>();

		while (sc.hasNext()) {
			String[] datos = sc.nextLine().split(",");
			String nombre = datos[0];
			String tipo = datos[1];
			int descuento = Integer.parseInt(datos[2]);
			ArrayList<Atraccion> atr = new ArrayList<Atraccion>();
			for (int i = 3; i < datos.length; i++) {
				if (atracciones.containsKey(datos[i])) {
					atr.add(atracciones.get(datos[i]));
				}
			}

			if (tipo.equals("porcentual")) {
				Promocion por = new PromocionPorcentual(nombre, descuento, atr);
				promociones.add(por);
			}

			if (tipo.equals("absoluta")) {
				Promocion abs = new PromocionAbsoluta(nombre, descuento, atr);
				promociones.add(abs);
			}
			if (tipo.equals("axb")) {
				Promocion axb = new PromocionAxB(nombre, atr);
				promociones.add(axb);
			}

		}
		sc.close();
	}

	private void generarItinerario(Usuario usuario, List<Ofertable> productosComprados) throws FileNotFoundException {
		int puntos = 0;
		int tiempo = 0;
		System.out.println("Resumen del itinerario de: " + usuario.getNombre() + "\n");
		System.out.println("Productos Comprados:" + productosComprados.size() + "\n");

		for (Ofertable producto : productosComprados) {

			producto.getTiempoEnHoras();
			tiempo += producto.getTiempoEnHoras();
		}

		System.out.println("Horas necesarias para realizarlos:" + tiempo + "\n");

		for (Ofertable producto : productosComprados) {

			producto.getPrecio();
			puntos += producto.getPrecio();
		}

		System.out.println("Puntos Marvel necesarios:" + puntos + "\n");

		PrintWriter salida = new PrintWriter(new File("Itinerario_Usuario_" + usuario.getNombre() + ".txt"));
		salida.write("Resumen del itinerario de: " + usuario.getNombre() + "\n");
		salida.write("Productos Comprados:" + productosComprados.size() + "\n");
		salida.write("Horas necesarias para realizarlos:" + tiempo + "\n");
		salida.write("Puntos Marvel necesarios:" + puntos + "\n");
		salida.close();

	}

	private int compararPrecioPromocion(Usuario o, Promocion p) {
		return Double.compare(o.getDinero(), p.getPrecioConDescuento());
	}

	private int compararPrecioAtraccion(Usuario o, Atraccion a) {
		return Double.compare(o.getDinero(), a.getPrecio());
	}

	private int compararTiempo(Usuario o, Ofertable p) {
		return Double.compare(o.getTiempoEnHoras(), p.getTiempoEnHoras());
	}

	private boolean verificarCupo(List<Atraccion> atracciones) {
		boolean hayCupo = true;
		for (Atraccion atraccion : atracciones) {
			if (!atraccion.hayCupo()) {
				hayCupo = false;
			}
		}
		return hayCupo;
	}

	private boolean verificarRepetidosPromocion(List<Atraccion> atr1, List<Atraccion> atr2) {
		boolean bandera = false;
		for (Atraccion atraccion : atr2) {
			if (atr1.contains(atraccion)) {
				bandera = true;
			}
		}
		return bandera;
	}

	private boolean verificarRepetidosAtraccion(List<Ofertable> productos, Atraccion atr) {
		boolean bandera = false;
for (Ofertable producto : productos) {
	if(producto.getClass().toString().equals("class mundoMarvel.Promocion")) {
		producto.getAtraccionesIndividuales().contains(atr.getNombre());
		bandera = true;
	}
	if(producto.getClass().toString().equals("class mundoMarvel.Atraccion")) {
		producto.getNombre().equals(atr.getNombre());
		bandera = true;
	}
}
		return bandera;
	}

	private void ofrecerProductos() throws FileNotFoundException {

		for (Usuario usuario : usuarios) {

			List<Ofertable> productosComprados = new LinkedList<Ofertable>();
			
			System.out.println("----------------------------------------------");
			System.out.println("Bienvenido/a a Mundo Marvel");
			System.out.println("Nombre de Visitante: " + usuario.getNombre());
			System.out.println("Le ofrecemos los siguientes productos");
			System.out.println();

			ofrecerPromociones(usuario, productosComprados);
			ofrecerAtracciones(usuario, productosComprados);

			generarItinerario(usuario, productosComprados);
		}
	}

	private List<Atraccion> aniadirPromocionComprada(List<Atraccion> atr, Promocion p) {
		List<Atraccion> lista = p.getAtracciones();
		for (Atraccion atraccion : lista) {
			if (!compararNombresIguales(atraccionesUsadas, atraccion)) {
				atr.add(atraccion);
			}
		}
		return atr;
	}

	private List<Atraccion> aniadirAtraccionesUsadas(List<Atraccion> atr, Atraccion a) {

		if (!compararNombresIguales(atraccionesUsadas, a)) {
			atr.add(a);
		}
		return atr;
	}


	private boolean compararNombresIguales(List<Atraccion> atracciones, Atraccion atr) {
		boolean bandera = false;
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(atr.getNombre())) {
				bandera = true;
			}
		}

		return bandera;
	}

	void ofrecerPromociones(Usuario usuario, List<Ofertable> productosComprados) {

		atraccionesUsadas = new ArrayList<Atraccion>();
		List<Atraccion> atraccionesCompradas = new LinkedList<Atraccion>();

		for (Promocion promocion : promociones) {

			if (compararPrecioPromocion(usuario, promocion) >= 0 && (compararTiempo(usuario, promocion) >= 0)
					&& verificarCupo(atraccionesUsadas)
					&& !verificarRepetidosPromocion(atraccionesCompradas, promocion.getAtracciones())) {

				System.out.println(promocion);
				System.out.println("Acepta la sugerencia?" + " Ingrese SI o NO");
				Scanner sc = new Scanner(System.in);
				String respuesta;
				respuesta = sc.nextLine().toUpperCase();

				while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
					System.out.println("Por favor, ingrese SI o NO.");
					respuesta = sc.nextLine().toUpperCase();
				}
				if (respuesta.equals("SI")) {
					usuario.comprarOfertable(promocion);
					aniadirPromocionComprada(atraccionesUsadas, promocion);
					aniadirPromocionComprada(atraccionesCompradas, promocion);
					restarCupo(atraccionesUsadas, atraccionesCompradas);
					aniadirOfertable(productosComprados, promocion);
				}
				System.out.println("----------------------------------------------");
			}
		}
	}

	void ofrecerAtracciones(Usuario usuario, List<Ofertable> productosComprados) {

		atraccionesUsadas = new ArrayList<Atraccion>();

		List<Atraccion> atraccionesCompradas = new LinkedList<Atraccion>();

		for (Atraccion atraccion : atr) {
			if (compararPrecioAtraccion(usuario, atraccion) >= 0 && compararTiempo(usuario, atraccion) >= 0
					&& verificarCupo(atraccionesUsadas)
					&& !verificarRepetidosAtraccion(productosComprados, atraccion)) {

				System.out.println(atraccion);
				System.out.println("Acepta la sugerencia?" + " Ingrese SI o NO");
				Scanner sc = new Scanner(System.in);
				String respuesta;
				respuesta = sc.nextLine().toUpperCase();

				while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
					System.out.println("Por favor, ingrese SI o NO.");
					respuesta = sc.nextLine().toUpperCase();
				}
				if (respuesta.equals("SI")) {
					usuario.comprarOfertable(atraccion);
					aniadirAtraccionesUsadas(atraccionesUsadas, atraccion);

					aniadirAtraccionesUsadas(atraccionesCompradas, atraccion);

					restarCupo(atraccionesUsadas, atraccionesCompradas);
					aniadirOfertable(productosComprados, atraccion);
				}
				System.out.println("----------------------------------------------");
			}
		}
	}

	void aniadirOfertable(List<Ofertable> productosComprados, Ofertable o) {
		productosComprados.add(o);
	}

	private void restarCupo(List<Atraccion> atraccionesUsadas, List<Atraccion> atraccionesCompradas) {
		for (Atraccion atraccion : atraccionesUsadas) {
			if (compararNombresIguales(atraccionesCompradas, atraccion)) {
				atraccion.restarCupo();
			}
		}

	}
}
