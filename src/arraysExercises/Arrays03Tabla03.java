package arraysExercises;

import java.util.Scanner;

public class Arrays03Tabla03 {

	/*
	 * Escribe un programa que lea 10 números por teclado, los almacene en una
	 * tabla, y que luego los muestre en orden inverso, es decir, el primero que se
	 * introduce es el último en mostrarse.
	 */
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			int[] numeros = new int[10];
			int i;

			// Pedir y guardar los números
			for (i = 0; i < 10; i++) {
				System.out.print("Introduce un número entero: ");
				numeros[i] = sc.nextInt();

			}

			// Mostrar los números en orden inverso
			System.out.println("\nLos números introducidos en orden inverso son:");
			for (i = 9; i >= 0; i--) {
				System.out.println(numeros[i]);

			}

			sc.close();
		}

	}

}
