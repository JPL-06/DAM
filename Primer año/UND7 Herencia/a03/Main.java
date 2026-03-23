package a03;

public class Main {
    public static void main(String[] args) {
        HoraExacta h1 = new HoraExacta(10, 30, 15);
        HoraExacta h2 = new HoraExacta(10, 30, 15);
        HoraExacta h3 = new HoraExacta(11, 0, 0);

        System.out.println("Hora 1: " + h1);
        System.out.println("Hora 2: " + h2);
        System.out.println("Hora 3: " + h3);

        // Prueba del método equals
        System.out.println("\n¿H1 es igual a H2? " + h1.equals(h2)); // true
        System.out.println("¿H1 es igual a H3? " + h1.equals(h3)); // false
    }
}