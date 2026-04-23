package dam.digitalizacion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CloudServer cloudServer = new CloudServer();

        System.out.println("======== Simulación Cloud - Fog - Edge ========\n");

        System.out.println("Escriba el número de rondas de lecturas que quiere registrar:");
        int numRondas = sc.nextInt();

        for (int ronda = 1; ronda <= numRondas; ronda++) {
            if (cloudServer.getTotalAlertCount() >= 20) break;  // Para el sistema si ya hay 20 alertas globales
            System.out.println("\n===== Ronda " + ronda + " =====");
            for (FogNode fog : cloudServer.getFogsAsignados()) {
                for (EdgeNode edge : fog.getEdgesAsignados()) {
                    if (cloudServer.getTotalAlertCount() < 20) {
                        edge.sendData(1);
                    }
                }
            }
        }

        cloudServer.showData();

        System.out.println("Alertas detectadas en total: " + cloudServer.getTotalAlertCount());
    }
}