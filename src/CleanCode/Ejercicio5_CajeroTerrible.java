package CleanCode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5_CajeroTerrible {

    /**
     * Pide el PIN al usuario. Devuelve:
     *  1 si es correcto,
     *  0 si incorrecto pero quedan intentos,
     * -1 si se bloquea tras 3 intentos.
     */
    static int pedirPin(Scanner sc) {
        final int PIN_CORRECTO = 1234;
        int intentos = 0;

        while (intentos < 3) {
            System.out.print("Introduce tu PIN: ");
            int pin;
            try {
                pin = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: debes introducir un número.");
                sc.nextLine();
                continue;
            }

            if (pin == PIN_CORRECTO) {
                return 1; // PIN correcto
            } else {
                System.out.println("PIN incorrecto");
            }
            intentos++;
        }

        return -1; // bloqueado
    }

    /**
     * Ejecuta el menú del cajero automático.
     * 
     * @param sc Scanner para entrada de usuario
     * @param historial Array de strings con las últimas operaciones
     * @param contador Operaciones realizadas (indice en historial)
     * @param saldo Saldo actual
     * @return saldo actualizado
     */
    static double menuCajero(Scanner sc, String[] historial, int[] contador, double saldo) {
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- MENÚ CAJERO ---");
            System.out.println("1) Ingresar dinero");
            System.out.println("2) Retirar dinero");
            System.out.println("3) Consultar saldo");
            System.out.println("4) Ver historial");
            System.out.println("5) Salir");
            System.out.print("Elige opción: ");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1: // Ingresar
                    System.out.print("Cantidad a ingresar: ");
                    double ingreso = sc.nextDouble();
                    if (ingreso <= 0) {
                        System.out.println("Cantidad inválida.");
                    } else {
                        saldo += ingreso;
                        historial[contador[0] % historial.length] = "INGRESO " + ingreso + " saldo=" + saldo;
                        contador[0]++;
                    }
                    break;

                case 2: // Retirar
                    System.out.print("Cantidad a retirar: ");
                    double retiro = sc.nextDouble();
                    if (retiro <= 0) {
                        System.out.println("Cantidad inválida.");
                    } else if (retiro > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= retiro;
                        historial[contador[0] % historial.length] = "RETIRO " + retiro + " saldo=" + saldo;
                        contador[0]++;
                    }
                    break;

                case 3: // Consultar saldo
                    System.out.println("Saldo actual: " + saldo);
                    break;

                case 4: // Ver historial
                    System.out.println("--- HISTORIAL ---");
                    for (int i = 0; i < historial.length; i++) {
                        if (historial[i] != null) {
                            System.out.println(historial[i]);
                        } else {
                            System.out.println("-"); // rellena con guiones
                        }
                    }
                    break;

                case 5: // Salir
                    System.out.println("Saliendo del cajero...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        return saldo;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double saldo = 500; // saldo inicial
        String[] historial = new String[5]; // historial de operaciones
        int[] contador = new int[]{0}; // contador de operaciones

        int resultadoPin = pedirPin(sc);
        if (resultadoPin == -1) {
            System.out.println("Cuenta bloqueada.");
            return;
        }

        saldo = menuCajero(sc, historial, contador, saldo);

        System.out.println("FIN. Saldo final: " + saldo);
        sc.close();
    }
}
