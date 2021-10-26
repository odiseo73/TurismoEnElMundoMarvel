package mundoMarvel;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		MundoMarvel mm = new MundoMarvel("usuarios.txt","atracciones.txt","promociones.txt");	
		System.out.println(mm.getAtracciones());
		}
}
