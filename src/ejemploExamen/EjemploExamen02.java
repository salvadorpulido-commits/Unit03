package ejemploExamen;

import java.util.Scanner;

public class EjemploExamen02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Almacenamiento de mensajes (array de 8 posiciones)
        String[] mensajes = {
            "LA NIEBLA CUBRE EL PUENTE",
            "NO MIRES DETRAS DE LA PUERTA",
            "EL RELOJ SUENA A MEDIANOCHE",
            "LAS SOMBRAS SABEN TU NOMBRE",
            "TODO MENSAJE DEJA UNA HUELLA",
            "EL ECO REPITE LA VERDAD",
            "ALGUIEN ESCONDE ALGO AQUI",
            "NUNCA APAGUES LA RADIO"
        };

        int opcion = 0;

        // 10. Menú principal repetitivo
        do {
            System.out.println("\n--- ARCHIVO DE LOS ECOS ---");
            System.out.println("1. Mostrar mensajes");
            System.out.println("2. Contar letra en un mensaje");
            System.out.println("3. Extraer mensaje oculto");
            System.out.println("4. Buscar palabra en todos los mensajes");
            System.out.println("5. Marcar mensaje como alterado");
            System.out.println("6. Mostrar palabra más larga de un mensaje");
            System.out.println("7. Mostrar estadísticas generales");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // ¡CRUCIAL! Limpiamos el buffer del intro tras leer un número

            switch (opcion) {
                case 1:
                    mostrarMensajes(mensajes);
                    break;
                case 2:
                    mostrarMensajes(mensajes);
                    System.out.print("Elige el índice del mensaje: ");
                    int indLetra = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    
                    if (esIndiceValido(mensajes, indLetra)) {
                        System.out.print("Introduce la letra a contar: ");
                        char letra = scanner.nextLine().charAt(0);
                        int cantidad = contarLetra(mensajes[indLetra], letra);
                        System.out.println("La letra '" + letra + "' aparece " + cantidad + " veces.");
                    } else {
                        System.out.println("Índice no válido.");
                    }
                    break;
                case 3:
                    mostrarMensajes(mensajes);
                    System.out.print("Elige el índice del mensaje: ");
                    int indOculto = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (esIndiceValido(mensajes, indOculto)) {
                        String oculto = extraerMensajeOculto(mensajes[indOculto]);
                        System.out.println("Mensaje oculto: " + oculto);
                    } else {
                        System.out.println("Índice no válido.");
                    }
                    break;
                case 4:
                    System.out.print("Introduce la palabra a buscar: ");
                    String palabra = scanner.nextLine();
                    int encontrados = buscarMensajesConPalabra(mensajes, palabra);
                    System.out.println("La palabra aparece en " + encontrados + " mensajes.");
                    break;
                case 5:
                    mostrarMensajes(mensajes);
                    System.out.print("Elige el índice del mensaje a alterar: ");
                    int indAlterar = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (marcarAlterado(mensajes, indAlterar)) {
                        System.out.println("Mensaje alterado correctamente.");
                    } else {
                        System.out.println("No se pudo alterar (índice inválido o ya estaba alterado).");
                    }
                    break;
                case 6:
                    mostrarMensajes(mensajes);
                    System.out.print("Elige el índice del mensaje: ");
                    int indLarga = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (esIndiceValido(mensajes, indLarga)) {
                        System.out.println("Palabra más larga: " + palabraMasLarga(mensajes[indLarga]));
                    } else {
                        System.out.println("Índice no válido.");
                    }
                    break;
                case 7:
                    mostrarEstadisticas(mensajes);
                    break;
                case 8:
                    System.out.println("Cerrando el Archivo de los Ecos...");
                    break;
                default:
                    System.out.println("Opción incorrecta. Inténtalo de nuevo.");
            }
        } while (opcion != 8);

        scanner.close();
    }

    // --- FUNCIONES SOLICITADAS ---

    // 2. Mostrar todos los mensajes
    public static void mostrarMensajes(String[] mensajes) {
        System.out.println("\nLista de mensajes:");
        for (int i = 0; i < mensajes.length; i++) {
            System.out.println(i + ". " + mensajes[i]);
        }
    }

    // 3. Validar índice
    public static boolean esIndiceValido(String[] mensajes, int indice) {
        // Un índice es válido si es mayor o igual a 0 y menor que el tamaño del array
        return indice >= 0 && indice < mensajes.length;
    }

    // 4. Contar cuántas veces aparece una letra (sin distinguir mayúsculas/minúsculas)
    public static int contarLetra(String mensaje, char letra) {
        int contador = 0;
        // Convertimos todo a minúsculas para comparar fácilmente
        String mensajeMin = mensaje.toLowerCase();
        char letraMin = Character.toLowerCase(letra);

        for (int i = 0; i < mensajeMin.length(); i++) {
            if (mensajeMin.charAt(i) == letraMin) {
                contador++;
            }
        }
        return contador;
    }

    // 5. Obtener mensaje sin espacios
    public static String quitarEspacios(String mensaje) {
        return mensaje.replace(" ", "");
    }

    // 6. Extraer mensaje oculto (posiciones pares sin contar espacios)
    public static String extraerMensajeOculto(String mensaje) {
        // Reutilizamos la función anterior (modularidad)
        String sinEspacios = quitarEspacios(mensaje);
        String resultado = "";

        // Saltamos de 2 en 2 para pillar las posiciones pares (0, 2, 4...)
        for (int i = 0; i < sinEspacios.length(); i += 2) {
            resultado += sinEspacios.charAt(i);
        }
        return resultado;
    }

    // 7. Buscar palabra en los mensajes
    public static int buscarMensajesConPalabra(String[] mensajes, String palabra) {
        int contador = 0;
        String palabraMin = palabra.toLowerCase();

        for (int i = 0; i < mensajes.length; i++) {
            // .contains() verifica si una cadena está dentro de otra
            if (mensajes[i].toLowerCase().contains(palabraMin)) {
                contador++;
            }
        }
        return contador;
    }

    // 8. Marcar mensaje alterado
    public static boolean marcarAlterado(String[] mensajes, int indice) {
        if (!esIndiceValido(mensajes, indice)) {
            return false;
        }

        // Si ya empieza por [ALTERADO], devolvemos false
        if (mensajes[indice].startsWith("[ALTERADO] ")) {
            return false;
        }

        // Modificamos el array original añadiendo la marca
        mensajes[indice] = "[ALTERADO] " + mensajes[indice];
        return true;
    }

    // 9. Obtener la palabra más larga de un mensaje
    public static String palabraMasLarga(String mensaje) {
        // .split(" ") divide el string en un array de palabras usando el espacio como separador
        String[] palabras = mensaje.split(" ");
        String masLarga = "";

        for (int i = 0; i < palabras.length; i++) {
            // Si la palabra actual es estrictamente mayor, la guardamos. 
            // Al usar ">" y no ">=", respetamos que en caso de empate se quede la primera.
            if (palabras[i].length() > masLarga.length()) {
                masLarga = palabras[i];
            }
        }
        return masLarga;
    }

    // 11. Función de estadísticas generales
    public static void mostrarEstadisticas(String[] mensajes) {
        int totalMensajes = mensajes.length;
        int alterados = 0;
        int maxLongitud = 0;
        String mensajeMasLargo = "";
        int totalLetraA = 0;

        for (int i = 0; i < mensajes.length; i++) {
            // Contar alterados
            if (mensajes[i].startsWith("[ALTERADO] ")) {
                alterados++;
            }

            // Buscar el mensaje más largo
            if (mensajes[i].length() > maxLongitud) {
                maxLongitud = mensajes[i].length();
                mensajeMasLargo = mensajes[i];
            }

            // Contar letra 'A' reutilizando nuestra función
            totalLetraA += contarLetra(mensajes[i], 'A');
        }

        System.out.println("\n--- ESTADÍSTICAS GENERALES ---");
        System.out.println("Total de mensajes: " + totalMensajes);
        System.out.println("Mensajes alterados: " + alterados);
        System.out.println("Mensaje de mayor longitud: \"" + mensajeMasLargo + "\" (" + maxLongitud + " caracteres)");
        System.out.println("La letra 'A' aparece un total de " + totalLetraA + " veces.");
    }
}