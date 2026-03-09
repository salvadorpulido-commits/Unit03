package ejemploExamen;

import java.util.Scanner; // Para leer datos del usuario

public class EjemploExamen02 {

    // ============================================================
    // FUNCIÓN 1: Mostrar todos los mensajes con su índice
    // Recibe: array de mensajes
    // ============================================================
    public static void mostrarMensajes(String[] mensajes) {
        // Recorremos el array y mostramos índice + mensaje
        for (int i = 0; i < mensajes.length; i++) {
            System.out.println(i + ". " + mensajes[i]);
        }
    }

    // ============================================================
    // FUNCIÓN 2: Validar si un índice existe en el array
    // Recibe: array de mensajes e índice a comprobar
    // Devuelve: true si el índice es válido, false si no
    // ============================================================
    public static boolean esIndiceValido(String[] mensajes, int indice) {
        // El índice es válido si está entre 0 y el último elemento del array
        return indice >= 0 && indice < mensajes.length;
    }

    // ============================================================
    // FUNCIÓN 3: Contar cuántas veces aparece una letra en un mensaje
    // Recibe: mensaje y letra a buscar
    // Devuelve: número de veces que aparece la letra
    // ============================================================
    public static int contarLetra(String mensaje, char letra) {
        int contador = 0; // Inicializamos el contador a 0

        // Convertimos ambos a minúsculas para no distinguir mayúsculas/minúsculas
        mensaje = mensaje.toLowerCase();
        letra   = Character.toLowerCase(letra);

        // Recorremos cada carácter del mensaje
        for (int i = 0; i < mensaje.length(); i++) {
            if (mensaje.charAt(i) == letra) {
                contador++; // Si coincide con la letra buscada, sumamos 1
            }
        }
        return contador; // Devolvemos el total encontrado
    }

    // ============================================================
    // FUNCIÓN 4: Quitar espacios de un mensaje
    // Recibe: mensaje original
    // Devuelve: mensaje sin espacios
    // ============================================================
    public static String quitarEspacios(String mensaje) {
        return mensaje.replace(" ", ""); // Reemplaza cada espacio por nada (lo elimina)
    }

    // ============================================================
    // FUNCIÓN 5: Extraer mensaje oculto
    // Toma los caracteres en posiciones PARES del mensaje sin espacios
    // Recibe: mensaje original
    // Devuelve: String con las letras de posición par (0, 2, 4...)
    // ============================================================
    public static String extraerMensajeOculto(String mensaje) {
        String sinEspacios = quitarEspacios(mensaje); // Primero eliminamos los espacios
        String oculto = "";                           // Aquí iremos construyendo el mensaje oculto

        // Recorremos solo las posiciones pares (0, 2, 4, 6...)
        for (int i = 0; i < sinEspacios.length(); i += 2) {
            oculto += sinEspacios.charAt(i); // Añadimos el carácter en posición par
        }
        return oculto; // Devolvemos el mensaje oculto formado
    }

    // ============================================================
    // FUNCIÓN 6: Buscar cuántos mensajes contienen una palabra
    // Recibe: array de mensajes y palabra a buscar
    // Devuelve: número de mensajes que contienen esa palabra
    // ============================================================
    public static int buscarMensajesConPalabra(String[] mensajes, String palabra) {
        int contador = 0;                       // Contador de mensajes que contienen la palabra
        palabra = palabra.toLowerCase();        // Pasamos la palabra buscada a minúsculas

        for (int i = 0; i < mensajes.length; i++) {
            // Convertimos el mensaje a minúsculas para comparar sin distinguir mayúsculas
            String mensajeLower = mensajes[i].toLowerCase();

            // contains() devuelve true si la cadena contiene la subcadena buscada
            if (mensajeLower.contains(palabra)) {
                contador++; // Si la contiene, sumamos 1 al contador
            }
        }
        return contador; // Devolvemos el número total de mensajes que la contienen
    }

