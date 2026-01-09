package arraysExercises;

import java.util.Random;
import java.util.Scanner;

public class Arrays08EntAleat100 {

	public static void main(String[] args) {
		/*Crea un programa que cree un array de enteros de tamaño 100
		 * y lo rellene con valores enteros aleatorios entre 1 y 10. 
		 * Luego pedirá un valor N y mostrará en qué posiciones del array aparece N. 
		 */

		int[] numeros = new int[100];
		
		Random aleatorio = new Random();
		
		Scanner lector = new Scanner(System.in);
		
		int N;		
			
		int posicion;
		
		for (posicion = 0; posicion < numeros.length; posicion++ ) {
			numeros[posicion] = aleatorio.nextInt(10) +1;
		
					
		}
		
		System.out.println(" Introduce un numero N (entre 1 y 10): ");
		N = lector.nextInt();
		
		for (posicion = 0; posicion < numeros.length; posicion++) {
			
			if (numeros[posicion] == N) {
			 System.out.println("Se ha encontrado el numero " + N + "en la posicion " + posicion );
			 
			}
		}
	}

}
