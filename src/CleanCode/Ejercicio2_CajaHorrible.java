package CleanCode;

import java.util.Scanner;

public class Ejercicio2_CajaHorrible {

    // Constantes con nombres claros
    static final double IVA = 0.21;
    static final double DESCUENTO_MAS_100 = 0.10;
    static final double DESCUENTO_SOCIO = 0.05;

    static final int TAM_STOCK = 200;
    static final int STOCK_INICIAL = 10;

    // Un solo Scanner para todo el programa
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Nombres de variables claros
        int numeroProductos;
        double subtotal = 0.0;

        // inicialización clara del stock
        int[] stock = new int[TAM_STOCK];
        for (int i = 0; i < stock.length; i++) {
            stock[i] = STOCK_INICIAL;
        }

        System.out.println("Caja registradora");

        String continuar = "S";

        while (continuar.equalsIgnoreCase("S")) {

            // Reutilización de método de lectura
            numeroProductos = leerEnteroEnRango("¿Número de productos?", 1, 50);

            // Arrays creados con tamaño correcto
            String[] nombres = new String[numeroProductos];
            double[] precios = new double[numeroProductos];
            int[] unidades = new int[numeroProductos];

            // uso de for en lugar de while confuso
            for (int i = 0; i < numeroProductos; i++) {

                System.out.println("Nombre del producto:");
                nombres[i] = sc.nextLine();

                // se controla que el precio no sea negativo
                precios[i] = leerDoubleMin("Precio:", 0);

                // se controla que las unidades sean válidas
                unidades[i] = leerEnteroEnRango("Unidades:", 1, 100);

                // cslculo claro del subtotal
                subtotal += precios[i] * unidades[i];

                // indice de stock separado y legible
                int indiceStock = obtenerIndiceStock(nombres[i], i);
                stock[indiceStock] -= unidades[i];

                if (stock[indiceStock] < 0) {
                    System.out.println("Aviso: stock negativo (simulación).");
                }
            }

            // Lectura clara de socio
            boolean esSocio = leerSiNo("¿Es socio?");

            // Cálculo separado en un método
            double[] totales = calcularTotales(subtotal, esSocio);

            imprimirTicket(
                    nombres,
                    precios,
                    unidades,
                    subtotal,
                    totales[0],
                    totales[1],
                    totales[2],
                    totales[3],
                    totales[4]
            );

            continuar = leerSiNo("¿Otra compra?") ? "S" : "N";

            // subtotal claramente indicado
            subtotal = 0.0;
        }

        System.out.println("Adiós");
    }

    // método para leer enteros con control de errores
    static int leerEnteroEnRango(String mensaje, int min, int max) {

        int valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.println(mensaje);

            if (!sc.hasNextInt()) {
                System.out.println("Cantidad errónea, introduzca un número válido.");
                sc.nextLine();
            } else {
                valor = sc.nextInt();
                sc.nextLine();

                if (valor < min || valor > max) {
                    System.out.println("Valor inválido. Debe estar entre " + min + " y " + max + ".");
                } else {
                    valido = true;
                }
            }
        }
        return valor;
    }

    // método para leer doubles válidos
    static double leerDoubleMin(String mensaje, double min) {

        double valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.println(mensaje);

            if (!sc.hasNextDouble()) {
                System.out.println("Cantidad errónea, introduzca un número válido.");
                sc.nextLine();
            } else {
                valor = sc.nextDouble();
                sc.nextLine();

                if (valor < min) {
                    System.out.println("Valor inválido.");
                } else {
                    valido = true;
                }
            }
        }
        return valor;
    }

    // método para leer S o N
    static boolean leerSiNo(String mensaje) {

        String respuesta;

        do {
            System.out.println(mensaje + " (S/N)");
            respuesta = sc.nextLine();
        } while (!respuesta.equalsIgnoreCase("S")
                && !respuesta.equalsIgnoreCase("N"));

        return respuesta.equalsIgnoreCase("S");
    }

    // método separado para índice de stock
    static int obtenerIndiceStock(String nombre, int posicion) {
        return (nombre.length() * 17 + posicion * 3) % TAM_STOCK;
    }

    // método exclusivo para cálculos
    static double[] calcularTotales(double subtotal, boolean esSocio) {

        double descuentoImporte = subtotal > 100 ? subtotal * DESCUENTO_MAS_100 : 0;
        double trasDescuento = subtotal - descuentoImporte;

        double descuentoSocio = esSocio ? trasDescuento * DESCUENTO_SOCIO : 0;

        double baseImponible = trasDescuento - descuentoSocio;
        double iva = baseImponible * IVA;
        double total = baseImponible + iva;

        return new double[] {
                descuentoImporte,
                descuentoSocio,
                baseImponible,
                iva,
                total
        };
    }

    //método solo para imprimir el ticket
    static void imprimirTicket(String[] nombres,
                               double[] precios,
                               int[] unidades,
                               double subtotal,
                               double d1,
                               double d2,
                               double base,
                               double iva,
                               double total) {

        System.out.println("=========== TICKET ===========");

        for (int i = 0; i < nombres.length; i++) {
            System.out.println(
                    "LIN " + (i + 1) + " -> "
                    + nombres[i] + " "
                    + unidades[i] + " "
                    + precios[i] + " "
                    + (unidades[i] * precios[i])
            );
        }

        System.out.println("SUBTOTAL = " + subtotal);
        System.out.println("DESCUENTO 1 = " + d1);
        System.out.println("DESCUENTO 2 = " + d2);
        System.out.println("BASE = " + base);
        System.out.println("IVA = " + iva);
        System.out.println("TOTAL = " + total);
        System.out.println("==============================");
    }
}
