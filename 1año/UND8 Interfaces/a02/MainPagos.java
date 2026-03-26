
public class MainPagos {
    public static void main(String[] args) {
        MetodoPago bizum = new PagoBizum();
        MetodoPago tarjeta = new PagoTarjeta();

        System.out.println("--- PRUEBA 1: BIZUM ---");
        bizum.procesarPago(100);

        System.out.println("\n--- PRUEBA 2: TARJETA ---");
        tarjeta.procesarPago(50);
    }
}