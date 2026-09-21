

import java.util.ArrayList;
import java.util.List;

public class EvaluarExpresion {
    public static double evaluarExpresion(String expr, boolean esGrados) {
        if (expr == null) {
            throw new IllegalArgumentException("La expresión no puede ser nula");
        }

        String entrada = expr.replace(" ", "").trim();
        if (entrada.isEmpty()) {
            throw new IllegalArgumentException("La expresión está vacía");
        }

        Parser parser = new Parser(entrada, esGrados);
        return parser.parse();
    }

    private static class Parser {
        private final String expresion;
        private final boolean grados;
        private final List<Token> tokens;
        private int indice;

        Parser(String expresion, boolean grados) {
            this.expresion = expresion;
            this.grados = grados;
            this.tokens = tokenizar(expresion);
            this.indice = 0;
        }

        double parse() {
            double resultado = parseExpresion();
            if (!fin()) {
                throw new IllegalArgumentException("Expresión no válida: " + expresion);
            }
            return resultado;
        }

        private double parseExpresion() {
            double valor = parseTermino();
            while (true) {
                if (coincide("+")) {
                    valor += parseTermino();
                } else if (coincide("-")) {
                    valor -= parseTermino();
                } else {
                    return valor;
                }
            }
        }

        private double parseTermino() {
            double valor = parsePotencia();
            while (true) {
                if (coincide("*")) {
                    valor *= parsePotencia();
                } else if (coincide("/")) {
                    double divisor = parsePotencia();
                    if (divisor == 0) {
                        throw new ArithmeticException("División por cero");
                    }
                    valor /= divisor;
                } else if (hayMultiplicacionImplicita()) {
                    valor *= parsePotencia();
                } else {
                    return valor;
                }
            }
        }

        private double parsePotencia() {
            double valor = parseUnario();
            if (coincide("^")) {
                valor = Math.pow(valor, parsePotencia());
            }
            return valor;
        }

        private double parseUnario() {
            if (coincide("+")) {
                return parseUnario();
            }
            if (coincide("-")) {
                return -parseUnario();
            }
            return parseFactor();
        }

        private double parseFactor() {
            if (fin()) {
                throw new IllegalArgumentException("Expresión incompleta");
            }

            Token token = peek();
            if (token.tipo == Tipo.NUMERO) {
                consumir();
                return token.valor;
            }
            if (token.tipo == Tipo.PI) {
                consumir();
                return Math.PI;
            }
            if (token.tipo == Tipo.LPAREN) {
                consumir();
                double valor = parseExpresion();
                esperar(Tipo.RPAREN);
                return valor;
            }
            if (token.tipo == Tipo.FUNCION) {
                consumir();
                String nombre = token.text;
                esperar(Tipo.LPAREN);
                double argumento = parseExpresion();
                esperar(Tipo.RPAREN);
                return aplicarFuncion(nombre, argumento);
            }

            throw new IllegalArgumentException("Token no esperado: " + token.text);
        }

        private boolean hayMultiplicacionImplicita() {
            if (fin()) {
                return false;
            }
            Token siguiente = peek();
            return siguiente.tipo == Tipo.NUMERO
                    || siguiente.tipo == Tipo.PI
                    || siguiente.tipo == Tipo.LPAREN
                    || siguiente.tipo == Tipo.FUNCION;
        }

        private boolean coincide(String texto) {
            if (fin()) {
                return false;
            }
            Token actual = peek();
            if (actual.text.equals(texto)) {
                indice++;
                return true;
            }
            return false;
        }

        private void esperar(Tipo tipo) {
            if (fin()) {
                throw new IllegalArgumentException("Falta cerrar un paréntesis o completar la expresión");
            }
            Token actual = peek();
            if (actual.tipo != tipo) {
                throw new IllegalArgumentException("Se esperaba " + tipo + " pero se encontró " + actual.text);
            }
            indice++;
        }

        private Token peek() {
            return tokens.get(indice);
        }

        private boolean fin() {
            return indice >= tokens.size() || tokens.get(indice).tipo == Tipo.EOF;
        }

        private void consumir() {
            if (!fin()) {
                indice++;
            }
        }

        private double aplicarFuncion(String nombre, double argumento) {
            double valor = grados ? Math.toRadians(argumento) : argumento;
            switch (nombre.toLowerCase()) {
                case "sin":
                    return Math.sin(valor);
                case "cos":
                    return Math.cos(valor);
                case "tan":
                    return Math.tan(valor);
                default:
                    throw new IllegalArgumentException("Función no soportada: " + nombre);
            }
        }

        private static List<Token> tokenizar(String entrada) {
            List<Token> tokens = new ArrayList<>();
            int i = 0;

            while (i < entrada.length()) {
                char c = entrada.charAt(i);

                if (Character.isWhitespace(c)) {
                    i++;
                    continue;
                }

                if (Character.isDigit(c) || c == '.') {
                    int inicio = i;
                    boolean punto = false;
                    while (i < entrada.length()) {
                        char ch = entrada.charAt(i);
                        if (Character.isDigit(ch)) {
                            i++;
                        } else if (ch == '.' && !punto) {
                            punto = true;
                            i++;
                        } else {
                            break;
                        }
                    }
                    String texto = entrada.substring(inicio, i);
                    tokens.add(new Token(Tipo.NUMERO, texto, Double.parseDouble(texto)));
                    continue;
                }

                if (c == 'π') {
                    tokens.add(new Token(Tipo.PI, "π", Math.PI));
                    i++;
                    continue;
                }

                if (entrada.regionMatches(true, i, "pi", 0, 2)) {
                    tokens.add(new Token(Tipo.PI, "pi", Math.PI));
                    i += 2;
                    continue;
                }

                if (Character.isLetter(c)) {
                    int inicio = i;
                    while (i < entrada.length() && Character.isLetter(entrada.charAt(i))) {
                        i++;
                    }
                    String nombre = entrada.substring(inicio, i).toLowerCase();
                    if (nombre.equals("sin") || nombre.equals("cos") || nombre.equals("tan")) {
                        tokens.add(new Token(Tipo.FUNCION, nombre, 0));
                    } else {
                        throw new IllegalArgumentException("Función desconocida: " + nombre);
                    }
                    continue;
                }

                switch (c) {
                    case '(':
                        tokens.add(new Token(Tipo.LPAREN, "(", 0));
                        i++;
                        break;
                    case ')':
                        tokens.add(new Token(Tipo.RPAREN, ")", 0));
                        i++;
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '^':
                        tokens.add(new Token(Tipo.OPERADOR, String.valueOf(c), 0));
                        i++;
                        break;
                    default:
                        throw new IllegalArgumentException("Carácter no permitido: " + c);
                }
            }

            tokens.add(new Token(Tipo.EOF, "", 0));
            return tokens;
        }
    }

    private enum Tipo {
        NUMERO, PI, FUNCION, LPAREN, RPAREN, OPERADOR, EOF
    }

    private static class Token {
        final Tipo tipo;
        final String text;
        final double valor;

        Token(Tipo tipo, String text, double valor) {
            this.tipo = tipo;
            this.text = text;
            this.valor = valor;
        }
    }
}