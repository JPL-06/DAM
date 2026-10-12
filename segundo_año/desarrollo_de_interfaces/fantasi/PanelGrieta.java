package dam.segundo_año.desarrollo_de_interfaces.fantasi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelGrieta extends JPanel {
    private final ArrayList<JButton> huecosGrieta = new ArrayList<>();

    public PanelGrieta(VentanaPrincipal ventana) {
        setLayout(new GridLayout(5, 1, 0, 5));
        setBackground(new Color(20, 40, 50));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        crearFilaRol("TOP", ventana);
        crearFilaRol("JG", ventana);
        crearFilaRol("MID", ventana);
        crearFilaRol("ADC", ventana);
        crearFilaRol("SUP", ventana);
    }

    private void crearFilaRol(String rol, VentanaPrincipal ventana) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        fila.setOpaque(false);

        JButton btn = new JButton("<html><center><b>" + rol + "</b><br>+ Añadir</center></html>");
        btn.setPreferredSize(new Dimension(300, 100));
        btn.setFocusable(false);
        btn.setBackground(new Color(245, 245, 245));

        btn.putClientProperty("posicion", rol);
        btn.putClientProperty("campeon", null);

        btn.addActionListener(e -> ventana.alinearEnBoton(btn));

        huecosGrieta.add(btn);
        fila.add(btn);
        add(fila);
    }

    public ArrayList<JButton> getHuecosGrieta() {
        return huecosGrieta;
    }
}
