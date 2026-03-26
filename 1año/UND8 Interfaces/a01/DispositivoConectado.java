

public interface DispositivoConectado {
    String TIPO_RED = "Wi-Fi 7";

    void encender();
    void apagar();

    default void conectar() {
        System.out.println("Conectando a la red " + TIPO_RED + "...");
    }

    static void mostrarInfoGeneral() {
        System.out.println("Aviso: Todos los dispositivos IoT deben mantenerse alejados de la humedad.");
    }
}