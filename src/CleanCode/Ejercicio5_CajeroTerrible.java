package CleanCode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5_CajeroTerrible {

    // CAMBIO: Renombrado de método para pedir PIN con nombre claro
    static int pedirPin(Scanner sc) {
        final int PIN_CORRECTO = 1234; // CAMBIO: constante con nombre descriptivo
        int intentos = 0; // CAMBIO: contador de intentos

        while (intentos < 3) { // CAMBIO: máximo 3 intentos
            System.out.print("Introduce tu PIN: ");
            int pin;
            try {
                pin = sc.nextInt(); // CAMBIO: lectura segura de entero
            } catch (InputMismatchException e) { // CAMBIO: control de error si introduce letra
                System.out.println("Error: debes introducir un número.");
                sc.nextLine(); // CAMBIO: limpia buffer
                continue; // CAMBIO: repite el bucle
            }

            if (pin == PIN_CORRECTO) {
                return 1; // CAMBIO: PIN correcto
            } else {
                System.out.println("PIN incorrecto"); // CAMBIO: mensaje claro
            }
            intentos++; // CAMBIO: incrementa contador
        }

        return -1; // CAMBIO: bloqueado tras 3 intentos
    }

    // CAMBIO: Renombrado de método del menú del cajero con nombre claro
    static double menuCajero(Scanner sc, String[] historial, int[] contador, double saldo) {
        int opcion = 0; // CAMBIO: opción del menú

        while (opcion != 5) { // CAMBIO: salir con opción 5
            System.out.println("\n--- MENÚ CAJERO ---"); // CAMBIO: menú legible
            System.out.println("1) Ingresar dinero");
            System.out.println("2) Retirar dinero");
            System.out.println("3) Consultar saldo");
            System.out.println("4) Ver historial");
            System.out.println("5) Salir");
            System.out.print("Elige opción: ");

            try {
                opcion = sc.nextInt(); // CAMBIO: lectura segura de opción
            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un número válido."); // CAMBIO: aviso error
                sc.nextLine(); // CAMBIO: limpia buffer
                continue; // CAMBIO: vuelve al menú
            }

            switch (opcion) { // CAMBIO: uso de switch para claridad
                case 1: // CAMBIO: Ingresar dinero
                    System.out.print("Cantidad a ingresar: ");
                    double ingreso = sc.nextDouble(); // CAMBIO: lectura cantidad
                    if (ingreso <= 0) {
                        System.out.println("Cantidad inválida."); // CAMBIO: validación
                    } else {
                        saldo += ingreso; // CAMBIO: actualiza saldo
                        historial[contador[0] % historial.length] = "INGRESO " + ingreso + " saldo=" + saldo; // CAMBIO: historial circular
                        contador[0]++; // CAMBIO: incrementa contador
                    }
                    break;

                case 2: // CAMBIO: Retirar dinero
                    System.out.print("Cantidad a retirar: ");
                    double retiro = sc.nextDouble();
                    if (retiro <= 0) {
                        System.out.println("Cantidad inválida.");
                    } else if (retiro > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= retiro; // CAMBIO: resta del saldo
                        historial[contador[0] % historial.length] = "RETIRO " + retiro + " saldo=" + saldo; // CAMBIO: registra historial
                        contador[0]++;
                    }
                    break;

                case 3: // CAMBIO: Consultar saldo
                    System.out.println("Saldo actual: " + saldo);
                    break;

                case 4: // CAMBIO: Mostrar historial
                    System.out.println("--- HISTORIAL ---");
                    for (int i = 0; i < historial.length; i++) {
                        if (historial[i] != null) {
                            System.out.println(historial[i]);
                        } else {
                            System.out.println("-"); // CAMBIO: rellena con guiones
                        }
                    }
                    break;

                case 5: // CAMBIO: Salir
                    System.out.println("Saliendo del cajero...");
                    break;

                default: // CAMBIO: opción inválida
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        return saldo; // CAMBIO: devuelve saldo actualizado
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // CAMBIO: nombre estándar del Scanner
        double saldo = 500; // CAMBIO: saldo inicial
        String[] historial = new String[5]; // CAMBIO: historial de últimas 5 operaciones
        int[] contador = new int[]{0}; // CAMBIO: contador de operaciones

        // CAMBIO: pedimos PIN y validamos
        int resultadoPin = pedirPin(sc);
        if (resultadoPin == -1) {
            System.out.println("Cuenta bloqueada."); // CAMBIO: mensaje bloqueado
            return;
        }

        // CAMBIO: ejecuta menú del cajero
        saldo = menuCajero(sc, historial, contador, saldo);

        System.out.println("FIN. Saldo final: " + saldo); // CAMBIO: imprime saldo final
        sc.close(); // CAMBIO: cerramos scanner
    }
}
