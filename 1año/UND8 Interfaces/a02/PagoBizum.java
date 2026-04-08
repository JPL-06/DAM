
public class PagoBizum implements MetodoPago {
    @Override
    public void procesarPago(double importe) {
        System.out.println("Procesando pago por Bizum...");
        generarFactura(importe);
    }
}