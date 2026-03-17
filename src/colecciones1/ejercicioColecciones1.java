package colecciones1;

import java.util.ArrayList;
import java.util.Scanner;

public class ejercicioColecciones1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);{
        ArrayList<Integer> lista = new ArrayList<Integer>();
        int numero = 0;
        int i = 0;

        System.out.println("Introduce números positivos (negativo para terminar):");
        numero = sc.nextInt();

        while (numero >= 0) {
            lista.add(numero);
            numero = sc.nextInt();
        }

        for (i = 0; i < lista.size(); i++) {
            if (lista.get(i) % 2 == 0) {
                System.out.println("Índice del número par: " + i);
            }
        }

        sc.close();
    }
}
    
}