
public class NotificacionPush implements Notificable {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("[APP-PUSH] Notificación emergente en la App: " + mensaje);
    }
}