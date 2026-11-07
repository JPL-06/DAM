import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JUEGO PRINCIPAL: Cruza la carretera.
 * - La rana se controla con los botones de la pantalla.
 * - Los coches se mueven solos (Timer).
 * - Si un coche toca a la rana, pierdes una vida; si llegas a la meta, ganas un punto.
 * - Ganas al llegar a PUNTOS_PARA_GANAR; pierdes al quedarte sin vidas.
 */
public class JuegoRana extends JFrame {

    /** Estados de la partida. */
    private enum Estado { INICIO, JUGANDO, PAUSA, FIN }

    private static final int VIDAS_INICIALES = 3;
    private static final int PUNTOS_PARA_GANAR = 5;
    private static final int FILA_PRIMER_CARRIL = 1;
    private static final int FILA_ULTIMO_CARRIL = 6;

    private static final String CARTA_INICIO = "inicio";
    private static final String CARTA_JUEGO = "juego";

    // Imágenes de los coches y hacia dónde miran en la imagen original
    private static final String[] IMAGENES_COCHES = {
        "imagenes/coche1.png", "imagenes/coche2.png", "imagenes/coche3.png",
        "imagenes/coche4.png", "imagenes/coche5.png"
    };
    private static final boolean[] MIRA_A_LA_DERECHA = {
        false, false, false, true, true
    };

    private final CardLayout cartas = new CardLayout();
    private JPanel zonaCentral;
    private Escenario escenario;
    private Rana rana;
    private final List<Coche> coches = new ArrayList<>();

    private JLabel etiquetaMarcador;
    private final List<JButton> botonesMovimiento = new ArrayList<>();
    private JButton botonPausa;

    private Estado estado = Estado.INICIO;
    private int vidas = VIDAS_INICIALES;
    private int puntos = 0;

    private Timer temporizador;

