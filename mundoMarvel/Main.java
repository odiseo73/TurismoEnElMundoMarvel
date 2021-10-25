package mundoMarvel;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		MundoMarvel mm = new MundoMarvel("archivosDeEntrada/usuarios.txt","archivosDeEntrada/atracciones.txt","archivosDeEntrada/promociones.txt");
	}

}
