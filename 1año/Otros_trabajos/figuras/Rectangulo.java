package figuras;

import java.awt.Color;

/**
 * Representa un rectángulo definido por su base y su altura.
 * Esta clase extiende de {@link Figura} e implementa los métodos 
 * para calcular el área y el perímetro específicos de esta forma.
 * * @author Jesus
 * @version 1.0
 */
public class Rectangulo extends Figura {
    /** La longitud de la base del rectángulo. */
    private double base;
    /** La longitud de la altura del rectángulo. */
    private double altura;

    /**
     * Construye un nuevo Rectángulo con posición, color, base y altura.
     * * @param x      Coordenada X del centro.
     * @param y      Coordenada Y del centro.
     * @param color  El color del rectángulo.
     * @param base   La base del rectángulo.
     * @param altura La altura del rectángulo.
     */
    public Rectangulo(double x, double y, Color color, double base, double altura) {
        super(x, y, color);
        this.base = base;
        this.altura = altura;
    }

    /** @return La base actual del rectángulo. */
    public double getBase() {
        return base;
    }

    /** @return La altura actual del rectángulo. */
    public double getAltura() {
        return altura;
    }

    /** @param base Nueva longitud para la base. */
    public void setBase(double base) {
        this.base = base;
    }

    /** @param altura Nueva longitud para la altura. */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * Calcula el perímetro del rectángulo.
     * @return El resultado de la fórmula: 2 * base + 2 * altura.
     */
    @Override
    public double perimetro() {
        return 2 * base + 2 * altura;
    }

    /**
     * Calcula el área del rectángulo.
     * @return El resultado de multiplicar la base por la altura.
     */
    @Override
    public double area() {
        return base * altura;
    }
}