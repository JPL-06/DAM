import java.awt.*;
import javax.swing.*;

public class Buscaminas extends javax.swing.JFrame {

    private JLabel Tiempo;
    private JLabel Minas;
    private JPanel panelTablero;
    private JComboBox<String> comboDificultad;

    private Timer timer;
    private int segundos = 0;

    private int numFilas = 10;
    private int numColumnas = 10;
    private int numBombas = 10;

    private int[][] casillas;
    private JButton[][] botones;
    private boolean[][] reveladas;

    public Buscaminas() {
        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UIManager.put("Button.disabledText", Color.BLACK);

        construirBarraMenu();

        setLayout(new GridBagLayout());
        GridBagConstraints gbcPrincipal = new GridBagConstraints();

        gbcPrincipal.gridx = 0;
        gbcPrincipal.weightx = 1;
        gbcPrincipal.insets = new Insets(15, 10, 15, 10);

        // Panel Superior
        gbcPrincipal.gridy = 0;
        gbcPrincipal.weighty = 0;
        gbcPrincipal.fill = GridBagConstraints.HORIZONTAL;
        JPanel superior = panelSuperior();
        add(superior, gbcPrincipal);

        // Panel Tablero
        gbcPrincipal.gridy = 1;
        gbcPrincipal.weighty = 1;
        gbcPrincipal.fill = GridBagConstraints.BOTH;
        panelTablero = new JPanel(new GridBagLayout());
        add(panelTablero, gbcPrincipal);

        iniciarOReconstruirJuego();
    }

    private void construirBarraMenu() {
        JMenuBar menuBar = new JMenuBar();

        // 1. Menú Juego
        JMenu menuJuego = new JMenu("Juego");
        JMenuItem itemNuevaPartida = new JMenuItem("Nueva partida");
        itemNuevaPartida.addActionListener(e -> reiniciarPartida());
        
        // Requisito: Sacar otro JFrame desde un menú
        JMenuItem itemAcercaDe = new JMenuItem("Instrucciones");
        itemAcercaDe.addActionListener(e -> abrirVentanaInstrucciones());

        menuJuego.add(itemNuevaPartida);
        menuJuego.add(itemAcercaDe);

        // 2. Menú Tamaño del Tablero
        JMenu menuNiveles = new JMenu("Tamaño de mapa");
        JMenuItem itemFacil = new JMenuItem("Pequeño (10x10)");
        itemFacil.addActionListener(e -> cambiarDimension(10, 10));

        JMenuItem itemMedio = new JMenuItem("Mediano (15x15)");
        itemMedio.addActionListener(e -> cambiarDimension(15, 15));

        JMenuItem itemDificil = new JMenuItem("Grande (20x20)");
        itemDificil.addActionListener(e -> cambiarDimension(20, 20));

        menuNiveles.add(itemFacil);
        menuNiveles.add(itemMedio);
        menuNiveles.add(itemDificil);

        menuBar.add(menuJuego);
        menuBar.add(menuNiveles);

        setJMenuBar(menuBar);
    }

    private void abrirVentanaInstrucciones() {
        JFrame ventanaAyuda = new JFrame("Instrucciones");
        ventanaAyuda.setSize(350, 180);
        ventanaAyuda.setLocationRelativeTo(this);
        
        JLabel texto = new JLabel("<html><body style='text-align: center; padding: 10px;'>"
                + "<h2>Buscaminas</h2>"
                + "<p>Haz clic en las casillas para despejar el campo.</p>"
                + "<p>Evita pulsar en las bombas escondidas.</p>"
                + "</body></html>", SwingConstants.CENTER);
        
        ventanaAyuda.add(texto);
        ventanaAyuda.setVisible(true);
    }

    private JPanel panelSuperior() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel Titulo = new JLabel("Buscaminas");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(Titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        Minas = new JLabel("Minas: " + numBombas);
        gbc.gridx = 0;
        panel.add(Minas, gbc);

        Tiempo = new JLabel("Tiempo: 0");
        gbc.gridx = 1;
        panel.add(Tiempo, gbc);

        // Requisito: JComboBox para seleccionar la dificultad/bombas
        String[] dificultades = {"Fácil (10 bombas)", "Medio (20 bombas)", "Difícil (35 bombas)"};
        comboDificultad = new JComboBox<>(dificultades);
        comboDificultad.addActionListener(e -> {
            int idx = comboDificultad.getSelectedIndex();
            if (idx == 0) aplicarBombas(10);
            else if (idx == 1) aplicarBombas(20);
            else if (idx == 2) aplicarBombas(35);
        });

        gbc.gridx = 2;
        panel.add(comboDificultad, gbc);

        return panel;
    }

