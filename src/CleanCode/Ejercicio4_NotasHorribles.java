package CleanCode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio4_NotasHorribles {

    // Método con nombres claros y estructura ordenada
    static int fff(double[][] notas, int alumnos, int modulos, int opcion) {

        double suma;
        double minimo = 10;
        double maximo = 0;

        if (notas == null) return -9;
        if (alumnos <= 0 || modulos <= 0) return -8;

        if (opcion == 2) { // ver notas
            for (int i = 0; i < alumnos; i++) {
                for (int j = 0; j < modulos; j++) {
                    System.out.print(notas[i][j] + " ");
                }
                System.out.println();
            }
            return 0;
        }

        if (opcion == 3) { // media por alumno
            for (int i = 0; i < alumnos; i++) {
                suma = 0;
                for (int j = 0; j < modulos; j++) {
                    suma += notas[i][j];
                }
                System.out.println("Alumno " + i + " media = " + (suma / modulos));
            }
            return 1;
        }

        if (opcion == 4) { // media por módulo
            for (int j = 0; j < modulos; j++) {
                suma = 0;
                for (int i = 0; i < alumnos; i++) {
                    suma += notas[i][j];
                }
                System.out.println("Módulo " + j + " media = " + (suma / alumnos));
            }
            return 2;
        }

        if (opcion == 5) { // mínimo y máximo
            for (int i = 0; i < alumnos; i++) {
                for (int j = 0; j < modulos; j++) {
                    if (notas[i][j] < minimo) minimo = notas[i][j];
                    if (notas[i][j] > maximo) maximo = notas[i][j];
                }
            }
            System.out.println("Nota mínima = " + minimo);
            System.out.println("Nota máxima = " + maximo);
            return 3;
        }

        return -1;
    }

   
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        int alumnos = 0;
        int modulos = 0;
        double[][] notas = null;
        boolean hayDatos = false;

        System.out.println("Gestor de notas");

        while (opcion != 6) {

            System.out.println("1) Cargar notas");
            System.out.println("2) Ver notas");
            System.out.println("3) Media por alumno");
            System.out.println("4) Media por módulo");
            System.out.println("5) Nota mínima y máxima");
            System.out.println("6) Salir");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un número válido.");
                sc.nextLine();
                continue;
            }

            if (opcion == 1) {

                System.out.print("Número de alumnos: ");
                alumnos = sc.nextInt();

                System.out.print("Número de módulos: ");
                modulos = sc.nextInt();

                notas = new double[alumnos][modulos];

                for (int i = 0; i < alumnos; i++) {
                    for (int j = 0; j < modulos; j++) {
                        System.out.print("Nota del alumno " + i + ", módulo " + j + ": ");

                        double nota = sc.nextDouble();
                        if (nota < 0) nota = 0;
                        if (nota > 10) nota = 10;

                        notas[i][j] = nota;
                    }
                }

                hayDatos = true;

            } else if (opcion >= 2 && opcion <= 5) {

                if (!hayDatos) {
                    System.out.println("Error: no hay datos cargados.");
                } else {
                    int resultado = fff(notas, alumnos, modulos, opcion);
                    if (resultado < 0) {
                        System.out.println("Error interno: " + resultado);
                    }
                }

            } else if (opcion != 6) {
                System.out.println("Opción no válida.");
            }
        }

        System.out.println("Programa finalizado.");
        sc.close();
    }
}
