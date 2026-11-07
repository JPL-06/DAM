import javax.swing.*;
import java.awt.*;

/**
 * Pantalla de inicio: título, instrucciones y botón Jugar.
 * No conoce el juego; cuando se pulsa Jugar ejecuta la acción que le pasan.
 */
public class PantallaInicio extends JPanel {

    public PantallaInicio(int vidas, int puntosParaGanar, Runnable alJugar) {
        super(new GridBagLayout());
        setPreferredSize(new Dimension(Escenario.ANCHO, Escenario.ALTO));
        setBackground(new Color(40, 110, 50));

        JLabel titulo = new JLabel("Cruza la carretera");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);

        JLabel instrucciones = new JLabel(
                "<html><div style='text-align:center'>"
                + "Llega a la franja verde de arriba sin que te atropelle ningún coche.<br>"
                + "Tienes " + vidas + " vidas y necesitas " + puntosParaGanar
                + " puntos para ganar.<br>"
                + "Mueve la rana con los botones ← → ↑ ↓."
                + "</div></html>");
        instrucciones.setFont(new Font("Arial", Font.PLAIN, 16));
        instrucciones.setForeground(Color.WHITE);

        JButton jugar = new JButton("Jugar");
        jugar.setFont(new Font("Arial", Font.BOLD, 20));
        jugar.addActionListener(e -> alJugar.run());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.insets = new Insets(12, 12, 12, 12);
        add(titulo, c);
        add(instrucciones, c);
        add(jugar, c);
    }
}
