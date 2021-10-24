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
	private List<Atraccion> atraccionesOrdenadasPorPrecio;
public String dato1;
	public MundoMarvel(String archivo, String archivo2, String archivo3) throws FileNotFoundException {
		setUsuarios(archivo);
		setAtracciones(archivo2);
		setPromociones(archivo3);
		//ordenarAtraccionesPorPrecio();
		ofrecerProductos();

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

	public List<Promocion> getPromociones() {
		return promociones;
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

		
		while (sc.hasNext()) {
			String[] datos = sc.nextLine().split(",");
			String nombre = datos[0];
			String tipo = datos[1];
			int descuento = Integer.parseInt(datos[2]);
			dato1 = tipo;
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

	private void generarArchivoDeSalida(Usuario usuario) throws FileNotFoundException {

		// se crea un archivo de salida para cada usuario
		PrintWriter salida = new PrintWriter(new File("itinerario_Usuario_" + usuario.getNombre() + ".txt"));
		
		salida.close();

	}
	private void generarItinerario(List<Ofertable> p) throws FileNotFoundException {
		System.out.println("Resumen del itinerario");
		System.out.println("Productos Comprados:" + p );
		System.out.println("Horas necesarias para realizarlos:");
		System.out.println("Puntos Marvel necesarios:");
	}
	public Map<String, Atraccion> getAtracciones() {
		return atracciones;
	}

	private int compararPrecio(Usuario o, Promocion p) {
		return Double.compare(o.getDinero(),p.getPrecioConDescuento());
	}
	
	private int compararPrecio(Usuario o, Atraccion a) {
		return Double.compare(o.getDinero(),a.getPrecio());
	}
	private int compararTiempo(Usuario o, Ofertable p) {
		return Double.compare(o.getTiempoEnHoras(), p.getTiempoEnHoras());
	}

	private boolean verificarCupo(List<Atraccion> atracciones) {
		boolean hayCupo = true;
		for (Atraccion atraccion : atracciones) {
			if(!atraccion.hayCupo()) {
				hayCupo = false;
			}
		}
		return hayCupo;
	}

	private boolean verificarRepetidos(List<Atraccion> atr1, List<Atraccion> atr2) {
		boolean bandera = false;
		for (Atraccion atraccion : atr2) {
		if(atr1.contains(atraccion)) {
			bandera = true;
		}
		}
		return bandera;
	}
	private boolean verificarRepetidos(List<Atraccion> atr1, Atraccion atr2) {
		boolean bandera = true;
		if(atr1.contains(atr2)) {
			bandera = false;
		}
		return bandera;
	}
	
	@SuppressWarnings("unchecked")
	public void ordenarAtraccionesPorPrecio() {
		LinkedList <Atraccion> listaDeAtracciones = (LinkedList<Atraccion>) atracciones.values();
		atraccionesOrdenadasPorPrecio = new LinkedList<Atraccion>();
		atraccionesOrdenadasPorPrecio = (LinkedList<Atraccion>) ((LinkedList<Atraccion>) listaDeAtracciones).clone();
		Collections.sort(atraccionesOrdenadasPorPrecio, new OrdenadorDeAtraccionesPorPrecio());
	
	}
	
	private void ofrecerProductos() throws FileNotFoundException {
		atraccionesUsadas = new ArrayList<Atraccion>();
		
		for (Usuario usuario : usuarios) {
			List<Ofertable> productosComprados = new LinkedList<Ofertable>();
			List<Atraccion> atraccionesCompradas = new LinkedList<Atraccion>();
			System.out.println("Bienvenido/a a Mundo Marvel");
			System.out.println("Nombre de Visitante: " + usuario.getNombre());

			for (Promocion promocion : promociones) {
				
				if (compararPrecio(usuario, promocion) >= 0  && (compararTiempo(usuario, promocion) >= 0)
						&& verificarCupo(atraccionesUsadas) && !verificarRepetidos(atraccionesCompradas, promocion.getAtracciones()))
						 {
										
					// mostrar por pantalla su promocion
					
					ofrecerSugerencias(usuario, promocion, atraccionesCompradas);
				}
				}
/*
			// ordenar el map atracciones y retornar una lista de atracciones. Luego recorrer esa lista 
			for (Atraccion atraccion : atraccionesOrdenadasPorPrecio) {
				if(compararPrecio(usuario,atraccion) >= 0 && compararTiempo(usuario, atraccion) >= 0 && verificarCupo(atraccionesUsadas)
						&& !verificarRepetidos(atraccionesCompradas, atraccion)){
					//mostrar por pantalla su atraccion
					ofrecerSugerencias(usuario,atraccion,atraccionesCompradas);
				}
					
			}
	*/
			//generarItinerario(usuario);
		}
		// Misma logica para las atracciones que se ofertan individualmente

	}

	private List<Atraccion> añadirPromocionComprada(List <Atraccion> atr, Promocion p) {
		List<Atraccion> lista = p.getAtracciones();
		for (Atraccion atraccion : lista) {
			if (!compararNombresIguales(atraccionesUsadas,atraccion)) {
				atr.add(atraccion);
			}
		}
		return atr;
	}
	private List<Atraccion> añadirAtraccionesUsadas(List <Atraccion> atr, Atraccion a) {
		
			if (!compararNombresIguales(atraccionesUsadas,a)) {
				atr.add(a);
			}
		return atr;
	}

	private boolean compararNombresIguales(List<Atraccion> atracciones, Atraccion atr) {
		boolean bandera = false;
		for (Atraccion atraccion : atracciones) {
			if(atraccion.getNombre().equals(atr.getNombre())) {
				bandera = true;
			}
			else {
				bandera = false;
			}
			}
		
		return bandera;
	}

	void ofrecerSugerencias(Usuario usuario, Promocion promocion, List<Atraccion> atraccionesCompradas) {
		//System.out.println(promocion);
		System.out.println("Acepta la sugerencia?" + " Ingrese s o n");
		Integer respuesta;
		Scanner sc = new Scanner(System.in);
		respuesta = Integer.parseInt(sc.nextLine());
		while (respuesta == 1) {
			
			//respuesta = sc.nextLine();
			//if (respuesta.equals("S")) {
				// si acepta lo compra sino pasa al siguiente
				usuario.comprarOfertable(promocion);
				añadirPromocionComprada(atraccionesUsadas,promocion);
				
				añadirPromocionComprada(atraccionesCompradas, promocion);
				//restar el cupo
				restarCupo(atraccionesUsadas,atraccionesCompradas);
		//}
				respuesta = Integer.parseInt(sc.nextLine());
		}
		sc.close();
	}
	void ofrecerSugerencias(Usuario usuario, Atraccion atraccion, List<Atraccion> atraccionesCompradas) {
		atraccion.toString();
		System.out.println("Acepta la sugerencia?" + " Ingrese S o N");
		Scanner sc = new Scanner(System.in);
		String respuesta = "";

		while (respuesta != "S" && respuesta != "N") {
			respuesta = sc.nextLine();
			if (respuesta == "S") {
				// si acepta lo compra sino pasa al siguiente
				usuario.comprarOfertable(atraccion);
				añadirAtraccionesUsadas(atraccionesUsadas,atraccion);
				
				añadirAtraccionesUsadas(atraccionesCompradas, atraccion);
				//restar el cupo
				restarCupo(atraccionesUsadas,atraccionesCompradas);
			}

		}
		sc.close();
	}
	private void restarCupo(List<Atraccion> atraccionesUsadas, List<Atraccion> atraccionesCompradas) {
		for (Atraccion atraccion : atraccionesUsadas) {
			if(compararNombresIguales(atraccionesCompradas, atraccion)) {
				atraccion.restarCupo();
			}
		}
		
	}
}
