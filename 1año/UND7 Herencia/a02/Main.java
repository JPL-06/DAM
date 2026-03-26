package a02;

public class Main {
    public static void main(String[] args) {
        
        // 1. Creamos una HoraExacta: 23:59:59 (Casi cambio de día)
        System.out.println("--- Prueba de Incremento Extremo ---");
        HoraExacta reloj = new HoraExacta(23, 59, 58);
        System.out.println("Inicio: " + reloj);

        reloj.inc(); // Debería ser 23:59:59
        System.out.println("Incremento 1: " + reloj);

        reloj.inc(); // Debería ser 00:00:00 (si Hora maneja el cambio de hora)
        System.out.println("Incremento 2 (Cambio de día): " + reloj);

        System.out.println("\n--- Prueba de setSegundos ---");
        
        // 2. Probar el método setSegundos con valores válidos e inválidos
        boolean valido = reloj.setSegundos(30);
        System.out.println("Asignar 30 seg: " + (valido ? "Éxito" : "Error") + " -> " + reloj);

        boolean invalido = reloj.setSegundos(75);
        System.out.println("Asignar 75 seg: " + (invalido ? "Éxito" : "Error") + " -> " + reloj);

        // 3. Probar métodos heredados de la clase padre (Hora)
        System.out.println("\n--- Prueba de Métodos Heredados ---");
        reloj.setMinutos(15);
        System.out.println("Cambiando minutos a 15: " + reloj);
    }
}