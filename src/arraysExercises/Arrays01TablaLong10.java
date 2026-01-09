package arraysExercises;


public class Arrays01TablaLong10 {
	
	/*Crea una tabla de enteros de longitud 10 que se inicializará 
	 * con números aleatorios comprendidos entre 1 y 100. 
	 */
	
	 public static void main(String[] args) {
	
	 int[] numeros = new int[10];
     int i;

     for (i = 0; i < 10; i++) {
         numeros[i] = (int) (Math.random() * 100) + 1;
     }

     for (i = 0; i < 10; i++) {
         System.out.println(numeros[i]);
     }
 }
}
