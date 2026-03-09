package colecciones1;

import java.util.ArrayList;

public class ejercicioColecciones2 {

    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<Integer>();
        int tamaño = 0;
        int numero = 0;
        int suma = 0;
        double media = 0;
        int maximo = 0;
        int minimo = 0;
        int i = 0;

        // Tamaño aleatorio entre 5 y 10
        tamaño = (int)(Math.random() * 6) + 5;

        // Llenar la lista con números aleatorios entre 0 y 100
        for (i = 0; i < tamaño; i++) {
            numero = (int)(Math.random() * 101);
            lista.add(numero);
        }

        // Inicializamos máximo y mínimo con el primer elemento
        maximo = lista.get(0);
        minimo = lista.get(0);

        // Recorrer la lista para calcular suma, máximo y mínimo
        for (i = 0; i < lista.size(); i++) {

            numero = lista.get(i);
            suma = suma + numero;

            if (numero > maximo) {
                maximo = numero;
            }

            if (numero < minimo) {
                minimo = numero;
            }
        }

        // Calcular media
        media = (double)suma / lista.size();

        // Mostrar resultados
        System.out.println("Lista generada: " + lista);
        System.out.println("Suma: " + suma);
        System.out.println("Media: " + media);
        System.out.println("Máximo: " + maximo);
        System.out.println("Mínimo: " + minimo);
    }
}