    // ============================================================
    // FUNCIÓN 7: Marcar un mensaje como alterado
    // Añade "[ALTERADO] " al inicio del mensaje si no lo tiene ya
    // Recibe: array de mensajes e índice del mensaje a marcar
    // Devuelve: true si se marcó correctamente, false si no
    // ============================================================
    public static boolean marcarAlterado(String[] mensajes, int indice) {

        // Primero comprobamos que el índice sea válido
        if (!esIndiceValido(mensajes, indice)) {
            return false; // Índice fuera de rango, no se puede marcar
        }

        // Comprobamos si el mensaje YA tiene la marca para no añadirla dos veces
        if (mensajes[indice].startsWith("[ALTERADO] ")) {
            return false; // Ya estaba marcado, devolvemos false
        }

        // Añadimos la marca al principio del mensaje
        mensajes[indice] = "[ALTERADO] " + mensajes[indice];
        return true; // Marcado correctamente
    }

    // ============================================================
    // FUNCIÓN 8: Obtener la palabra más larga de un mensaje
    // Recibe: mensaje
    // Devuelve: la palabra más larga (en empate, la primera)
    // ============================================================
    public static String palabraMasLarga(String mensaje) {

        // split(" ") divide el mensaje en palabras usando el espacio como separador
        String[] palabras = mensaje.split(" ");

        String masLarga = ""; // Aquí guardaremos la palabra más larga encontrada

        // Recorremos todas las palabras del mensaje
        for (int i = 0; i < palabras.length; i++) {
            // Si la palabra actual es MÁS LARGA que la guardada, la reemplazamos
            // Con > en vez de >= garantizamos que en empate se queda la primera
            if (palabras[i].length() > masLarga.length()) {
                masLarga = palabras[i];
            }
        }
        return masLarga; // Devolvemos la palabra más larga encontrada
    }

    // ============================================================
    // FUNCIÓN 9: Mostrar estadísticas generales
    // Muestra: total mensajes, alterados, más largo, apariciones de 'A'
    // Recibe: array de mensajes
    // ============================================================
    public static void mostrarEstadisticas(String[] mensajes) {

        int totalMensajes  = mensajes.length; // Total de mensajes en el array
        int alterados      = 0;               // Contador de mensajes marcados como alterados
        int longitudMaxima = 0;               // Para encontrar el mensaje más largo
        int indiceMaximo   = 0;               // Índice del mensaje más largo
        int totalA         = 0;               // Total de veces que aparece la letra 'A'

        // Recorremos todos los mensajes para calcular las estadísticas
        for (int i = 0; i < mensajes.length; i++) {

            // Comprobamos si está marcado como alterado
            if (mensajes[i].startsWith("[ALTERADO] ")) {
                alterados++;
            }

            // Comprobamos si es el mensaje más largo
            if (mensajes[i].length() > longitudMaxima) {
                longitudMaxima = mensajes[i].length();
                indiceMaximo   = i; // Guardamos su índice
            }

            // Sumamos las apariciones de 'A' en este mensaje
            totalA += contarLetra(mensajes[i], 'a'); // Reutilizamos la función contarLetra
        }

        // Mostramos todas las estadísticas por pantalla
        System.out.println("\n===== ESTADÍSTICAS GENERALES =====");
        System.out.println("Total de mensajes      : " + totalMensajes);
        System.out.println("Mensajes alterados     : " + alterados);
        System.out.println("Mensaje más largo      : " + mensajes[indiceMaximo]);
        System.out.println("Total apariciones 'A'  : " + totalA);
        System.out.println("===================================");
    }

    // ============================================================
    // FUNCIÓN 10: Mostrar el menú principal
    // Muestra las opciones disponibles al usuario
    // ============================================================
    public static void mostrarMenu() {
        System.out.println("\n======= ARCHIVO DE LOS ECOS =======");
        System.out.println("1. Mostrar mensajes");
        System.out.println("2. Contar letra en un mensaje");
        System.out.println("3. Extraer mensaje oculto");
        System.out.println("4. Buscar palabra en todos los mensajes");
        System.out.println("5. Marcar mensaje como alterado");
        System.out.println("6. Mostrar palabra más larga de un mensaje");
        System.out.println("7. Mostrar estadísticas generales");
        System.out.println("8. Salir");
        System.out.print("Elige una opción: ");
    }

