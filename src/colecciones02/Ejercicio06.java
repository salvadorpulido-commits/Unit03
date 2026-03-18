package colecciones02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio06 {

    public static void main(String[] args) {
        
        // 1. Estructura Diccionario que guarda Listas.
        // Clave (String): Nombre. Valor (ArrayList<String>): Lista de sus teléfonos.
        HashMap<String, ArrayList<String>> libreta = new HashMap<>();
        
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        
        do {
            System.out.println("\n--- LIBRETA DE DIRECCIONES ---");
            System.out.println("1. Añadir persona");
            System.out.println("2. Añadir teléfono a una persona");
            System.out.println("3. Mostrar teléfonos de una persona");
            System.out.println("4. Eliminar teléfono de una persona");
            System.out.println("5. Eliminar una persona");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.print("Nombre de la nueva persona: ");
                    String nuevaPersona = sc.nextLine();
                    
                    // Comprobamos que no exista 
                    if (!libreta.containsKey(nuevaPersona)) {
                        // La creamos asignándole una lista de teléfonos VACÍA
                        libreta.put(nuevaPersona, new ArrayList<>());
                        System.out.println("¡Persona añadida!");
                    } else {
                        System.out.println("Esa persona ya existe.");
                    }
                    break;
                    
                case "2":
                    System.out.print("¿A quién le quieres añadir un teléfono?: ");
                    String nombreAdd = sc.nextLine();
                    
                    if (libreta.containsKey(nombreAdd)) {
                        System.out.print("Dime el número de teléfono: ");
                        String nuevoTelefono = sc.nextLine();
                        
                        // .get() nos da la lista de esa persona, y con .add() metemos el teléfono
                        libreta.get(nombreAdd).add(nuevoTelefono);
                        System.out.println("¡Teléfono añadido correctamente!");
                    } else {
                        System.out.println("Esa persona no existe. ¡Añádela primero con la opción 1!");
                    }
                    break;
                    
                case "3":
                    System.out.print("¿De quién quieres ver los teléfonos?: ");
                    String nombreVer = sc.nextLine();
                    
                    if (libreta.containsKey(nombreVer)) {
                        // Sacamos su lista de teléfonos y la mostramos
                        ArrayList<String> susTelefonos = libreta.get(nombreVer);
                        
                        // Si la lista está vacía avisamos, si no, la imprimimos
                        if (susTelefonos.isEmpty()) {
                            System.out.println(nombreVer + " todavía no tiene teléfonos guardados.");
                        } else {
                            System.out.println("Teléfonos de " + nombreVer + ": " + susTelefonos);
                        }
                    } else {
                        System.out.println("Esa persona no está en la libreta.");
                    }
                    break;
                    
                case "4":
                    System.out.print("¿De quién quieres borrar un teléfono?: ");
                    String nombreBorrarTel = sc.nextLine();
                    
                    if (libreta.containsKey(nombreBorrarTel)) {
                        System.out.print("Dime el número exacto que quieres borrar: ");
                        String telABorrar = sc.nextLine();
                        
                        // Intentamos borrar el teléfono de su lista
                        if (libreta.get(nombreBorrarTel).remove(telABorrar)) {
                            System.out.println("¡Teléfono borrado!");
                        } else {
                            System.out.println("Ese teléfono no estaba en la lista de " + nombreBorrarTel);
                        }
                    } else {
                        System.out.println("Esa persona no está en la libreta.");
                    }
                    break;
                    
                case "5":
                    System.out.print("¿Qué persona quieres eliminar por completo?: ");
                    String nombreBorrar = sc.nextLine();
                    
                    // .remove() borra a la persona y su lista de teléfonos
                    if (libreta.containsKey(nombreBorrar)) {
                        libreta.remove(nombreBorrar);
                        System.out.println("¡Persona (y todos sus teléfonos) eliminada!");
                    } else {
                        System.out.println("Esa persona no estaba en la libreta.");
                    }
                    break;
                    
                case "6":
                    System.out.println("Cerrando la libreta de direcciones ");
                    break;
                    
                default:
                    System.out.println("Opción incorrecta. Por favor, elige del 1 al 6.");
            }
            
        } while (!opcion.equals("6"));
        
        sc.close();
    }
}