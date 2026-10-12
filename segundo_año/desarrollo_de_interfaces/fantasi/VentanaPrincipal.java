package dam.segundo_año.desarrollo_de_interfaces.fantasi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    private GestorPartida miEquipo;
    private ArrayList<Campeon> mercado;

    private PanelGrieta panelGrieta;
    private JLabel lblPresupuesto;
    private JComboBox<String> comboPosicion;
    private JPanel panelFichas;

    public VentanaPrincipal(String nombre, double dinero) {
        setTitle("LoL Fantasy - Invocador: " + nombre);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        this.mercado = GeneradorCampeones.cargarMercado();
        this.miEquipo = new GestorPartida(nombre, dinero);

        panelSuperior();
        panelCentral();
        panelLateralMercado();
    }

    private void panelSuperior() {
        JPanel sup = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        lblPresupuesto = new JLabel();
        lblPresupuesto.setFont(new Font("Arial", Font.BOLD, 18));
        actualizarTextoPresupuesto();
        sup.add(lblPresupuesto);

        JButton btnReiniciar = new JButton("Cambiar de Invocador");
        btnReiniciar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Seguro? Perderás tu plantilla y tu alineación.",
                    "Cambiar de Invocador", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                new VentanaInicio().setVisible(true);
                dispose();
            }
        });
        sup.add(btnReiniciar);

        JButton btnFinalizar = new JButton("Finalizar");
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFinalizar.addActionListener(e -> finalizar());
        sup.add(btnFinalizar);

        add(sup, BorderLayout.NORTH);
    }

    private void panelCentral() {
        panelGrieta = new PanelGrieta(this);
        add(panelGrieta, BorderLayout.CENTER);
    }

    private void panelLateralMercado() {
        JPanel panelMercado = new JPanel(new BorderLayout(5, 5));
        panelMercado.setPreferredSize(new Dimension(340, 0));
        panelMercado.setBorder(BorderFactory.createTitledBorder("Mercado de Campeones (clic para fichar)"));

        comboPosicion = new JComboBox<>(new String[]{"TODAS", "TOP", "JG", "MID", "ADC", "SUP"});
        comboPosicion.addActionListener(e -> mostrarMercado());
        panelMercado.add(comboPosicion, BorderLayout.NORTH);

        // Una ficha (botón con foto) por cada campeón, en dos columnas
        panelFichas = new JPanel(new GridLayout(0, 2, 5, 5));
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(panelFichas, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(contenedor);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelMercado.add(scroll, BorderLayout.CENTER);

        add(panelMercado, BorderLayout.EAST);
        mostrarMercado();
    }

    // Vuelve a crear las fichas del mercado según el filtro de posición
    private void mostrarMercado() {
        String filtro = (String) comboPosicion.getSelectedItem();
        panelFichas.removeAll();
        for (Campeon c : mercado) {
            if (filtro.equals("TODAS") || c.getPosiciones().contains(filtro)) {
                panelFichas.add(crearFicha(c));
            }
        }
        panelFichas.revalidate();
        panelFichas.repaint();
    }

    private JButton crearFicha(Campeon c) {
        ImageIcon foto = GeneradorCampeones.obtenerImagenRellena(c.getRutaFoto(), 80, 64);
        JButton ficha = new JButton("<html><center><b>" + c.getNombre() + "</b> (" + c.getPosiciones().get(0)
                + ")<br>Master " + c.getMastery() + "<br>" + c.getValorMercado() + "k EA</center></html>", foto);
        ficha.setPreferredSize(new Dimension(145, 130));
        ficha.setMargin(new Insets(2, 2, 2, 2));
        ficha.setVerticalTextPosition(SwingConstants.BOTTOM);
        ficha.setHorizontalTextPosition(SwingConstants.CENTER);
        ficha.addActionListener(e -> mostrarDialogoFichaje(c));
        return ficha;
    }

    // Uso de JDialog simple para confirmar fichaje
    private void mostrarDialogoFichaje(Campeon c) {
        JDialog dialog = new JDialog(this, "Confirmar Fichaje", true);
        dialog.setSize(330, 230);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 12));

        JLabel lblFoto = new JLabel();
        ImageIcon icono = GeneradorCampeones.obtenerImagenEscalada(c.getRutaFoto(), 90, 90);
        if (icono != null) {
            lblFoto.setIcon(icono);
        }

        JLabel lblInfo = new JLabel("<html><center>¿Fichar a <b>" + c.getNombre()
                + "</b>?<br>Coste: <b>" + c.getValorMercado() + "k EA</b></center></html>");

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(e -> {
            dialog.dispose();
            intentarFichar(c);
        });
        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(lblFoto);
        dialog.add(lblInfo);
        dialog.add(btnConfirmar);
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }

    public void intentarFichar(Campeon c) {
        boolean fichado = miEquipo.fichar(c);
        if (fichado) {
            mercado.remove(c);
            mostrarMercado();
            actualizarTextoPresupuesto();
            JOptionPane.showMessageDialog(this, "¡Fichaje completado! Haz clic en su rol en la Grieta para alinearlo.");
        } else {
            JOptionPane.showMessageDialog(this, "No tienes suficiente Esencia Azul para fichar a " + c.getNombre(),
                    "Fondos insuficientes", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void alinearEnBoton(JButton btn) {
        String rolRequerido = (String) btn.getClientProperty("posicion");

        ArrayList<Campeon> disponibles = new ArrayList<>();
        for (Campeon c : miEquipo.getPlantilla()) {
            if (c.getPosiciones().contains(rolRequerido) && !estaAlineado(c)) {
                disponibles.add(c);
            }
        }

        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No tienes ningún campeón libre para la posición de " + rolRequerido + ".\nFíchalo primero en el mercado.",
                    "Sin campeones", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Campeon seleccionado = (Campeon) JOptionPane.showInputDialog(
                this,
                "Selecciona tu " + rolRequerido + " titular:",
                "Alineación LoL",
                JOptionPane.PLAIN_MESSAGE,
                null,
                disponibles.toArray(),
                disponibles.get(0)
        );

        if (seleccionado != null) {
            btn.putClientProperty("campeon", seleccionado);
            btn.setBackground(new Color(255, 215, 0));

            // Foto del campeón dentro del botón, con el texto a la derecha
            btn.setIcon(GeneradorCampeones.obtenerImagenRellena(seleccionado.getRutaFoto(), 80, 80));
            btn.setHorizontalTextPosition(SwingConstants.RIGHT);

            btn.setText("<html><b>" + rolRequerido + ": " + seleccionado.getNombre() + "</b><br>"
                    + seleccionado.getClase() + " · Master " + seleccionado.getMastery() + "<br>"
                    + seleccionado.getValorMercado() + "k EA</html>");
        }
    }

    // Comprueba que hay un titular en cada rol y abre la ventana con el equipo completo
    private void finalizar() {
        ArrayList<Campeon> equipo = new ArrayList<>();
        for (JButton b : panelGrieta.getHuecosGrieta()) {
            Campeon c = (Campeon) b.getClientProperty("campeon");
            if (c != null) {
                equipo.add(c);
            }
        }

        if (equipo.size() < panelGrieta.getHuecosGrieta().size()) {
            JOptionPane.showMessageDialog(this,
                    "Te faltan titulares: tienes " + equipo.size() + " de "
                            + panelGrieta.getHuecosGrieta().size() + ".\nAlinea un campeón en cada rol.",
                    "Equipo incompleto", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Pasar parámetros a la ventana de resumen y cerrar esta con dispose()
        new VentanaResumen(miEquipo.getNombreInvocador(), equipo, miEquipo.getPresupuesto()).setVisible(true);
        dispose();
    }

    private boolean estaAlineado(Campeon c) {
        for (JButton b : panelGrieta.getHuecosGrieta()) {
            if (b.getClientProperty("campeon") == c) {
                return true;
            }
        }
        return false;
    }

    private void actualizarTextoPresupuesto() {
        lblPresupuesto.setText(String.format("Esencia Azul Disponible: %.1f k EA", miEquipo.getPresupuesto()));
    }
}
