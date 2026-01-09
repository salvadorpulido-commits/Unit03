package arraysExercises;

import java.util.Scanner;

public class Arrays02TablaNumDecim {

	/*Diseñar un programa que solicite al usuario 5 números decimales 
	 * y los almacene en una tabla. Utiliza el mismo bucle tanto para 
	 * solicitar los 5 números como para almacenarlos en la tabla. 
	 * A continuación, en un bucle distinto, mostrar por consola los 
	 * números en el mismo orden en el que se han introducido.
	 */

public static void main(String[] args) {
		


		
     Scanner sc = new Scanner(System.in);
     double[] numeros = new double[5];
     int i;

     // Pedir y guardar los números
	    for (i = 0; i < 5; i++) {
		System.out.print("Introduce un número decimal: ");
		numeros[i] = sc.nextDouble();
		
		}

	 // Mostrar los números
		System.out.println("\nLos números introducidos son:");
		for (i = 0; i < 5; i++) {
		System.out.println(numeros[i]);
		
		}

	    sc.close();
	    
	    }
 }

