package colecciones02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio04 {

    public static void main(String[] args) {
        
        // 1. Creamos array para guardar textos 
        ArrayList<String> listaNombres = new ArrayList<>();
        
        // 2. Abrimos el Scanner para leer desde teclado
        Scanner sc = new Scanner(System.in);
        String opcion = ""; // Aquí guardaremos lo que elija el usuario
        
        // 3. Bucle do-while: "Haz esto MIENTRAS la opción no sea 5"
        do {
            // Mostramos el menú por pantalla
            System.out.println("\n--- MENÚ DE NOMBRES ---");
            System.out.println("1. Añadir nuevo nombre");
            System.out.println("2. Eliminar un nombre específico");
            System.out.println("3. Ordenar la lista alfabéticamente");
            System.out.println("4. Buscar si un nombre está en la lista");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            
            // Leemos la opción elegida
            opcion = sc.nextLine();
            
            // 4. Usamos un switch para decidir qué hacer según la opción
            switch (opcion) {
                case "1":
                    System.out.print("Escribe el nombre que quieres añadir: ");
                    String nuevoNombre = sc.nextLine();
                    listaNombres.add(nuevoNombre); // Lo metemos en la lista
                    System.out.println("¡Nombre añadido!");
                    break;
                    
                case "2":
                    System.out.print("Escribe el nombre que quieres borrar: ");
                    String nombreABorrar = sc.nextLine();
                    // .remove() busca el nombre, lo borra y devuelve true 
                    if (listaNombres.remove(nombreABorrar)) {
                        System.out.println("¡Nombre eliminado correctamente!");
                    } else {
                        System.out.println("Ese nombre no estaba en la lista.");
                    }
                    break;
                    
                case "3":
                    // Usamos la herramienta Collections
                    Collections.sort(listaNombres); 
                    System.out.println("Lista ordenada: " + listaNombres);
                    break;
                    
                case "4":
                    System.out.print("Escribe el nombre que quieres buscar: ");
                    String nombreABuscar = sc.nextLine();
                    
                    if (listaNombres.contains(nombreABuscar)) {
                        System.out.println("Sí, " + nombreABuscar + " está en la lista.");
                    } else {
                        System.out.println("No, ese nombre no está registrado.");
                    }
                    break;
                    
                case "5":
                    System.out.println("Saliendo del programa");
                    break;
                    
                default:
                    // Si el usuario añade valores incorrectos, mensaje de correccion
                	
                    System.out.println("Opción incorrecta. Por favor, elige del 1 al 5.");
            }
            
        } while (!opcion.equals("5")); // Si es 5, el bucle termina
        
        // cerramos scanner
        
        sc.close(); 
    }
}