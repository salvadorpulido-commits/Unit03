package ejemploExamen;

import java.util.Scanner;

public class EjemploExamen01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Solicitamos los datos al usuario
        System.out.println("Introduce el texto largo a analizar:");
        String textoOriginal = scanner.nextLine();

        System.out.println("Introduce el mensaje oculto a buscar:");
        String mensajeOriginal = scanner.nextLine();

        // 2. Normalizamos el texto y el mensaje (minúsculas y sin espacios)
        String textoLimpio = normalizarTexto(textoOriginal);
        String mensajeLimpio = normalizarTexto(mensajeOriginal);

        // 3. Convertimos a arrays de caracteres usando nuestra propia función
        char[] arrayTexto = convertirAArray(textoLimpio);
        char[] arrayMensaje = convertirAArray(mensajeLimpio);

        // Validación de seguridad para evitar errores si introducen cadenas vacías
        if (arrayTexto.length == 0 || arrayMensaje.length == 0) {
            System.out.println("El texto o el mensaje están vacíos. No se puede analizar.");
            scanner.close();
            return;
        }

        // 4. Buscamos el mensaje
        int[] posiciones = buscarMensaje(arrayTexto, arrayMensaje);

        // 5. Mostramos los resultados
        System.out.println("\n--- RESULTADOS DEL ANÁLISIS ---");
        System.out.println("El mensaje aparece: " + posiciones.length + " veces.");
        
        if (posiciones.length > 0) {
            System.out.print("Posiciones iniciales en el texto limpio: ");
            for (int i = 0; i < posiciones.length; i++) {
                System.out.print(posiciones[i]);
                if (i < posiciones.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        // 6. Calculamos y mostramos el porcentaje
        double porcentaje = calcularPorcentaje(posiciones.length, arrayMensaje.length, arrayTexto.length);
        System.out.printf("Porcentaje de letras utilizadas: %.2f%%\n", porcentaje);

        scanner.close();
    }

    // --- FUNCIONES OBLIGATORIAS ---

    /**
     * Normalización del texto: convierte a minúsculas y elimina espacios.
     */
    public static String normalizarTexto(String texto) {
        // toLowerCase() pasa a minúsculas y replace() cambia los espacios por "nada"
        return texto.toLowerCase().replace(" ", "");
    }

    /**
     * Conversión a array de caracteres de forma manual.
     * Aunque existe texto.toCharArray(), en los exámenes suelen pedir que sepas hacerlo con un bucle.
     */
    public static char[] convertirAArray(String texto) {
        char[] resultado = new char[texto.length()];
        for (int i = 0; i < texto.length(); i++) {
            resultado[i] = texto.charAt(i); // Copiamos letra a letra
        }
        return resultado;
    }

    /**
     * Búsqueda del mensaje: Devuelve un array con las posiciones iniciales.
     */
    public static int[] buscarMensaje(char[] textoArr, char[] mensajeArr) {
        // Creamos un array temporal del tamaño del texto (el peor caso es que empiece en cada letra)
        int[] iniciosTemporales = new int[textoArr.length];
        int contadorApariciones = 0;
        
        int indiceTexto = 0; // Desde dónde empezamos a buscar en el texto general

        while (indiceTexto < textoArr.length) {
            int indiceMensaje = 0; // Apunta a la letra del mensaje que estamos buscando
            int posicionInicioActual = -1; // Guardará dónde empezó este mensaje
            int ultimaPosicionEncontrada = -1; // Guardará la posición de la última letra encontrada

            // Recorremos el texto desde la posición actual hacia adelante
            for (int i = indiceTexto; i < textoArr.length; i++) {
                // Si la letra del texto coincide con la letra del mensaje que buscamos
                if (textoArr[i] == mensajeArr[indiceMensaje]) {
                    
                    // Si es la primera letra del mensaje, guardamos esta posición de inicio
                    if (indiceMensaje == 0) {
                        posicionInicioActual = i;
                    }
                    
                    // Avanzamos a la siguiente letra del mensaje
                    indiceMensaje++;

                    // Si ya hemos encontrado todas las letras del mensaje
                    if (indiceMensaje == mensajeArr.length) {
                        ultimaPosicionEncontrada = i; // Guardamos dónde terminó
                        break; // Salimos del bucle 'for' porque ya encontramos el mensaje completo
                    }
                }
            }

            // Si al salir del bucle 'for' encontramos el mensaje completo
            if (indiceMensaje == mensajeArr.length) {
                iniciosTemporales[contadorApariciones] = posicionInicioActual;
                contadorApariciones++;
                // Para la siguiente iteración del 'while', empezamos a buscar DESPUÉS de donde terminó el último
                indiceTexto = ultimaPosicionEncontrada + 1; 
            } else {
                // Si buscamos hasta el final y NO completamos el mensaje, es imposible que aparezca más veces.
                // Rompemos el 'while' para terminar.
                break;
            }
        }

        // Finalmente, creamos un array del tamaño exacto de las apariciones para devolverlo
        int[] resultadoFinal = new int[contadorApariciones];
        for (int i = 0; i < contadorApariciones; i++) {
            resultadoFinal[i] = iniciosTemporales[i];
        }

        return resultadoFinal;
    }

    /**
     * Cálculo de porcentaje de uso.
     */
    public static double calcularPorcentaje(int apariciones, int longMensaje, int longTexto) {
        if (longTexto == 0) return 0.0; // Evitar división por cero

        // Total de caracteres gastados = veces que aparece * longitud del mensaje
        int caracteresUtilizados = apariciones * longMensaje;
        
        // Regla de 3: (parte / total) * 100
        // Multiplicamos por 100.0 (con decimal) para que Java no haga una división entera y pierda los decimales
        return (caracteresUtilizados * 100.0) / longTexto;
    }
}
