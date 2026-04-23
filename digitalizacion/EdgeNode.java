package dam.digitalizacion;

import java.util.Random;

public class EdgeNode {
    private final String name;
    private final FogNode fogNode;
    private final Random RANDOM = new Random();

    public EdgeNode(String name, FogNode fogNode) {
        this.name = name;
        this.fogNode = fogNode;
        fogNode.getEdgesAsignados().add(this);
    }

    public void sendData(int totalReadings) {
        for (int i = 1; i <= totalReadings; i++) {
            if (fogNode.getCloudServer().getTotalAlertCount() < 20) {
                double temperature = 20 + RANDOM.nextDouble() * 15;
                SensorData data = new SensorData(name, temperature); // El sensor ID es el nombre del edge
                System.out.println("[" + fogNode.getName() + " | " + name + "] Enviando lectura " + i + ": " + data);
                fogNode.processData(data);
                System.out.println();
            } else {
                System.out.println("[" + fogNode.getName() + " | " + name
                        + "] Registro parado, límite global de 20 alertas alcanzado.");
                break;
            }
        }
    }

    public String getName() {
        return name;
    }
}