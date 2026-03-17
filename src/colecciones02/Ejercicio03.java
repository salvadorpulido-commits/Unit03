package colecciones02;

import java.util.TreeMap; // Importamos TreeMap en lugar de HashMap

public class Ejercicio03 {

    public static void main(String[] args) {
        
        String texto = "En un agujero en el suelo, vivia un hobbit. No un agujero humedo, sucio, repugnante, con restos de gusanos y olor a fango, ni tampoco un agujero, seco, desnudo y arenoso, sin nada en que sentarse o que comer: era un agujero-hobbit, y eso significa comodidad";
        
        // Lo pasamos todo a minúsculas para no separar la 'A' mayúscula de la 'a' minúscula
        texto = texto.toLowerCase();
        
        // 1. Creamos nuestro diccionario que SE ORDENA SOLO alfabéticamente
        TreeMap<Character, Integer> conteoLetras = new TreeMap<>();
        
        // 2. Recorremos letra por letra todo el texto
        for (char letra : texto.toCharArray()) {
            
            // 3. Solo hacemos caso si es una letra de verdad (ignoramos puntos, comas, espacios...)
            if (Character.isLetter(letra)) {
                
                // Si la letra ya está en nuestro diccionario...
                if (conteoLetras.containsKey(letra)) {
                    // Miramos cuánto valía y le sumamos 1
                    int cantidad = conteoLetras.get(letra);
                    conteoLetras.put(letra, cantidad + 1);
                } 
                // Si es una letra nueva que no habíamos visto aún...
                else {
                    // La anotamos por primera vez con un 1
                    conteoLetras.put(letra, 1);
                }
            }
        }
        
        // 4. Imprimimos el resultado (saldrá ordenado de la 'a' a la 'z')
        System.out.println("Frecuencia de cada letra (orden alfabético):");
        System.out.println(conteoLetras);
    }
}