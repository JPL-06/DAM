package dam.digitalizacion;

import java.util.ArrayList;
import java.util.List;

public class CloudServer {
    private final List<SensorData> dataList = new ArrayList<>();
    private ArrayList<FogNode> fogsAsignados = new ArrayList<FogNode>();
    static int edgeCounter = 1;
    private int totalAlertCount = 0;  // Contador global de alertas en todo el sistema

    public CloudServer() {
        for (int i = 1; i <= 5; i++) {
            new FogNode("FOG-" + i, this);
        }
    }

    public void saveData(ArrayList<SensorData> paqueteDeDatos) {
        for(SensorData data: paqueteDeDatos) {
            try {
                Thread.sleep(2000); // 2000 ms = 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dataList.add(data);
            System.out.println("[CLOUD] Dato guardado: " + data);
        }
    }

    public ArrayList<FogNode> getFogsAsignados() {
        return fogsAsignados;
    }

    public int getTotalAlertCount() {
        return totalAlertCount;
    }

    public void incrementAlert() {
        totalAlertCount++;
    }

    public void showData() {
        double sum = 0;
        double average;

        System.out.println("\n[CLOUD] Historial de datos:");

        if (dataList.isEmpty()) {
            System.out.println("No hay datos guardados");
            return;
        }

        for (SensorData data : dataList) {
            System.out.println(data);
            sum += data.getTemperature();
        }

        average = sum / dataList.size();
        System.out.println("Media de temperaturas: " + String.format("%.2f", average));
    }
}