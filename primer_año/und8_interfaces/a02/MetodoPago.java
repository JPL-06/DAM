
public interface MetodoPago {
    double IVA = 0.21;

    void procesarPago(double importe);

    default void logTransaccion(String mensaje) {
        System.out.println("[LOG]: " + mensaje);
    }

    default void generarFactura(double importeBase) {
        logTransaccion("Iniciando registro de factura...");
        double importeFinal = importeBase * (1 + IVA);
        System.out.println("Factura generada. Importe base: " + importeBase + "€ | Total con IVA: " + importeFinal + "€");
    }
}