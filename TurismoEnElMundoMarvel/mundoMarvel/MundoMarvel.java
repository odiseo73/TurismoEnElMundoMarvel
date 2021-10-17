package mundoMarvel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MundoMarvel {

	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<String[]> promociones;

	public MundoMarvel(String archivo, String archivo2, String archivo3) throws FileNotFoundException {
		setUsuarios(archivo);
		setAtracciones(archivo2);
		setPromociones(archivo3);
	}


	private void generarItinerario() {

	}

	private void ofrecerSugerencias() {
		Iterator<Usuario> itr = usuarios.iterator();
		while (itr.hasNext()) {
			
		}
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

	private void setAtracciones(String archivo2) throws FileNotFoundException {
		atracciones = new ArrayList<Atraccion>();
		Scanner sc = new Scanner(new File(archivo2));
		while (sc.hasNext()) {
			String[] datos = sc.nextLine().split(",");
			String nombre = datos[0];
			int precio = Integer.parseInt(datos[1]);
			double tiempoEnHoras = Double.parseDouble(datos[2]);
			int cupo = Integer.parseInt(datos[3]);
			Atraccion a = new Atraccion(nombre, precio, tiempoEnHoras, cupo);
			atracciones.add(a);
		}
		sc.close();
	}

	private void setPromociones(String archivo3) throws FileNotFoundException {
		// Las promociones tienen su nombre en el primer lugar y despues los nombres de
		// las demás atracciones
		promociones = new ArrayList<String[]>();
		Scanner sc = new Scanner(new File(archivo3));
		while (sc.hasNext()) {
			String[] datos = sc.nextLine().split(",");
			promociones.add(datos);
		}
		sc.close();
	}

}
