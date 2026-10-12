package dam.segundo_año.desarrollo_de_interfaces.fantasi;

import java.util.ArrayList;

public class Campeon {
    private final String nombre;
    private final String clase; // Ej: Luchador, Mago, Asesino...
    private final int mastery; // Como la media del jugador
    private final ArrayList<String> posiciones; // TOP, JG, MID, ADC, SUP
    private final double valorMercado; // En miles de Esencia Azul (k EA)
    private final String rutaFoto; // URL de internet o recurso del proyecto

    public Campeon(String nombre, String clase, int mastery,
                   ArrayList<String> posiciones, double valorMercado, String rutaFoto) {
        this.nombre = nombre;
        this.clase = clase;
        this.mastery = mastery;
        this.posiciones = posiciones;
        this.valorMercado = valorMercado;
        this.rutaFoto = rutaFoto;
    }

    public String getNombre() { return nombre; }
    public String getClase() { return clase; }
    public int getMastery() { return mastery; }
    public ArrayList<String> getPosiciones() { return posiciones; }
    public double getValorMercado() { return valorMercado; }
    public String getRutaFoto() { return rutaFoto; }

    @Override
    public String toString() {
        return nombre + " (" + String.join("/", posiciones) + " - Master " + mastery + ") - " + valorMercado + "k EA";
    }
}
