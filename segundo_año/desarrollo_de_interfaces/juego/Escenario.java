import javax.swing.*;
import java.awt.*;

public class Escenario extends JPanel {

    public static final int TAM_FILA = 50;
    public static final int FILAS = 8;
    public static final int ANCHO = 600;
    public static final int ALTO = TAM_FILA * FILAS;

    // Texto grande en el centro (por ejemplo "PAUSA"); null = no se muestra
    private String mensaje;

    public Escenario() {
        super(null);
        setPreferredSize(new Dimension(ANCHO, ALTO));
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
        repaint();
    }

    // Se pinta después de los hijos para que el mensaje quede por encima de los personajes
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        if (mensaje != null) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, ALTO / 2 - 40, ANCHO, 80);

            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.WHITE);
            FontMetrics medidas = g.getFontMetrics();
            g.drawString(mensaje,
                    (ANCHO - medidas.stringWidth(mensaje)) / 2,
                    ALTO / 2 + medidas.getAscent() / 2 - 4);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Hierba de fondo (meta e inicio)
        g.setColor(new Color(60, 160, 70));
        g.fillRect(0, 0, ANCHO, ALTO);

        // Carretera (filas 1 a 6)
        g.setColor(new Color(60, 60, 60));
        g.fillRect(0, TAM_FILA, ANCHO, TAM_FILA * 6);

        // Rayas discontinuas entre carriles
        g.setColor(Color.WHITE);
        for (int fila = 2; fila <= 6; fila++) {
            int y = fila * TAM_FILA - 2;
            for (int x = 0; x < ANCHO; x += 40) {
                g.fillRect(x, y, 20, 4);
            }
        }
    }
}
