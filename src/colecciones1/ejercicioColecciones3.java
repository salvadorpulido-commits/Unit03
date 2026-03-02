package colecciones1;

import java.util.ArrayList;
import java.util.Collections;

public class ejercicioColecciones3 {

    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<Integer>();
        int numero = 0;
        int i = 0;

        for (i = 0; i < 30; i++) {
            numero = (int)(Math.random() * 10) + 1;
            lista.add(numero);
        }

        Collections.sort(lista);

        System.out.println("Colección ordenada:");
        System.out.println(lista);
    }
}