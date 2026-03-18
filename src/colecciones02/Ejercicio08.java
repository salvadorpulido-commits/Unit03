package colecciones02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio08 {

    public static void main(String[] args) {
        
        // 1. Creamos nuestro mapa de categorías y tareas
        // Clave (String): Categoría. Valor (ArrayList<String>): Lista de tareas
        HashMap<String, ArrayList<String>> gestorTareas = new HashMap<>();
        
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        
        do {
            System.out.println("\n--- GESTOR DE TAREAS ---");
            System.out.println("1. Añadir una nueva tarea");
            System.out.println("2. Eliminar una tarea");
            System.out.println("3. Listar tareas por categoría");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.print("¿En qué categoría quieres añadir la tarea? (Ej: Trabajo, Personal): ");
                    // Lo pasamos a mayúsculas para evitar que "trabajo" y "Trabajo" se guarden separados
                    String categoriaNueva = sc.nextLine().toUpperCase();
                    
                    // Si la categoría no existe, la creamos con una lista vacía
                    if (!gestorTareas.containsKey(categoriaNueva)) {
                        gestorTareas.put(categoriaNueva, new ArrayList<>());
                    }
                    
                    System.out.print("Escribe la descripción de la tarea: ");
                    String nuevaTarea = sc.nextLine();
                    
                    // Cogemos la lista de esa categoría y le añadimos la tarea
                    gestorTareas.get(categoriaNueva).add(nuevaTarea);
                    System.out.println("¡Tarea añadida a la categoría " + categoriaNueva + "!");
                    break;
                    
                case "2":
                    System.out.print("¿De qué categoría es la tarea que quieres borrar?: ");
                    String categoriaBorrar = sc.nextLine().toUpperCase();
                    
                    // Comprobamos si la categoría existe
                    if (gestorTareas.containsKey(categoriaBorrar)) {
                        System.out.print("Escribe el nombre exacto de la tarea a borrar: ");
                        String tareaABorrar = sc.nextLine();
                        
                        // Intentamos borrar la tarea de esa categoría
                        if (gestorTareas.get(categoriaBorrar).remove(tareaABorrar)) {
                            System.out.println("¡Tarea eliminada correctamente!");
                        } else {
                            System.out.println("Esa tarea no existe en la categoría " + categoriaBorrar);
                        }
                    } else {
                        System.out.println("No existe ninguna categoría con ese nombre.");
                    }
                    break;
                    
                case "3":
                    System.out.println("\n--- TAREAS PENDIENTES ---");
                    
                    // Si el mapa está vacío, avisamos
                    if (gestorTareas.isEmpty()) {
                        System.out.println("No hay ninguna tarea registrada.");
                    } else {
                        // Recorremos todas las categorías que hemos guardado
                        for (String categoria : gestorTareas.keySet()) {
                            System.out.println("Categoría: " + categoria);
                            
                            // Sacamos la lista de tareas de esa categoría
                            ArrayList<String> listaTareas = gestorTareas.get(categoria);
                            
                            // Si la categoría se quedó sin tareas al borrar, lo indicamos
                            if (listaTareas.isEmpty()) {
                                System.out.println("  - (No hay tareas pendientes aquí)");
                            } else {
                                // Imprimimos cada tarea con un guion delante para que quede bonito
                                for (String tarea : listaTareas) {
                                    System.out.println("  - " + tarea);
                                }
                            }
                        }
                    }
                    break;
                    
                case "4":
                    System.out.println("Cerrando el gestor de tareas");
                    break;
                    
                default:
                    System.out.println("Opción incorrecta. Por favor, elige del 1 al 4.");
            }
            
        } while (!opcion.equals("4"));
        
        sc.close();
    }
}