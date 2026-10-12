package dam.segundo_año.desarrollo_de_interfaces.fantasi;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {
    private JTextField txtEquipo;
    private JComboBox<Double> comboPresupuesto;

    public VentanaInicio() {
        setTitle("LoL Fantasy");
        setSize(320, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Ventana fija como pide el enunciado
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15)); // FlowLayout obligatorio

        add(new JLabel("Nombre del Invocador / Equipo:"));
        txtEquipo = new JTextField("T1 Fan", 15);
        add(txtEquipo);

        add(new JLabel("Presupuesto Inicial (k EA):"));
        Double[] presupuestos = {50.0, 80.0, 100.0, 150.0};
        comboPresupuesto = new JComboBox<>(presupuestos);
        comboPresupuesto.setSelectedIndex(1);
        add(comboPresupuesto);

        JButton btnEntrar = new JButton("Entrar a la Grieta");
        btnEntrar.addActionListener(e -> {
            String nombre = txtEquipo.getText().trim();
            double dinero = (double) comboPresupuesto.getSelectedItem();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Introduce un nombre.");
                return;
            }

            // Pasar parámetros al siguiente JFrame y cerrar esta ventana con dispose()
            new VentanaPrincipal(nombre, dinero).setVisible(true);
            this.dispose();
        });

        add(btnEntrar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaInicio().setVisible(true));
    }
}
