import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Incluye ActionEvent, ActionListener, etc.

public class Buscaminas extends JFrame implements ActionListener {

    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private static final int MINAS_TOTALES = 10;

    private JLabel lblMinas;
    private JLabel lblResultado;
    private JButton btnNuevaPartida;
    private JButton[][] botonesTablero;

    private boolean[][] minas;
    private boolean[][] descubiertas;

    private int casillasPorDescubrir;
    private boolean juegoTerminado;

    public Buscaminas() {
        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        botonesTablero = new JButton[FILAS][COLUMNAS];
        minas = new boolean[FILAS][COLUMNAS];
        descubiertas = new boolean[FILAS][COLUMNAS];

        construirPanelSuperior();
        construirPanelCentral();
        construirPanelInferior();

        iniciarPartida();

        pack();
        setLocationRelativeTo(null);
    }

    private void construirPanelSuperior() {
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTitulo = new JLabel("BUSCAMINAS");
        lblMinas = new JLabel("Minas: " + MINAS_TOTALES);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelSuperior.add(lblTitulo, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1.0;
        panelSuperior.add(lblMinas, gbc);

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.fill = GridBagConstraints.HORIZONTAL;
        gbcMain.weightx = 1.0;
        add(panelSuperior, gbcMain);
    }

    private void construirPanelCentral() {
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(40, 40));
                btn.setActionCommand(i + "," + j);
                btn.addActionListener(this);

                botonesTablero[i][j] = btn;

                gbc.gridx = j;
                gbc.gridy = i;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                panelCentral.add(btn, gbc);
            }
        }

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 1.0;
        add(panelCentral, gbcMain);
    }

    private void construirPanelInferior() {
        JPanel panelInferior = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.addActionListener(this);

        lblResultado = new JLabel("En curso");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInferior.add(btnNuevaPartida, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panelInferior.add(lblResultado, gbc);

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        gbcMain.fill = GridBagConstraints.HORIZONTAL;
        gbcMain.weightx = 1.0;
        add(panelInferior, gbcMain);
    }

    private void iniciarPartida() {
        juegoTerminado = false;
        casillasPorDescubrir = (FILAS * COLUMNAS) - MINAS_TOTALES;
        lblResultado.setText("En curso");

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                minas[i][j] = false;
                descubiertas[i][j] = false;
                botonesTablero[i][j].setText("");
                botonesTablero[i][j].setEnabled(true);
            }
        }

        colocarMinasAleatorias();
    }

    private void colocarMinasAleatorias() {
        int minasColocadas = 0;

        while (minasColocadas < MINAS_TOTALES) {
            int f = (int) (Math.random() * FILAS);
            int c = (int) (Math.random() * COLUMNAS);

            if (!minas[f][c]) {
                minas[f][c] = true;
                minasColocadas++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();

        if (origen == btnNuevaPartida) {
            iniciarPartida();
            return;
        }

        if (juegoTerminado) {
            return;
        }

        if (origen instanceof JButton) {
            JButton btnPulsado = (JButton) origen;
            String[] pos = btnPulsado.getActionCommand().split(",");
            int fila = Integer.parseInt(pos[0]);
            int col = Integer.parseInt(pos[1]);

            if (!descubiertas[fila][col]) {
                procesarJugada(fila, col);
            }
        }
    }

    private void procesarJugada(int f, int c) {
        descubiertas[f][c] = true;
        botonesTablero[f][c].setEnabled(false);

        if (minas[f][c]) {
            juegoTerminado = true;
            lblResultado.setText("¡Has perdido!");
            revelarTodasLasMinas();
            return;
        }

        casillasPorDescubrir--;

        if (casillasPorDescubrir == 0) {
            juegoTerminado = true;
            lblResultado.setText("¡Has ganado!");
            revelarTodasLasMinas();
        }
    }

    private void revelarTodasLasMinas() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (minas[i][j]) {
                    botonesTablero[i][j].setText("*");
                }
                botonesTablero[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Buscaminas().setVisible(true);
        });
    }
}