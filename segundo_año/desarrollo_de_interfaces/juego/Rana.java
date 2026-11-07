/**
 * JUGADOR: la rana. Hereda de Personaje.
 * Se controla con los botones de la pantalla: cada pulsación es un salto de una casilla.
 */
public class Rana extends Personaje {

    private static final int PASO = Escenario.TAM_FILA;

    private final int inicioX;
    private final int inicioY;

    /** La rana se coloca centrada en la casilla (columna, fila). */
    public Rana(int columna, int fila) {
        super("imagenes/rana.png", "🐸", 0, 0, 0, 34);
        inicioX = columna * PASO + (PASO - getWidth()) / 2;
        inicioY = fila * PASO + (PASO - getHeight()) / 2;
        setLocation(inicioX, inicioY);
    }

    public void moverIzquierda() {
        if (getX() - PASO >= 0) {
            mover(-PASO, 0);
        }
    }

    public void moverDerecha(int anchoPanel) {
        if (getX() + getWidth() + PASO <= anchoPanel) {
            mover(PASO, 0);
        }
    }

    public void moverArriba() {
        if (getY() - PASO >= 0) {
            mover(0, -PASO);
        }
    }

    public void moverAbajo(int altoPanel) {
        if (getY() + getHeight() + PASO <= altoPanel) {
            mover(0, PASO);
        }
    }

    public boolean haLlegadoALaMeta() {
        return getY() < Escenario.TAM_FILA;
    }

    public void volverAlInicio() {
        setLocation(inicioX, inicioY);
    }
}
