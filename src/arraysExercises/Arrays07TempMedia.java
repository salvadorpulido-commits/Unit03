package arraysExercises;

import java.util.Scanner;

public class Arrays07TempMedia {
	
	/*Realiza un programa que pida la temperatura media que ha hecho en cada mes 
	 * de un determinado año y las almacene en una tabla (una posición por mes). 
	 * A continuación, se debe mostrar un diagrama de barras horizontales con esos datos. 
	 * Las barras del diagrama se pueden dibujar a base de asteriscos o cualquier otro 
	 * carácter.
	 */

	public static void main(String[] args) {
		
		Scanner lector = new Scanner(System.in);
		
		String[] meses = {"Enero", "febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		
		double[] temperaturas = new double[meses.length];
		int posicion;
		int posicionAsterisco; 
		int numAsteriscos;
			
		
	    System.out.println("Introduce la temperatura media de cada mes del año ");		
	    System.out.println("---------------------------------------------------");
	    
	    for (posicion = 0; posicion < meses.length ; posicion++ ) {
	    	System.out.println("Temperatura media de "+ meses [posicion] + " : ");
	    	temperaturas[posicion] = lector.nextDouble();
	    	
	    }
	    
	    for (posicion = 0; posicion < meses.length ; posicion++ ) {
	    	
	    	System.out.println(meses[posicion] + " : ");
	    	
	    	//cacular el numero de asteriscos que va a tener la figura
	    	
	    }
	    
	    	numAsteriscos = (int) temperaturas[posicion]; {
	    			
	    	//Dibujamos la barra
	    			
	    	for (posicionAsterisco = 0; posicionAsterisco < numAsteriscos; posicionAsterisco++ ) {	
	    	System.out.print(" * ");
	    	
	    }
	    
	    lector.close();
	}

 }
	
}
	
