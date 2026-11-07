import java.awt.Color;

public class Coche extends Personaje {

    private static final int VELOCIDAD_MAXIMA = 7;

    private final int velocidadInicial;
    private int dx;

    private static final Color[] COLORES = {
        Color.YELLOW, Color.ORANGE, Color.CYAN, Color.PINK, Color.RED, Color.LIGHT_GRAY
    };

    private static final int ALTO = 32;

    /**
     * @param imagen           ruta de la imagen del coche
     * @param miraALaDerecha   hacia dónde mira el coche en la imagen original;
     *                         si no coincide con su sentido de marcha, se voltea
     */
    public Coche(int x, int fila, int velocidad, String imagen, boolean miraALaDerecha) {
        super(imagen, "🚗",
              x, fila * Escenario.TAM_FILA + (Escenario.TAM_FILA - ALTO) / 2,
              0, ALTO, (velocidad > 0) != miraALaDerecha);
        setForeground(COLORES[fila % COLORES.length]);
        velocidadInicial = velocidad;
        dx = velocidad;
    }

    public void moverAutomaticamente(int ancho) {
        mover(dx, 0);

        if (dx > 0 && getX() > ancho) {
            setLocation(-getWidth(), getY());
        } else if (dx < 0 && getX() + getWidth() < 0) {
            setLocation(ancho, getY());
        }
    }

    /** Aumenta un punto la velocidad, sin pasar del máximo. */
    public void acelerar() {
        if (Math.abs(dx) < VELOCIDAD_MAXIMA) {
            dx += Integer.signum(dx);
        }
    }

    public void restaurarVelocidad() {
        dx = velocidadInicial;
    }
}
