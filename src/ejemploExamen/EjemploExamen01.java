package ejemploExamen;

import java.util.ArrayList;  // Para usar listas dinámicas
import java.util.HashSet;    // Para guardar índices sin repetición
import java.util.Scanner;    // Para leer datos del usuario
import java.util.Set;        // Interfaz que usa HashSet

public class EjemploExamen01 {

    // ============================================================
    // FUNCIÓN 1: Normalizar el texto
    // Convierte a minúsculas y elimina espacios
    // Recibe: String original | Devuelve: String limpio
    // ============================================================
    public static String normalizar(String texto) {
        texto = texto.toLowerCase();    // Convierte todas las letras a minúsculas
        texto = texto.replace(" ", ""); // Elimina todos los espacios del texto
        return texto;                   // Devuelve el texto normalizado
    }

    // ============================================================
    // FUNCIÓN 2: Convertir String a array de caracteres
    // Recibe: String | Devuelve: char[]
    // ============================================================
    public static char[] convertirAArray(String texto) {
        return texto.toCharArray(); // Convierte el String en un array de chars y lo devuelve
    }

    // ============================================================
    // FUNCIÓN 3: Buscar el mensaje oculto en el texto
    // Recibe: array del texto y array del mensaje
    // Devuelve: array con las posiciones de inicio de cada aparición
    // ============================================================
    public static int[] buscarMensaje(char[] texto, char[] mensaje) {

        // Usamos ArrayList porque no sabemos cuántas apariciones habrá
        ArrayList<Integer> posiciones = new ArrayList<>();

        // Probamos cada posición del texto como posible punto de inicio
        for (int i = 0; i < texto.length; i++) {

            int posTexto = i;    // Puntero que avanza por el texto desde la posición i
            int posMensaje = 0;  // Puntero que avanza por el mensaje letra a letra

            // Buscamos las letras del mensaje en orden dentro del texto
            while (posTexto < texto.length && posMensaje < mensaje.length) {
                if (texto[posTexto] == mensaje[posMensaje]) {
                    posMensaje++; // Si la letra coincide, avanzamos en el mensaje
                }
                posTexto++;       // Siempre avanzamos en el texto
            }

            // Si posMensaje llegó al final, encontramos todas las letras del mensaje
            if (posMensaje == mensaje.length) {
                posiciones.add(i); // Guardamos la posición de inicio de esta aparición
            }
        }

        // Convertimos el ArrayList a un array int[] para devolverlo
        int[] resultado = new int[posiciones.size()];
        for (int i = 0; i < posiciones.size(); i++) {
            resultado[i] = posiciones.get(i);
        }
        return resultado; // Devuelve array vacío si no hubo apariciones
    }

    // ============================================================
    // FUNCIÓN 4: Calcular el porcentaje de letras del texto usadas
    // Recibe: array del texto, array del mensaje, posiciones encontradas
    // Devuelve: double con el porcentaje
    // ============================================================
    public static double calcularPorcentaje(char[] texto, char[] mensaje, int[] posiciones) {

        // Usamos un Set para guardar índices sin duplicados
        // (una misma letra del texto podría usarse en varias apariciones)
        Set<Integer> indicesUsados = new HashSet<>();

        // Para cada aparición encontrada, simulamos la búsqueda
        // y marcamos qué índices del texto fueron utilizados
        for (int pos : posiciones) {
            int posTexto = pos;  // Empezamos desde la posición de inicio de esta aparición
            int posMensaje = 0;  // Recorremos el mensaje desde el principio

            while (posTexto < texto.length && posMensaje < mensaje.length) {
                if (texto[posTexto] == mensaje[posMensaje]) {
                    indicesUsados.add(posTexto); // Marcamos este índice del texto como usado
                    posMensaje++;
                }
                posTexto++;
            }
        }

        // Porcentaje = (letras usadas / total letras del texto) * 100
        return (double) indicesUsados.size() / texto.length * 100;
    }

    // ============================================================
    // MAIN: Punto de entrada del programa
    // ============================================================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Creamos el lector de teclado

        // --- Pedimos los datos al usuario ---
        System.out.println("Introduce el texto:");
        String textoOriginal = scanner.nextLine(); // Leemos el texto completo (con espacios)

        System.out.println("Introduce el mensaje oculto a buscar:");
        String mensajeOriginal = scanner.nextLine(); // Leemos el mensaje a buscar

        // --- Normalizamos ambos (minúsculas y sin espacios) ---
        String textoNormalizado   = normalizar(textoOriginal);
        String mensajeNormalizado = normalizar(mensajeOriginal);

        // --- Convertimos a arrays de caracteres ---
        char[] textoArray   = convertirAArray(textoNormalizado);
        char[] mensajeArray = convertirAArray(mensajeNormalizado);

        // --- Buscamos el mensaje en el texto ---
        int[] posiciones = buscarMensaje(textoArray, mensajeArray);

        // --- Mostramos los resultados ---
        System.out.println("\n--- RESULTADOS ---");
        System.out.println("El mensaje aparece " + posiciones.length + " veces.");

        if (posiciones.length > 0) {
            // Mostramos las posiciones de inicio de cada aparición
            System.out.print("Posiciones de inicio: ");
            for (int pos : posiciones) {
                System.out.print(pos + " ");
            }
            System.out.println();

            // Calculamos y mostramos el porcentaje de letras usadas
            double porcentaje = calcularPorcentaje(textoArray, mensajeArray, posiciones);
            System.out.printf("Porcentaje de letras utilizadas: %.2f%%%n", porcentaje);

        } else {
            System.out.println("No se encontró el mensaje en el texto.");
        }

        scanner.close(); // Cerramos el scanner para liberar recursos
    }
}