
public class PagoTarjeta implements MetodoPago {
    @Override
    public void procesarPago(double importe) {
        System.out.println("Conectando con el terminal de la tarjeta...");
        // Llamamos al método de la interfaz para completar la operación
        generarFactura(importe);
    }
}