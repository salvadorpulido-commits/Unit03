package repaso;

public class Ejercicio03BuscaPalabras {

	 public static void main(String[] args) {

	        String texto = "Java es un lenguaje de programación muy utilizado en desarrollo de software";
	        String[] claves = { "java", "python", "software", "web" };
	        String[] encontradas;

	        encontradas = palabrasEncontradas(texto, claves);

	        mostrarResultado(encontradas);
	    }

	    static boolean contienePalabra(String texto, String palabra) {
	        texto = texto.toLowerCase();
	        palabra = palabra.toLowerCase();

	        return texto.contains(palabra);
	    }

	    static String[] palabrasEncontradas(String texto, String[] claves) {
	        int contador = 0;

	        for (int i = 0; i < claves.length; i++) {
	            if (contienePalabra(texto, claves[i])) {
	                contador++;
	            }
	        }

	        String[] resultado = new String[contador];
	        int posicion = 0;

	        for (int i = 0; i < claves.length; i++) {
	            if (contienePalabra(texto, claves[i])) {
	                resultado[posicion] = claves[i];
	                posicion++;
	            }
	        }
	        return resultado;
	    }

	    static void mostrarResultado(String[] encontradas) {
	        if (encontradas.length == 0) {
	            System.out.println("No se ha encontrado ninguna palabra clave.");
	        } else {
	            System.out.println("Palabras encontradas: " + String.join(", ", encontradas));
	        }
	    }
	}