    public JuegoRana() {

        setTitle("Cruza la carretera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ESCENARIO
        escenario = new Escenario();

        // Rana en la fila de inicio (última fila), en la columna 5
        rana = new Rana(5, Escenario.FILAS - 1);
        escenario.add(rana);

        crearCoches();

        // ZONA CENTRAL: pantalla de inicio o escenario (solo se ve una a la vez)
        zonaCentral = new JPanel(cartas);
        zonaCentral.add(new PantallaInicio(VIDAS_INICIALES, PUNTOS_PARA_GANAR,
                this::iniciarPartida), CARTA_INICIO);
        zonaCentral.add(escenario, CARTA_JUEGO);

        add(zonaCentral, BorderLayout.CENTER);

        // MARCADOR
        etiquetaMarcador = new JLabel();
        etiquetaMarcador.setForeground(Color.WHITE);
        etiquetaMarcador.setBackground(Color.DARK_GRAY);
        etiquetaMarcador.setOpaque(true);
        etiquetaMarcador.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaMarcador.setHorizontalAlignment(SwingConstants.CENTER);
        actualizarMarcador();

        add(etiquetaMarcador, BorderLayout.NORTH);

        // BOTONES DE MOVIMIENTO Y PAUSA
        JPanel controles = new JPanel(new FlowLayout());

        JButton izquierda = new JButton("←");
        JButton derecha = new JButton("→");
        JButton arriba = new JButton("↑");
        JButton abajo = new JButton("↓");

        izquierda.addActionListener(e -> rana.moverIzquierda());
        derecha.addActionListener(e -> rana.moverDerecha(Escenario.ANCHO));
        arriba.addActionListener(e -> {
            rana.moverArriba();
            comprobarMeta();
        });
        abajo.addActionListener(e -> rana.moverAbajo(Escenario.ALTO));

        botonesMovimiento.add(izquierda);
        botonesMovimiento.add(derecha);
        botonesMovimiento.add(arriba);
        botonesMovimiento.add(abajo);

        botonPausa = new JButton("Pausa");
        botonPausa.addActionListener(e -> alternarPausa());

        for (JButton boton : botonesMovimiento) {
            controles.add(boton);
        }
        controles.add(botonPausa);

        add(controles, BorderLayout.SOUTH);

        // Hasta pulsar Jugar, los controles están desactivados
        activarMovimiento(false);
        botonPausa.setEnabled(false);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        // MOVIMIENTO AUTOMÁTICO Y COLISIONES (se arranca al pulsar Jugar)
        temporizador = new Timer(20, e -> actualizarJuego());

        setVisible(true);
    }

    /** Dos coches por carril; los carriles alternan sentido y velocidad. */
    private void crearCoches() {
        for (int fila = FILA_PRIMER_CARRIL; fila <= FILA_ULTIMO_CARRIL; fila++) {
            int velocidad = 2 + fila % 3;
            if (fila % 2 == 0) {
                velocidad = -velocidad;
            }

            // Cada carril usa un modelo de coche distinto
            int modelo = (fila - 1) % IMAGENES_COCHES.length;

            int desfase = 40 * fila;
            for (int x : new int[] {desfase, desfase + 300}) {
                Coche coche = new Coche(x, fila, velocidad,
                        IMAGENES_COCHES[modelo], MIRA_A_LA_DERECHA[modelo]);
                coches.add(coche);
                escenario.add(coche);
            }
        }
    }

    // ESTADOS DE LA PARTIDA

    private void iniciarPartida() {
        estado = Estado.JUGANDO;
        cartas.show(zonaCentral, CARTA_JUEGO);
        escenario.setMensaje(null);
        botonPausa.setText("Pausa");
        botonPausa.setEnabled(true);
        activarMovimiento(true);
        temporizador.start();
    }

    /** Detiene o reanuda el MISMO temporizador; no se crea uno nuevo. */
    private void alternarPausa() {
        if (estado == Estado.JUGANDO) {
            temporizador.stop();
            estado = Estado.PAUSA;
            botonPausa.setText("Reanudar");
            escenario.setMensaje("PAUSA");
            activarMovimiento(false);
        } else if (estado == Estado.PAUSA) {
            estado = Estado.JUGANDO;
            botonPausa.setText("Pausa");
            escenario.setMensaje(null);
            activarMovimiento(true);
            temporizador.start();
        }
    }

    private void terminarPartida(boolean victoria) {
        estado = Estado.FIN;
        temporizador.stop();

        String texto = victoria ? "¡Has ganado!" : "Fin del juego.";
        int opcion = JOptionPane.showConfirmDialog(
                this,
                texto + " Puntos: " + puntos + "\n¿Jugar otra vez?",
                victoria ? "Victoria" : "Fin del juego",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciar();
        } else {
            System.exit(0);
        }
    }

    private void reiniciar() {
        vidas = VIDAS_INICIALES;
        puntos = 0;
        rana.volverAlInicio();
        for (Coche coche : coches) {
            coche.restaurarVelocidad();
        }
        actualizarMarcador();
        iniciarPartida();
    }

    private void activarMovimiento(boolean activo) {
        for (JButton boton : botonesMovimiento) {
            boton.setEnabled(activo);
        }
    }

    // BUCLE DEL JUEGO

    private void actualizarJuego() {
        for (Coche coche : coches) {
            coche.moverAutomaticamente(Escenario.ANCHO);
        }

        comprobarColisiones();
        escenario.repaint();
    }

    private void comprobarColisiones() {
        for (Coche coche : coches) {
            if (rana.colisionaCon(coche)) {
                procesarColision();
                return;
            }
        }
    }

    private void procesarColision() {
        vidas--;
        rana.volverAlInicio();
        actualizarMarcador();

        if (vidas <= 0) {
            terminarPartida(false);
        }
    }

    private void comprobarMeta() {
        if (rana.haLlegadoALaMeta()) {
            puntos++;
            rana.volverAlInicio();

            // Cada 2 puntos los coches van más rápido
            if (puntos % 2 == 0) {
                for (Coche coche : coches) {
                    coche.acelerar();
                }
            }
            actualizarMarcador();

            if (puntos >= PUNTOS_PARA_GANAR) {
                terminarPartida(true);
            }
        }
    }

    private void actualizarMarcador() {
        etiquetaMarcador.setText("Vidas: " + vidas
                + "    Puntos: " + puntos + " / " + PUNTOS_PARA_GANAR);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoRana());
    }
}
