package DAM.Digitalizacion.Trabajo_primer_año;

public class FogNode {
   private final CloudServer cloudServer;
    private int alertCount;

    public FogNode(CloudServer cloudServer) {
        this.cloudServer = cloudServer;
    }
    // ejercicio dia 1
public void processData(SensorData data) {
    System.out.printf("[FOG] Dato recibido: %s%n", data);

    if (alertCount >= 20) {
        System.out.println("[FOG] Límite de alertas alcanzado");
        return;
    }

    String statusMessage = (data.getTemperature() > 30) ? "Alerta: temperatura alta" : "Temperatura normal";
    System.out.println("[FOG] " + statusMessage);

    if (data.getTemperature() > 30) alertCount++;

    cloudServer.saveData(data);
}
    public int getAlertCount() {
        return alertCount;
    }
    public void setAlertCount(int alertCount) {
        this.alertCount = alertCount;
    }
}