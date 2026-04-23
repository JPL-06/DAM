package dam.digitalizacion;

import java.util.ArrayList;

public class FogNode {
    private String name;
    private final CloudServer cloudServer;
    ArrayList<EdgeNode> edgesAsignados = new ArrayList<EdgeNode>();
    private int alertCount;
    ArrayList<SensorData> paqueteParaEnviar = new ArrayList<>();

    public FogNode(String name, CloudServer cloudServer) {
        this.name = name;
        this.cloudServer = cloudServer;
        cloudServer.getFogsAsignados().add(this);
        for (int j = 1; j <= 5; j++) {
            new EdgeNode("EDGE-" + CloudServer.edgeCounter, this);
            CloudServer.edgeCounter++;
        }
    }

    public void processData(SensorData data) {
        System.out.println("[" + name + "] Dato recibido: " + data);
        if (data.getTemperature() > 30) {
            alertCount++;
            cloudServer.incrementAlert();  // Incrementa el contador GLOBAL del sistema
            System.out.println("[" + name + "] ⚠ Temperatura alta (alerta global " + cloudServer.getTotalAlertCount() + "/20)");
        } else {
            System.out.println("[" + name + "] Temperatura normal");
        }
        paqueteParaEnviar.add(data);
        if (paqueteParaEnviar.size() == 5 || cloudServer.getTotalAlertCount() >= 20) {
            cloudServer.saveData(paqueteParaEnviar);
            paqueteParaEnviar.clear();
        }
    }

    public ArrayList<EdgeNode> getEdgesAsignados() {
        return edgesAsignados;
    }

    public String getName() {
        return name;
    }

    public CloudServer getCloudServer() {
        return cloudServer;
    }

    public int getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(int alertCount) {
        this.alertCount = alertCount;
    }
}