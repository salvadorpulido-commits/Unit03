package repaso;

public class Ejercicio02GestionNotas {

    public static void main(String[] args) {

        double[] notas = { 6.5, 4.3, 8.2, 9.0, 5.5, 3.8 };
        double media;
        double maxima;
        int aprobados;

        System.out.println("Notas originales:");
        mostrarNotas(notas);

        media = calcularMedia(notas);
        maxima = notaMaxima(notas);
        aprobados = contarAprobados(notas);

        System.out.println("\nMedia: " + media);
        System.out.println("Nota máxima: " + maxima);
        System.out.println("Aprobados: " + aprobados);

        subirNotas(notas, 0.5);

        System.out.println("\nNotas tras la subida:");
        mostrarNotas(notas);
    }

    static double calcularMedia(double[] notas) {
        double suma = 0;

        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        return suma / notas.length;
    }

    static double notaMaxima(double[] notas) {
        double max = notas[0];

        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
            }
        }
        return max;
    }

    static int contarAprobados(double[] notas) {
        int contador = 0;

        for (int i = 0; i < notas.length; i++) {
            if (notas[i] >= 5) {
                contador++;
            }
        }
        return contador;
    }

    static void subirNotas(double[] notas, double incremento) {
        for (int i = 0; i < notas.length; i++) {
            notas[i] += incremento;
            if (notas[i] > 10) {
                notas[i] = 10;
            }
        }
    }

    static void mostrarNotas(double[] notas) {
        for (int i = 0; i < notas.length; i++) {
            System.out.print(notas[i] + " ");
        }
        System.out.println();
    }
}
