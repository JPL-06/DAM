package figuras;

import java.awt.Color;

/**
 * Representa un cuadrado, que es un tipo específico de rectángulo con lados iguales.
 * Hereda de la clase {@link Rectangulo} y utiliza sus métodos para calcular el área y el perímetro.
 * * @author Jesus
 * @version 1.0
 * @see Rectangulo
 */
public class Cuadrado extends Rectangulo {

    /**
     * Constructor para crear un cuadrado con un centro específico, color y longitud de lado.
     * Llama al constructor de la superclase Rectangulo pasando el lado como base y altura.
     * * @param x     Coordenada X del centro del cuadrado.
     * @param y     Coordenada Y del centro del cuadrado.
     * @param color Color del cuadrado.
     * @param lado  Longitud de los lados del cuadrado.
     */
    public Cuadrado(double x, double y, Color color, double lado) {
        super(x, y, color, lado, lado);
    }
}