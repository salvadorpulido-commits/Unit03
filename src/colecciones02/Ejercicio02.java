package colecciones02;

import java.util.LinkedHashSet;
import java.util.Random;

public class Ejercicio02 {

    public static void main(String[] args) {
        
        // Creamos la colección especial, guarda el orden de llegada y no deja meter  a nadie repetido.
    	
        LinkedHashSet<Integer> numeros = new LinkedHashSet<>();
        Random generador = new Random();
        
        // 2. Mientras no tengamos 10 números guardados, seguimos intentando.
        while (numeros.size() < 10) {
            
            // Generamos un número del 1 al 20
            int aleatorio = generador.nextInt(20) + 1;
            
            // Lo intentamos meter en la colección. 
            // Si ya estaba dentro, la colección lo ignora y el bucle vuelve a dar otra vuelta.
            
            numeros.add(aleatorio);
        }
        
        // 3. Imprimimos el resultado
        System.out.println("Números generados: " + numeros);
    }
}