package CleanCode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5_CajeroTerrible {

    //Renombre para pedir PIN con nombre claro
    static int pedirPin(Scanner sc) {
        // constante con nombre corregido
        final int PIN_CORRECTO = 1234; 
        int intentos = 0; // contador de intentos

        //mximo 3 intentos
        while (intentos < 3) { 
            System.out.print("Introduce tu PIN: ");
            int pin;
            try {
                pin = sc.nextInt(); 
            } catch (InputMismatchException e) { // lanza error si se introduce letra
                System.out.println("Error: debes introducir un número.");
                sc.nextLine(); // limpia el  buffer
                continue; //repite el bucle
            }

            if (pin == PIN_CORRECTO) {
                return 1; //PIN correcto
            } else {
                System.out.println("PIN incorrecto"); // devuelve mensaje mas claro si se introduce mal el pin
            }
            intentos++; // incrementa contador
        }

        return -1; // bloqueado tras 3 intentos
    }

    //Renombre del menu del cajero con nombre mas claro
    static double menuCajero(Scanner sc, String[] historial, int[] contador, double saldo) {
        int opcion = 0; //opción del menu

        while (opcion != 5) { //se añade salir con opción 5
            System.out.println("\n--- MENÚ CAJERO ---"); // menu mas legible
            System.out.println("1) Ingresar dinero");
            System.out.println("2) Retirar dinero");
            System.out.println("3) Consultar saldo");
            System.out.println("4) Ver historial");
            System.out.println("5) Salir");
            System.out.print("Elige opción: ");

            try {
                opcion = sc.nextInt(); //lectura segura de opcion
            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un número válido."); // sec avisa si hay error
                sc.nextLine(); //limpia el buffer
                continue; //vuelve al menu
            }

            switch (opcion) { // se usa switch para mayor  claridad
                case 1: //Ingresar dinero
                    System.out.print("Cantidad a ingresar: ");
                    double ingreso = sc.nextDouble();
                    if (ingreso <= 0) {
                        System.out.println("Cantidad inválida."); // validación
                    } else {
                        saldo += ingreso; // actualiza saldo
                        historial[contador[0] % historial.length] = "INGRESO " + ingreso + " saldo=" + saldo;
                        contador[0]++; // CAMBIO: incrementa contador
                    }
                    break;

                case 2: // Retirar dinero
                    System.out.print("Cantidad a retirar: ");
                    double retiro = sc.nextDouble();
                    if (retiro <= 0) {
                        System.out.println("Cantidad inválida.");
                    } else if (retiro > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= retiro; //resta del saldo
                        historial[contador[0] % historial.length] = "RETIRO " + retiro + " saldo=" + saldo; // registra historial
                        contador[0]++;
                    }
                    break;

                case 3: //Consultar saldo
                    System.out.println("Saldo actual: " + saldo);
                    break;

                case 4: //Mostrar historial
                    System.out.println("--- HISTORIAL ---");
                    for (int i = 0; i < historial.length; i++) {
                        if (historial[i] != null) {
                            System.out.println(historial[i]);
                        } else {
                            System.out.println("-"); // rellena con guiones
                        }
                    }
                    break;

                case 5: //Salir
                    System.out.println("Saliendo del cajero...");
                    break;

                default: //opción invalida
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        return saldo; //Devuelve saldo actualizado
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Se cambia el nombre del Scanner
        double saldo = 500; //saldo inicial
        String[] historial = new String[5]; //historial de últimas 5 operaciones
        int[] contador = new int[]{0}; //contador de operaciones

        //pedimos PIN y validamos
        int resultadoPin = pedirPin(sc);
        if (resultadoPin == -1) {
            System.out.println("Cuenta bloqueada."); // mensaje bloqueado
            return;
        }

        // Se ejecuta menu del cajero
        saldo = menuCajero(sc, historial, contador, saldo);

        System.out.println("FIN. Saldo final: " + saldo); //imprime saldo final
        sc.close(); //cerramos scanner
    }
}

