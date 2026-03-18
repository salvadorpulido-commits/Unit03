package colecciones02;

import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class Ejercicio07 {

    public static void main(String[] args) {
        
        // 1. Creamos clasificador.
        // Usamos TreeMap para que las claves (longitudes) se ordenen de menor a mayor.
        // Clave (Integer): Número de letras. Valor (HashSet<String>): Conjunto de palabras sin repetir.
        TreeMap<Integer, HashSet<String>> clasificacion = new TreeMap<>();
        
        Scanner sc = new Scanner(System.in);
        String palabra = "";
        
        System.out.println("\n--- CLASIFICADOR DE PALABRAS ---");
        System.out.println("Escribie palabras para clasificarlas.");
        System.out.println("Escribe 'salir' cuando quieras ver el resultado final.\n");
        
        // 2. Bucle para pedir palabras
        do {
            System.out.print("Introduce una palabra: ");
            palabra = sc.nextLine();
            
            // Si la palabra no es "salir" y tampoco está en blanco...
            if (!palabra.equalsIgnoreCase("salir") && !palabra.isBlank()) {
                
                // Calculamos cuántas letras tiene
                int longitud = palabra.length();
                
                // Si es la PRIMERA VEZ que vemos una palabra con esta longitud...
                if (!clasificacion.containsKey(longitud)) {
                    // Le creamos su propio cajón (un HashSet vacío) y le ponemos la etiqueta del número
                    clasificacion.put(longitud, new HashSet<>());
                }
                
                // Buscamos el cajón de esa longitud y metemos la palabra dentro
                // Si la palabra ya estaba la ignora
                clasificacion.get(longitud).add(palabra);
            }
            
        } while (!palabra.equalsIgnoreCase("salir"));
        
        // 3. Mostramos el resultado 
        System.out.println("\n--- RESULTADO DE LA CLASIFICACIÓN ---");
        
        // Si no se escribe nada, avisamos
        if (clasificacion.isEmpty()) {
            System.out.println("No has introducido ninguna palabra.");
        } else {
            // keySet() nos da una lista con todas las claves (los números de letras)
            for (Integer numeroLetras : clasificacion.keySet()) {
                // Para cada número, sacamos su conjunto de palabras
                HashSet<String> grupoPalabras = clasificacion.get(numeroLetras);
                System.out.println("Palabras de " + numeroLetras + " letras: " + grupoPalabras);
            }
        }
        
        sc.close();
    }
}