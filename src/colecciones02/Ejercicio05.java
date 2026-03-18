package colecciones02;

import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio05 {

    public static void main(String[] args) {
        
        // Creamos listado de series.
        // Atributo (String): Nombre de la serie. Valor (Double): valoración.
        HashMap<String, Double> listaSeries = new HashMap<>();
        
        // Abrimos  Scanner 
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        
        // Bucle do-while para el menu
        do {
            System.out.println("\n--- GESTOR DE SERIES DE TV ---");
            System.out.println("1. Agregar serie");
            System.out.println("2. Buscar serie");
            System.out.println("3. Eliminar serie");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            
           
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.print("Nombre de la serie: ");
                    String nombreNueva = sc.nextLine();
                    
                    System.out.print("Valoración (ejemplo: 8.5): ");
                    // Leemos el texto con 'sc' y lo convertimos a número decimal (double)
                    double valoracion = Double.parseDouble(sc.nextLine());
                    
                    // .put() guarda la pareja (nombre, nota) en el mapa
                    listaSeries.put(nombreNueva, valoracion);
                    System.out.println("¡Serie guardada!");
                    break;
                    
                case "2":
                    System.out.print("¿Qué serie quieres buscar?: ");
                    String nombreBuscar = sc.nextLine();
                    
                    // Comprobamos si el nombre de la serie existe
                    if (listaSeries.containsKey(nombreBuscar)) {
                        // .get() nos devuelve la nota asociada a ese nombre
                        double nota = listaSeries.get(nombreBuscar);
                        System.out.println("La serie '" + nombreBuscar + "' tiene una valoración de: " + nota);
                    } else {
                        System.out.println("No tengo ninguna serie guardada con ese nombre.");
                    }
                    break;
                    
                case "3":
                    System.out.print("¿Qué serie quieres eliminar?: ");
                    String nombreBorrar = sc.nextLine();
                    
                    // Comprobamos primero si existe para poder avisar al usuario
                    if (listaSeries.containsKey(nombreBorrar)) {
                        listaSeries.remove(nombreBorrar); // .remove() la borra usando el nombre
                        System.out.println("¡Serie eliminada correctamente!");
                    } else {
                        System.out.println("No se puede eliminar porque esa serie no existe.");
                    }
                    break;
                    
                case "4":
                    System.out.println("Saliendo del gestor de series ");
                    break;
                    
                default:
                    System.out.println("Opción incorrecta. Por favor, elige del 1 al 4.");
            }
            
        } while (!opcion.equals("4"));
        
        // Cerramos Scanner 
        sc.close();
    }
}