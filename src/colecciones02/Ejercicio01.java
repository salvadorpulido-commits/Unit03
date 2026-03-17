package colecciones02;

	
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;

	public class Ejercicio01 {

	    public static void main(String[] args) {
	        
	        // 1. Instanciamos la lista de números enteros
	        List<Integer> listaNumeros = new ArrayList<>();
	        
	        // 2. Llenamos la lista con los números del 1 al 10
	        for (int i = 1; i <= 10; i++) {
	            listaNumeros.add(i);
	        }
	        
	        // 3. Mostramos la lista original por pantalla
	        System.out.println("Lista original: " + listaNumeros);
	        
	        // 4. Mezclamos la lista de forma aleatoria
	        // Ojo: Esto modifica la lista actual directamente
	        Collections.shuffle(listaNumeros);
	        
	        // 5. Mostramos la lista mezclada por pantalla
	        System.out.println("Lista mezclada: " + listaNumeros);
	    }


}
