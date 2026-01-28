package repaso;

import java.util.Scanner;

public class Ejercicio01AnalisisDeFrases {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String frase;
        String[] palabras;
        int palabrasLargas;
        String masLarga;

        System.out.println("Introduce una frase:");
        frase = sc.nextLine();

        palabras = obtenerPalabras(frase);

        palabrasLargas = contarPalabrasLargas(palabras, 5);
        masLarga = palabraMasLarga(palabras);

        System.out.println("\nPalabras de la frase:");
        for (int i = 0; i < palabras.length; i++) {
            System.out.println(palabras[i]);
        }

        System.out.println("\nNúmero total de palabras: " + palabras.length);
        System.out.println("Palabras con 5 o más letras: " + palabrasLargas);
        System.out.println("Palabra más larga: " + masLarga);

        sc.close();
    }

    static String[] obtenerPalabras(String frase) {
        frase = frase.trim();
        frase = frase.toLowerCase();
        return frase.split(" ");
    }

    static int contarPalabrasLargas(String[] palabras, int longitudMinima) {
        int contador = 0;

        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].length() >= longitudMinima) {
                contador++;
            }
        }
        return contador;
    }

    static String palabraMasLarga(String[] palabras) {
        String mayor = palabras[0];

        for (int i = 1; i < palabras.length; i++) {
            if (palabras[i].length() > mayor.length()) {
                mayor = palabras[i];
            }
        }
        return mayor;
    }
}