    private void aplicarBombas(int cantidad) {
        int maxPermitido = (numFilas * numColumnas) - 1;
        this.numBombas = Math.min(cantidad, maxPermitido);
        reiniciarPartida();
    }

    private void cambiarDimension(int filas, int columnas) {
        this.numFilas = filas;
        this.numColumnas = columnas;
        int maxBombas = (numFilas * numColumnas) - 1;
        if (numBombas > maxBombas) {
            numBombas = maxBombas;
        }
        iniciarOReconstruirJuego();
    }

    private void iniciarOReconstruirJuego() {
        casillas = new int[numFilas][numColumnas];
        botones = new JButton[numFilas][numColumnas];
        reveladas = new boolean[numFilas][numColumnas];

        construirPanelTablero();
        reiniciarPartida();

        pack();
        setLocationRelativeTo(null);
    }

    private void construirPanelTablero() {
        panelTablero.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(35, 35));
                boton.setMargin(new Insets(0, 0, 0, 0));
                boton.setFont(new Font("Arial", Font.BOLD, 12));

                final int fila = i;
                final int columna = j;
                boton.addActionListener(e -> jugada(fila, columna));

                botones[i][j] = boton;

                gbc.gridx = j;
                gbc.gridy = i;
                panelTablero.add(boton, gbc);
            }
        }
        panelTablero.revalidate();
        panelTablero.repaint();
    }

    private void jugada(int fila, int columna) {
        if (reveladas[fila][columna]) return;

        if (casillas[fila][columna] == 1) {
            timer.stop();
            mostrarTodasLasMinas();
            JOptionPane.showMessageDialog(this, "Has perdido.");
            reiniciarPartida();
        } else {
            revelarCasilla(fila, columna);
            comprobarVictoria();
        }
    }

    private void iniciarTimer() {
        if (timer != null) timer.stop();
        segundos = 0;
        if (Tiempo != null) Tiempo.setText("Tiempo: 0");
        timer = new Timer(1000, e -> {
            segundos++;
            Tiempo.setText("Tiempo: " + segundos);
        });
        timer.start();
    }

    private void crearCasillas() {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                casillas[i][j] = 0;
            }
        }

        int bombasColocadas = 0;
        int maxBombas = Math.min(numBombas, (numFilas * numColumnas) - 1);
        while (bombasColocadas < maxBombas) {
            int fila = (int) (Math.random() * numFilas);
            int columna = (int) (Math.random() * numColumnas);
            if (casillas[fila][columna] == 0) {
                casillas[fila][columna] = 1;
                bombasColocadas++;
            }
        }
    }

    private void reiniciarPartida() {
        crearCasillas();
        if (Minas != null) Minas.setText("Minas: " + contarMinas());

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                botones[i][j].setText("");
                botones[i][j].setEnabled(true);
                botones[i][j].setOpaque(false);
                botones[i][j].setBackground(null);
                reveladas[i][j] = false;
            }
        }
        iniciarTimer();
    }

    private int contarBombas(int fila, int columna) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int f = fila + i, c = columna + j;
                if (f >= 0 && f < numFilas && c >= 0 && c < numColumnas && casillas[f][c] == 1) {
                    contador++;
                }
            }
        }
        return contador;
    }

    private void revelarCasilla(int fila, int columna) {
        if (fila < 0 || fila >= numFilas || columna < 0 || columna >= numColumnas) return;
        if (reveladas[fila][columna] || casillas[fila][columna] == 1) return;

        reveladas[fila][columna] = true;
        int bombasAlrededor = contarBombas(fila, columna);

        botones[fila][columna].setText(bombasAlrededor == 0 ? "" : String.valueOf(bombasAlrededor));
        botones[fila][columna].setEnabled(false);
        botones[fila][columna].setOpaque(true);
        botones[fila][columna].setBackground(new Color(220, 220, 220));

        if (bombasAlrededor == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        revelarCasilla(fila + i, columna + j);
                    }
                }
            }
        }
    }

    private void comprobarVictoria() {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (casillas[i][j] == 0 && !reveladas[i][j]) {
                    return;
                }
            }
        }

        if (timer != null) timer.stop();
        mostrarTodasLasMinas();
        JOptionPane.showMessageDialog(this, "¡Has ganado! Tiempo: " + segundos + " segundos");
        reiniciarPartida();
    }

    private void mostrarTodasLasMinas() {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (casillas[i][j] == 1) {
                    botones[i][j].setText("X");
                    botones[i][j].setForeground(Color.RED);
                    botones[i][j].setEnabled(false);
                    botones[i][j].setOpaque(true);
                    botones[i][j].setBackground(new Color(255, 180, 180));
                }
            }
        }
    }

    private int contarMinas() {
        int contador = 0;
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (casillas[i][j] == 1) contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Buscaminas ventana = new Buscaminas();
            ventana.setVisible(true);
        });
    }
}