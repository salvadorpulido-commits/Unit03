package ArraysBidimens;

import java.util.Scanner;

/*Necesitamos crear un programa para almacenar las notas de 4 alumnos 
 * (llamados “Alumno 1”, “Alumno 2”, etc.) y 5 asignaturas. 
 * El usuario introducirá las notas por teclado y luego el programa mostrará la tabla con las notas. 
 * A continuación, mostrará la nota mínima, máxima y media de cada alumno.
 */


public class ArraysBid02 {

	private static final int NUM_ALUMNOS = 4;
	private static final int NUM_ASIGNATURAS = 5;
	
	public static void main(String[] args) {
		
		double [][] notas = new double [NUM_ALUMNOS][NUM_ASIGNATURAS];
		
		Scanner teclado = new Scanner(System.in);
		
		rellenarNotas(teclado, notas);
		
	    System.out.println(Array.toString(notas));
			
		mostrarTablaNotas(notas);
		
		mostrarResumenPorAlumno(notas);
		
	
	}
	
	/**Rellena la tabla de notas con los alumnos en filas y 
	 * asignaturas en columnas
	 */
	
	private static void rellenarNotas(Scanner teclado, double [][] notas ) {

		}
		
		int indiceAlumno;
		for (indiceAlumno=0; indiceAlumno < NUM_ALUMNOS; indiceAlumno ++) {
			System.out.println("Introduce las notas del alumno " + (indiceAlumno +1));
		
			int indiceAsignatura;
			for (indiceAsignatura = 0; indiceAsignatura < NUM_ASIGNATURAS; indiceAsignatura++);
			System.out.println(" Asigantura " + (indiceAsignatura +1) + ":");
			
			notas [indiceAlumno][indiceAsignatura] = teclado.nextDouble();		
		}
	
	teclado.close();
	
	}

}





