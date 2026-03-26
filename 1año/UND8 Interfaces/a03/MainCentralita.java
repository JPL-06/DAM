package a01;

public class MainCentralita {
    public static void main(String[] args) {

        Notificable[] canales = new Notificable[3];

        canales[0] = new NotificacionEmail();
        canales[1] = new NotificacionSMS();
        canales[2] = new NotificacionPush();

        String avisoEmergencia = "Mantenimiento urgente del servidor";

        for (Notificable canal : canales) {
            canal.enviarNotificacion(avisoEmergencia);
        }

    }
}