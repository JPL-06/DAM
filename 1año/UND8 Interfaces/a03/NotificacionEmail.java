
public class NotificacionEmail implements Notificable {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("[EMAIL] Enviando correo electrónico con el asunto 'AVISO': " + mensaje);
    }
}