    // ============================================================
    // MAIN: Punto de entrada del programa
    // ============================================================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Lector de teclado

        // --- Inicializamos el array con los 8 mensajes ---
        String[] mensajes = {
            "LA NIEBLA CUBRE EL PUENTE",
            "NO MIRES DETRÁS DE LA PUERTA",
            "EL RELOJ SUENA A MEDIANOCHE",
            "LAS SOMBRAS SABEN TU NOMBRE",
            "TODO MENSAJE DEJA UNA HUELLA",
            "EL ECO REPITE LA VERDAD",
            "ALGUIEN ESCONDE ALGO AQUÍ",
            "NUNCA APAGUES LA RADIO"
        };

        int opcion = 0; // Variable para guardar la opción elegida por el usuario

        // --- Bucle principal: se repite hasta que el usuario elija salir (opción 8) ---
        do {
            mostrarMenu(); // Mostramos el menú en cada iteración
            opcion = Integer.parseInt(scanner.nextLine()); // Leemos la opción como entero

            switch (opcion) {

                case 1: // --- Mostrar todos los mensajes ---
                    System.out.println();
                    mostrarMensajes(mensajes);
                    break;

                case 2: // --- Contar letra en un mensaje ---
                    System.out.print("Introduce el índice del mensaje: ");
                    int idxLetra = Integer.parseInt(scanner.nextLine());

                    // Validamos el índice antes de continuar
                    if (!esIndiceValido(mensajes, idxLetra)) {
                        System.out.println("Índice no válido.");
                        break;
                    }
                    System.out.print("Introduce la letra a contar: ");
                    char letra = scanner.nextLine().charAt(0); // Tomamos solo el primer carácter

                    int veces = contarLetra(mensajes[idxLetra], letra);
                    System.out.println("La letra '" + letra + "' aparece " + veces + " veces en ese mensaje.");
                    break;

                case 3: // --- Extraer mensaje oculto ---
                    System.out.print("Introduce el índice del mensaje: ");
                    int idxOculto = Integer.parseInt(scanner.nextLine());

                    if (!esIndiceValido(mensajes, idxOculto)) {
                        System.out.println("Índice no válido.");
                        break;
                    }
                    String oculto = extraerMensajeOculto(mensajes[idxOculto]);
                    System.out.println("Mensaje oculto: " + oculto);
                    break;

                case 4: // --- Buscar palabra en todos los mensajes ---
                    System.out.print("Introduce la palabra a buscar: ");
                    String palabra = scanner.nextLine();

                    int encontrados = buscarMensajesConPalabra(mensajes, palabra);
                    System.out.println("La palabra '" + palabra + "' aparece en " + encontrados + " mensaje(s).");
                    break;

                case 5: // --- Marcar mensaje como alterado ---
                    System.out.print("Introduce el índice del mensaje a marcar: ");
                    int idxAlt = Integer.parseInt(scanner.nextLine());

                    boolean marcado = marcarAlterado(mensajes, idxAlt);
                    if (marcado) {
                        System.out.println("Mensaje marcado como alterado correctamente.");
                    } else {
                        System.out.println("No se pudo marcar (índice inválido o ya estaba alterado).");
                    }
                    break;

                case 6: // --- Mostrar la palabra más larga de un mensaje ---
                    System.out.print("Introduce el índice del mensaje: ");
                    int idxLarga = Integer.parseInt(scanner.nextLine());

                    if (!esIndiceValido(mensajes, idxLarga)) {
                        System.out.println("Índice no válido.");
                        break;
                    }
                    String masLarga = palabraMasLarga(mensajes[idxLarga]);
                    System.out.println("La palabra más larga es: " + masLarga);
                    break;

                case 7: // --- Mostrar estadísticas generales ---
                    mostrarEstadisticas(mensajes);
                    break;

                case 8: // --- Salir ---
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    break;

                default: // --- Opción no válida ---
                    System.out.println("Opción no reconocida. Inténtalo de nuevo.");
            }

        } while (opcion != 8); // Repetimos mientras el usuario no elija salir

        scanner.close(); // Cerramos el scanner al terminar
    }
}
