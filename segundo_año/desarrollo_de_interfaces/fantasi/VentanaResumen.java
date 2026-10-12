package dam.segundo_año.desarrollo_de_interfaces.fantasi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaResumen extends JFrame {

    public VentanaResumen(String invocador, ArrayList<Campeon> equipo, double presupuestoRestante) {
        setTitle("Tu equipo");
        setSize(760, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 12));

        JLabel lblTitulo = new JLabel("Este es tu equipo, " + invocador, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setPreferredSize(new Dimension(720, 40));
        add(lblTitulo);

        double gastado = 0;
        int mediaTotal = 0;
        for (Campeon c : equipo) {
            add(crearTarjeta(c));
            gastado += c.getValorMercado();
            mediaTotal += c.getMastery();
        }

        JLabel lblDatos = new JLabel(String.format("Media del equipo: %.1f   ·   Gastado: %.1f k EA   ·   Sobran: %.1f k EA",
                (double) mediaTotal / equipo.size(), gastado, presupuestoRestante), SwingConstants.CENTER);
        lblDatos.setFont(new Font("Arial", Font.BOLD, 14));
        lblDatos.setPreferredSize(new Dimension(720, 30));
        add(lblDatos);

        JButton btnNueva = new JButton("Nueva Partida");
        btnNueva.addActionListener(e -> {
            new VentanaInicio().setVisible(true);
            dispose();
        });
        add(btnNueva);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> dispose());
        add(btnSalir);
    }

    // Una tarjeta por campeón: foto, rol, nombre y datos
    private JPanel crearTarjeta(Campeon c) {
        JPanel tarjeta = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 4));
        tarjeta.setPreferredSize(new Dimension(130, 215));
        tarjeta.setBackground(new Color(255, 215, 0));
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(20, 40, 50), 2));

        JLabel foto = new JLabel(GeneradorCampeones.obtenerImagenRellena(c.getRutaFoto(), 110, 110));
        foto.setPreferredSize(new Dimension(120, 110));
        tarjeta.add(foto);
        tarjeta.add(crearTexto(c.getPosiciones().get(0), true));
        tarjeta.add(crearTexto(c.getNombre(), true));
        tarjeta.add(crearTexto(c.getClase() + " · Master " + c.getMastery(), false));
        tarjeta.add(crearTexto(c.getValorMercado() + "k EA", false));
        return tarjeta;
    }

    private JLabel crearTexto(String texto, boolean negrita) {
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", negrita ? Font.BOLD : Font.PLAIN, 13));
        etiqueta.setPreferredSize(new Dimension(126, 18));
        return etiqueta;
    }
}
