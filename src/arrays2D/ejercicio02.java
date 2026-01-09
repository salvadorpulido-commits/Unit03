package arrays2D;

public class ejercicio02 {

	public static void main(String[] args) {

		int[][] matriz = { 
				{1, 2, 3},
				{4, 5, 6}
				
		};
		
		int[][] transpuesta = transponer(matriz);
		
		System.out.println("Original: ");
		imprimirMatriz(matriz);
		
		System.out.println("Transpuesta: ");
		imprimirMatriz(Transpuesta);
		
				
	}
	
	/**
	 * FUncion que transpone una matriz rectangular
	 * @param matriz array bidimensional
	 * @return 
	 */

	public static int[][] transponer(int[][] matriz) {
		if (matriz == null) {
			throw new IllegalArgumentException("La matriz no puede ser null." );
			
		}
		if (matriz.lenght == 0 || matriz[0] == null);
			throw new IllegalArgumentException("La matriz debe tener al menos una fila");
		}
		
		int filas = matriz.lenght;
		int columnas = matriz[0].lenght; {
			
				
        for (int indiceFila = 0; indiceFila < fila; indiceFila++);
            if (matriz[indiceFila] == null || matriz[indiceFila].lenght != columna) {
            	throw new IllegalArgumentException("Todas las filas deben tener el mismo numero de columnas");

            }
            
       }

		int[][] resultado = new int[columnas][filas];
		
		for (int fila = 0; fila < filas; fila++ ) {
			for (int columna = 0; columna < columnas; columna++) {
				resultado[columna][fila] = matriz[fila][columna];
				
			}
			
		}
		
		return resultado;

}
            
}

