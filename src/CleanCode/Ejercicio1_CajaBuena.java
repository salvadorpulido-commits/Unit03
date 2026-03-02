package CleanCode;

import java.util.Scanner; // Import scanner, en vez de *

public class Ejercicio1_CajaBuena {

    // Constantes claras
    static final double IVA = 0.21;
    static final double DESCUENTO_MAS_DE_100 = 0.10;
    static final double DESCUENTO_POR_SOCIO = 0.05;
    static final double UMBRAL_DESCUENTO = 100.0;

    static final int TAM_STOCK = 200;
    static final int STOCK_INICIAL = 10;

    // Scanner unico y nombrado correctamente para evitar confusiones.
    
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[] stock = new int[TAM_STOCK];
        for (int i = 0; i < stock.length; i++) {
            stock[i] = STOCK_INICIAL;
        }

        boolean seguirComprando = true;

        while (seguirComprando) {

            int numeroProductos = leerEnteroEnRango(
                    "¿Cuántos productos vas a introducir?", 1, 50);

            String[] nombresProducto = new String[numeroProductos];
            double[] preciosProducto = new double[numeroProductos];
            int[] unidadesProducto = new int[numeroProductos];

            double subtotal = 0.0;

            for (int i = 0; i < numeroProductos; i++) {

                nombresProducto[i] = leerNombreProducto(i + 1);

                preciosProducto[i] = leerDoubleMin(
                        "Precio del producto " + (i + 1) + ":", 0.0);

                unidadesProducto[i] = leerEnteroEnRango(
                        "Unidades del producto " + (i + 1) + ":", 1, 100);

                subtotal += preciosProducto[i] * unidadesProducto[i];

                int indexStock = obtenerIndexStock(nombresProducto[i], i, TAM_STOCK);
                stock[indexStock] -= unidadesProducto[i];

                if (stock[indexStock] < 0) {
                    System.out.println("Aviso: stock negativo (simulación).");
                }
            }

            boolean esSocio = leerSiNo("¿Es socio?");

            double[] totales = calcularTotales(
                    subtotal, esSocio,
                    UMBRAL_DESCUENTO,
                    DESCUENTO_MAS_DE_100,
                    DESCUENTO_POR_SOCIO,
                    IVA);

            imprimirTicket(
                    nombresProducto,
                    preciosProducto,
                    unidadesProducto,
                    subtotal,
                    totales[0],
                    totales[1],
                    totales[2],
                    totales[3],
                    totales[4]);

            seguirComprando = leerSiNo("¿Quieres registrar otra compra?");
        }
    }

    // Lee un entero controlando letras y rango
    static int leerEnteroEnRango(String mensaje, int min, int max) {

        int valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.println(mensaje);

            if (!sc.hasNextInt()) {
                // lanza mensaje para error de introducir datos no validos
                System.out.println("Cantidad errónea, introduzca un número válido.");
                sc.nextLine(); // limpiar entrada incorrecta
            } else {
                valor = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                if (valor < min || valor > max) {
                    System.out.println("Valor inválido. Debe estar entre " + min + " y " + max + ".");
                } else {
                    valido = true;
                }
            }
        }
        return valor;
    }

    // Lee un double controlando letras
    static double leerDoubleMin(String mensaje, double min) {

        double valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.println(mensaje);

            // lanza mensaje para error de introducir datos no validos
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

    // Lee y valida el nombre del producto
    static String leerNombreProducto(int numero) {

        String nombre;
        boolean valido;

        do {
            System.out.println("Nombre del producto " + numero + ":");
            nombre = sc.nextLine().trim();
            valido = true;

            for (int i = 0; i < nombre.length(); i++) {
                char c = nombre.charAt(i);
                if (!Character.isLetter(c) && c != ' ') {
                    valido = false;
                }
            }

            // lanza mensaje para error de introducir datos no validos
            if (!valido || nombre.isEmpty()) {
                System.out.println("Nombre inválido. Solo letras.");
            }

        } while (!valido || nombre.isEmpty());

        return nombre;
    }

    // Lee S o N
    static boolean leerSiNo(String mensaje) {

        String respuesta;

        do {
            System.out.println(mensaje + " (S/N)");
            respuesta = sc.nextLine().trim();
        } while (!respuesta.equalsIgnoreCase("S")
                && !respuesta.equalsIgnoreCase("N"));

        return respuesta.equalsIgnoreCase("S");
    }

    // Simulación de índice de stock
    static int obtenerIndexStock(String nombreProducto, int posicion, int tamStock) {
        return (nombreProducto.length() * 17 + posicion * 3) % tamStock;
    }

    // Cálculo de totales
    static double[] calcularTotales(double subtotal,
                                    boolean esSocio,
                                    double umbral,
                                    double descSubtotal,
                                    double descSocio,
                                    double iva) {

        double descuentoImporte = subtotal > umbral ? subtotal * descSubtotal : 0.0;
        double subtotalConDescuento = subtotal - descuentoImporte;

        double descuentoSocio = esSocio ? subtotalConDescuento * descSocio : 0.0;

        double baseImponible = subtotalConDescuento - descuentoSocio;
        double importeIva = baseImponible * iva;
        double total = baseImponible + importeIva;

        return new double[] {
                descuentoImporte,
                descuentoSocio,
                baseImponible,
                importeIva,
                total
        };
    }

    // Imprime el ticket
    static void imprimirTicket(String[] nombres,
                               double[] precios,
                               int[] unidades,
                               double subtotal,
                               double descuento1,
                               double descuento2,
                               double baseImponible,
                               double iva,
                               double total) {

        System.out.println("=========== TICKET ===========");

        for (int i = 0; i < nombres.length; i++) {
            double linea = precios[i] * unidades[i];
            System.out.println((i + 1) + ") " + nombres[i]
                    + "  " + unidades[i] + " x " + precios[i]
                    + " = " + linea);
        }

        System.out.println("------------------------------");
        System.out.println("SUBTOTAL: " + subtotal);
        System.out.println("DESC IMPORTE: " + descuento1);
        System.out.println("DESC SOCIO: " + descuento2);
        System.out.println("BASE: " + baseImponible);
        System.out.println("IVA: " + iva);
        System.out.println("TOTAL: " + total);
        System.out.println("==============================");
    }
}

