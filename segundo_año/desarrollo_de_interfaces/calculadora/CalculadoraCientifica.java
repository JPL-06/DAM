

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class CalculadoraCientifica extends JFrame {

    // Componentes de pantalla (nombres propios)
    private JTextField displayOperacion;
    private JTextField displayResultado;
    private JRadioButton rbGrados;
    private JRadioButton rbRadianes;

    // Método auxiliar para ir acumulando símbolos en pantalla
    private void anadirSimbolo(String simbolo) {
        displayOperacion.setText(displayOperacion.getText() + simbolo);
    }

    public CalculadoraCientifica() {
        // Configuración de la ventana principal
        setTitle("Calculadora Científica DAM");
        setSize(430, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fondo de la ventana principal
        getContentPane().setBackground(new Color(18, 18, 18)); // Negro antracita suave

        // Aplicamos GridBagLayout al contenedor principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Paleta de colores sobria y oscura
        Color colorFondoDisplay = new Color(28, 28, 28);       
        Color colorTextoOperacion = new Color(140, 140, 140);   
        Color colorTextoResultado = new Color(220, 220, 220);   

        Color colorBtnNumero = new Color(38, 38, 38);          
        Color colorBtnOperador = new Color(55, 55, 55);        
        Color colorBtnTrigo = new Color(48, 52, 60);           
        Color colorBtnLimpiar = new Color(120, 45, 45);        
        Color colorBtnIgual = new Color(40, 95, 60);           

        // 1. PANTALLA SUPERIOR: OPERACIÓN
        displayOperacion = new JTextField();
        displayOperacion.setEditable(false);
        displayOperacion.setHorizontalAlignment(JTextField.RIGHT);
        displayOperacion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        displayOperacion.setBackground(colorFondoDisplay);
        displayOperacion.setForeground(colorTextoOperacion);
        displayOperacion.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0.08;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(8, 8, 0, 8);
        add(displayOperacion, gbc);

        // 2. PANTALLA INFERIOR: RESULTADO
        displayResultado = new JTextField("0");
        displayResultado.setEditable(false);
        displayResultado.setHorizontalAlignment(JTextField.RIGHT);
        displayResultado.setFont(new Font("Segoe UI", Font.BOLD, 28));
        displayResultado.setBackground(colorFondoDisplay);
        displayResultado.setForeground(colorTextoResultado);
        displayResultado.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 8, 8, 8);
        add(displayResultado, gbc);

        // 3. SELECCIÓN DE UNIDAD ÁNGULAR (RadioButtons)
        rbGrados = new JRadioButton("Grados", true);
        rbRadianes = new JRadioButton("Radianes");
        configurarOpcionAngulo(rbGrados, colorTextoResultado);
        configurarOpcionAngulo(rbRadianes, colorTextoResultado);

        ButtonGroup bgModoAngulo = new ButtonGroup();
        bgModoAngulo.add(rbGrados);
        bgModoAngulo.add(rbRadianes);

        gbc.gridy = 2; gbc.gridwidth = 2; gbc.weighty = 0.04;
        gbc.gridx = 0; add(rbGrados, gbc);
        gbc.gridx = 2; add(rbRadianes, gbc);

        // Ajustes para la cuadrícula de botones
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(3, 3, 3, 3);

        // FILA 4: TRIGONOMÉTRICAS Y BOTÓN LIMPIAR
        JButton btnSeno = construirBoton("sin", colorBtnTrigo, Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 3; add(btnSeno, gbc);

        JButton btnCoseno = construirBoton("cos", colorBtnTrigo, Color.WHITE);
        gbc.gridx = 1; gbc.gridy = 3; add(btnCoseno, gbc);

        JButton btnTangente = construirBoton("tan", colorBtnTrigo, Color.WHITE);
        gbc.gridx = 2; gbc.gridy = 3; add(btnTangente, gbc);

        JButton btnBorrarTodo = construirBoton("C", colorBtnLimpiar, Color.WHITE);
        gbc.gridx = 3; gbc.gridy = 3;
        gbc.gridheight = 2; 
        gbc.fill = GridBagConstraints.BOTH;
        add(btnBorrarTodo, gbc);

        gbc.gridheight = 1; 

        // FILA 5: DÍGITOS 7, 8, 9
        JButton btnDigit7 = construirBoton("7", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 0; gbc.gridy = 4; add(btnDigit7, gbc);

        JButton btnDigit8 = construirBoton("8", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 1; gbc.gridy = 4; add(btnDigit8, gbc);

        JButton btnDigit9 = construirBoton("9", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 2; gbc.gridy = 4; add(btnDigit9, gbc);

        // FILA 6: DÍGITOS 4, 5, 6 Y DIVISIÓN
        JButton btnDigit4 = construirBoton("4", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 0; gbc.gridy = 5; add(btnDigit4, gbc);

        JButton btnDigit5 = construirBoton("5", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 1; gbc.gridy = 5; add(btnDigit5, gbc);

        JButton btnDigit6 = construirBoton("6", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 2; gbc.gridy = 5; add(btnDigit6, gbc);

        JButton btnDivision = construirBoton("/", colorBtnOperador, Color.WHITE);
        gbc.gridx = 3; gbc.gridy = 5; add(btnDivision, gbc);

        // FILA 7: DÍGITOS 1, 2, 3 Y MULTIPLICACIÓN
        JButton btnDigit1 = construirBoton("1", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 0; gbc.gridy = 6; add(btnDigit1, gbc);

        JButton btnDigit2 = construirBoton("2", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 1; gbc.gridy = 6; add(btnDigit2, gbc);

        JButton btnDigit3 = construirBoton("3", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 2; gbc.gridy = 6; add(btnDigit3, gbc);

        JButton btnMultiplicar = construirBoton("*", colorBtnOperador, Color.WHITE);
        gbc.gridx = 3; gbc.gridy = 6; add(btnMultiplicar, gbc);

        // FILA 8: 0, DECIMAL, PI Y RESTA
        JButton btnDigit0 = construirBoton("0", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 0; gbc.gridy = 7; add(btnDigit0, gbc);

        JButton btnDecimal = construirBoton(".", colorBtnNumero, colorTextoResultado);
        gbc.gridx = 1; gbc.gridy = 7; add(btnDecimal, gbc);

        JButton btnConstantePi = construirBoton("π", colorBtnTrigo, Color.WHITE);
        gbc.gridx = 2; gbc.gridy = 7; add(btnConstantePi, gbc);

        JButton btnRestar = construirBoton("-", colorBtnOperador, Color.WHITE);
        gbc.gridx = 3; gbc.gridy = 7; add(btnRestar, gbc);

        // FILA 9: PARÉNTESIS, SUMA Y BOTÓN IGUAL
        JButton btnAbrePar = construirBoton("(", colorBtnOperador, Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 8; add(btnAbrePar, gbc);

        JButton btnCierraPar = construirBoton(")", colorBtnOperador, Color.WHITE);
        gbc.gridx = 1; gbc.gridy = 8; add(btnCierraPar, gbc);

        JButton btnSumar = construirBoton("+", colorBtnOperador, Color.WHITE);
        gbc.gridx = 2; gbc.gridy = 8; add(btnSumar, gbc);

        JButton btnCalcular = construirBoton("=", colorBtnIgual, Color.WHITE);
        gbc.gridx = 3; gbc.gridy = 8;
        add(btnCalcular, gbc);

        // ASIGNACIÓN DE EVENTOS
        btnDigit0.addActionListener(e -> anadirSimbolo("0"));
        btnDigit1.addActionListener(e -> anadirSimbolo("1"));
        btnDigit2.addActionListener(e -> anadirSimbolo("2"));
        btnDigit3.addActionListener(e -> anadirSimbolo("3"));
        btnDigit4.addActionListener(e -> anadirSimbolo("4"));
        btnDigit5.addActionListener(e -> anadirSimbolo("5"));
        btnDigit6.addActionListener(e -> anadirSimbolo("6"));
        btnDigit7.addActionListener(e -> anadirSimbolo("7"));
        btnDigit8.addActionListener(e -> anadirSimbolo("8"));
        btnDigit9.addActionListener(e -> anadirSimbolo("9"));

        btnDecimal.addActionListener(e -> anadirSimbolo("."));
        btnConstantePi.addActionListener(e -> anadirSimbolo("π"));
        btnAbrePar.addActionListener(e -> anadirSimbolo("("));
        btnCierraPar.addActionListener(e -> anadirSimbolo(")"));

        btnSumar.addActionListener(e -> anadirSimbolo("+"));
        btnRestar.addActionListener(e -> anadirSimbolo("-"));
        btnMultiplicar.addActionListener(e -> anadirSimbolo("*"));
        btnDivision.addActionListener(e -> anadirSimbolo("/"));

        btnSeno.addActionListener(e -> anadirSimbolo("sin("));
        btnCoseno.addActionListener(e -> anadirSimbolo("cos("));
        btnTangente.addActionListener(e -> anadirSimbolo("tan("));

        btnBorrarTodo.addActionListener(e -> {
            displayOperacion.setText("");
            displayResultado.setText("0");
        });

        btnCalcular.addActionListener(e -> ejecutarCalculo());
    }

    // Métodos para construcción visual limpia
    private JButton construirBoton(String etiqueta, Color fondo, Color texto) {
        JButton btn = new JButton(etiqueta);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        return btn;
    }

    private void configurarOpcionAngulo(JRadioButton rb, Color texto) {
        rb.setOpaque(false);
        rb.setForeground(texto);
        rb.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rb.setFocusPainted(false);
    }

    // --- AQUÍ ESTÁ EL CAMBIO PRINCIPAL ---
    // Procesamiento de la operación delegando en la clase de tu amigo
    private void ejecutarCalculo() {
        String expresion = displayOperacion.getText();
        if (expresion.trim().isEmpty()) return;

        try {
            // Llamamos a la clase externa y le pasamos si está en grados
            double valor = EvaluarExpresion.evaluarExpresion(expresion, rbGrados.isSelected());
            
            // Formateo para quitar decimales innecesarios
            if (valor == (long) valor) {
                displayResultado.setText(String.format(Locale.US, "%d", (long) valor));
            } else {
                displayResultado.setText(String.format(Locale.US, "%.8f", valor)
                        .replaceAll("0+$", "")
                        .replaceAll("\\.$", ""));
            }
        } catch (Exception ex) {
            displayResultado.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraCientifica().setVisible(true);
        });
    }
}