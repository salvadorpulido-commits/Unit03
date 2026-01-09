package CleanCode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio3_CajaMagnifica {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        final double IVA = 0.21;
	        final double DESCUENTO_MAS_DE_100 = 0.10;
	        final double DESCUENTO_POR_SOCIO = 0.05;
	        final double UMBRAL_DESCUENTO = 100.0;

	        final int TAM_STOCK = 200;
	        final int STOCK_INICIAL = 10;

	        int[] stock = inicializarStock(TAM_STOCK, STOCK_INICIAL);

	        boolean seguirComprando = true;

	        while (seguirComprando) {
	            int numeroProductos = leerEnteroEnRango(
	                    scanner,
	                    "¿Cuántos productos vas a introducir?",
	                    1,
	                    50
	            );

	            String[] nombresProducto = new String[numeroProductos];
	            double[] preciosProducto = new double[numeroProductos];
	            int[] unidadesProducto = new int[numeroProductos];

	            double subtotal = 0.0;

	            for (int i = 0; i < numeroProductos; i++) {
	                nombresProducto[i] = leerTextoNoVacio(scanner, "Nombre del producto " + (i + 1) + ":");

	                preciosProducto[i] = leerDoubleMin(
	                        scanner,
	                        "Precio del producto " + (i + 1) + ":",
	                        0.0
	                );

	                unidadesProducto[i] = leerEnteroEnRango(
	                        scanner,
	                        "Unidades del producto " + (i + 1) + ":",
	                        1,
	                        100
	                );

	                subtotal += preciosProducto[i] * unidadesProducto[i];

	                int indexStock = obtenerIndexStock(nombresProducto[i], i, TAM_STOCK);
	                actualizarStock(stock, indexStock, unidadesProducto[i]);
	            }

	            boolean esSocio = leerSiNo(scanner, "¿Es socio?");

	            double[] totales = calcularTotales(
	                    subtotal,
	                    esSocio,
	                    UMBRAL_DESCUENTO,
	                    DESCUENTO_MAS_DE_100,
	                    DESCUENTO_POR_SOCIO,
	                    IVA
	            );

	            double descuentoSubtotal = totales[0];
	            double descuentoSocio = totales[1];
	            double baseImponible = totales[2];
	            double importeIva = totales[3];
	            double total = totales[4];

	            imprimirTicket(
	                    nombresProducto,
	                    preciosProducto,
	                    unidadesProducto,
	                    subtotal,
	                    descuentoSubtotal,
	                    descuentoSocio,
	                    baseImponible,
	                    importeIva,
	                    total
	            );

	            seguirComprando = leerSiNo(scanner, "¿Quieres registrar otra compra?");
	        }

	        scanner.close();
	    }

	    /**
	     * Inicializa un array de stock con un número de unidades inicial para cada posición.
	     *
	     * @param tamStock      tamaño del array de stock.
	     * @param stockInicial  unidades iniciales para cada posición.
	     * @return array de stock inicializado.
	     */
	    private static int[] inicializarStock(int tamStock, int stockInicial) {
	        int[] stock = new int[tamStock];
	        for (int i = 0; i < stock.length; i++) {
	            stock[i] = stockInicial;
	        }
	        return stock;
	    }

	    /**
	     * Lee un número entero y valida que esté dentro de un rango [min, max].
	     * Si el usuario introduce texto u otro tipo de dato, se captura InputMismatchException,
	     * se limpia el buffer y se repregunta.
	     *
	     * @param scanner scanner de entrada.
	     * @param mensaje mensaje a mostrar al usuario.
	     * @param min     valor mínimo permitido.
	     * @param max     valor máximo permitido.
	     * @return entero válido dentro del rango.
	     */
	    private static int leerEnteroEnRango(Scanner scanner, String mensaje, int min, int max) {
	        int valor = 0;
	        boolean valido = false;

	        while (!valido) {
	            try {
	                System.out.println(mensaje);
	                valor = scanner.nextInt();
	                scanner.nextLine(); 

	                if (valor < min || valor > max) {
	                    System.out.println("Error: el valor debe estar entre " + min + " y " + max + ".");
	                } else {
	                    valido = true;
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Error: debes introducir un número entero.");
	                scanner.nextLine();
	            }
	        }

	        return valor;
	    }

	    /**
	     * Lee un número decimal (double) y valida que sea mayor o igual que un mínimo.
	     * Si el usuario introduce texto u otro tipo de dato, se captura InputMismatchException,
	     * se limpia el buffer y se repregunta.
	     *
	     * @param scanner scanner de entrada.
	     * @param mensaje mensaje a mostrar al usuario.
	     * @param min     valor mínimo permitido.
	     * @return double válido (>= min).
	     */
	    private static double leerDoubleMin(Scanner scanner, String mensaje, double min) {
	        double valor = 0.0;
	        boolean valido = false;

	        while (!valido) {
	            try {
	                System.out.println(mensaje);
	                valor = scanner.nextDouble();
	                scanner.nextLine();

	                if (valor < min) {
	                    System.out.println("Error: el valor debe ser mayor o igual que " + min + ".");
	                } else {
	                    valido = true;
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Error: debes introducir un número (por ejemplo 12.5).");
	                scanner.nextLine(); 
	            }
	        }

	        return valor;
	    }

	    /**
	     * Lee un texto no vacío (no permite cadenas en blanco).
	     *
	     * @param scanner scanner de entrada.
	     * @param mensaje mensaje a mostrar al usuario.
	     * @return texto no vacío (trim aplicado).
	     */
	    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
	        String texto;

	        while (true) {
	            System.out.println(mensaje);
	            texto = scanner.nextLine().trim();

	            if (!texto.isEmpty()) {
	                return texto;
	            }

	            System.out.println("Error: el texto no puede estar vacío.");
	        }
	    }

	    /**
	     * Lee una respuesta S/N y devuelve true si la respuesta es S, false si es N.
	     *
	     * @param scanner scanner de entrada.
	     * @param mensaje mensaje a mostrar al usuario.
	     * @return true si S, false si N.
	     */
	    private static boolean leerSiNo(Scanner scanner, String mensaje) {
	        while (true) {
	            System.out.println(mensaje + " (S/N)");
	            String respuesta = scanner.nextLine().trim();

	            if (respuesta.equalsIgnoreCase("S")) return true;
	            if (respuesta.equalsIgnoreCase("N")) return false;

	            System.out.println("Error: escribe solo S o N.");
	        }
	    }

	    /**
	     * Calcula el índice de stock a partir del nombre del producto y su posición.
	     * NOTA: esta lógica es una simulación heredada del código sucio.
	     *
	     * @param nombreProducto nombre del producto.
	     * @param posicion       posición del producto dentro del ticket (0..n-1).
	     * @param tamStock       tamaño del stock.
	     * @return índice válido del stock.
	     */
	    private static int obtenerIndexStock(String nombreProducto, int posicion, int tamStock) {
	        return (nombreProducto.length() * 17 + posicion * 3) % tamStock;
	    }

	    /**
	     * Actualiza el stock restando unidades y muestra un aviso si queda negativo.
	     *
	     * @param stock       array de stock.
	     * @param indexStock  índice a actualizar.
	     * @param unidades    unidades a restar.
	     */
	    private static void actualizarStock(int[] stock, int indexStock, int unidades) {
	        stock[indexStock] -= unidades;
	        if (stock[indexStock] < 0) {
	            System.out.println("Aviso: stock negativo (simulación).");
	        }
	    }

	    /**
	     * Calcula descuentos, base imponible, IVA y total.
	     * Devuelve un array con:
	     * [0]=descuentoSubtotal, [1]=descuentoSocio, [2]=baseImponible, [3]=importeIva, [4]=total
	     *
	     * @param subtotal           subtotal antes de descuentos.
	     * @param esSocio            indica si el cliente es socio.
	     * @param umbral             umbral para aplicar descuento de subtotal.
	     * @param descSubtotal       descuento por superar umbral (0..1).
	     * @param descSocio          descuento adicional si es socio (0..1).
	     * @param iva                iva (0..1).
	     * @return array de 5 posiciones con los totales.
	     */
	    private static double[] calcularTotales(double subtotal, boolean esSocio,
	                                            double umbral, double descSubtotal, double descSocio, double iva) {

	        double descuento1 = subtotal > umbral ? subtotal * descSubtotal : 0.0;
	        double subtotalConDescuento = subtotal - descuento1;

	        double descuento2 = esSocio ? subtotalConDescuento * descSocio : 0.0;

	        double baseImponible = subtotalConDescuento - descuento2;
	        double importeIva = baseImponible * iva;
	        double total = baseImponible + importeIva;

	        return new double[]{descuento1, descuento2, baseImponible, importeIva, total};
	    }

	    /**
	     * Imprime un ticket con el detalle de productos y el resumen de importes.
	     *
	     * @param nombres          nombres de productos.
	     * @param precios          precios por unidad.
	     * @param unidades         unidades por producto.
	     * @param subtotal         subtotal sin descuentos.
	     * @param descuento1       descuento por superar umbral.
	     * @param descuento2       descuento por socio.
	     * @param baseImponible    base imponible.
	     * @param iva             importe de IVA.
	     * @param total            total final.
	     */
	    private static void imprimirTicket(String[] nombres, double[] precios, int[] unidades,
	                                       double subtotal, double descuento1, double descuento2,
	                                       double baseImponible, double iva, double total) {

	        System.out.println("=========== TICKET ===========");
	        for (int i = 0; i < nombres.length; i++) {
	            double linea = precios[i] * unidades[i];
	            System.out.println((i + 1) + ") " + nombres[i] + "  " + unidades[i] + " x " + precios[i] + " = " + linea);
	        }
	        System.out.println("------------------------------");
	        System.out.println("SUBTOTAL: " + subtotal);
	        System.out.println("DESC > 100: " + descuento1);
	        System.out.println("DESC SOCIO: " + descuento2);
	        System.out.println("BASE: " + baseImponible);
	        System.out.println("IVA: " + iva);
	        System.out.println("TOTAL: " + total);
	        System.out.println("==============================");
	    }
	}
