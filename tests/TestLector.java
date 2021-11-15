package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import lectorDeArchivos.Parque;

public class TestLector {

	@org.junit.Test
		public void test() throws FileNotFoundException {
			Parque parque = new Parque("C:\\Users\\lucia\\Documents\\Programación\\Eclipse\\TurismoEnELmundoMarvel-2daEntrega\\TurismoEnElMundoMarvel\\archivosDeEntrada\\usuarios.txt",
					"C:\\Users\\lucia\\Documents\\Programación\\Eclipse\\TurismoEnELmundoMarvel-2daEntrega\\TurismoEnElMundoMarvel\\archivosDeEntrada\\atracciones.txt"
					,"C:\\Users\\lucia\\Documents\\Programación\\Eclipse\\TurismoEnELmundoMarvel-2daEntrega\\TurismoEnElMundoMarvel\\archivosDeEntrada\\promociones.txt");	
			
		assertNotNull(parque.getUsuarios());
	}

}
