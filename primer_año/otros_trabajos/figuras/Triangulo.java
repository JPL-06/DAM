package figuras;

import java.awt.Color;

/**
 * Representa un triángulo definido por la longitud de sus tres lados.
 * Esta clase hereda de {@link Figura} y proporciona implementaciones específicas
 * para el cálculo de área y perímetro de un triángulo.
 * * @author Jesus
 * @version 1.0
 */
public class Triangulo extends Figura {
    /** Longitud del primer lado. */
    private double lado1;
    /** Longitud del segundo lado. */
    private double lado2;
    /** Longitud del tercer lado. */
    private double lado3;

    /**
     * Construye un nuevo Triángulo con posición, color y las dimensiones de sus lados.
     * * @param x Coordenada X del centro.
     * @param y Coordenada Y del centro.
     * @param color El color del triángulo.
     * @param lado1 Longitud del lado 1.
     * @param lado2 Longitud del lado 2.
     * @param lado3 Longitud del lado 3.
     */
    public Triangulo(double x, double y, Color color, double lado1, double lado2, double lado3) {
        super(x, y, color);
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    /** @return La longitud del lado 1. */
    public double getLado1() {
        return lado1;
    }

    /** @return La longitud del lado 2. */
    public double getLado2() {
        return lado2;
    }

    /** @return La longitud del lado 3. */
    public double getLado3() {
        return lado3;
    }

    /** @param lado1 Nueva longitud para el lado 1. */
    public void setLado1(double lado1) {
        this.lado1 = lado1;
    }

    /** @param lado2 Nueva longitud para el lado 2. */
    public void setLado2(double lado2) {
        this.lado2 = lado2;
    }

    /** @param lado3 Nueva longitud para el lado 3. */
    public void setLado3(double lado3) {
        this.lado3 = lado3;
    }

    /**
     * Calcula el perímetro del triángulo sumando sus tres lados.
     * @return El perímetro total.
     */
    @Override
    public double perimetro() {
        return lado1 + lado2 + lado3;
    }

    /**
     * Calcula el área del triángulo utilizando la Fórmula de Herón.
     * Primero calcula el semiperímetro (sp) y luego aplica la raíz cuadrada
     * del producto del semiperímetro por las diferencias con cada lado.
     * * @return El área calculada del triángulo.
     */
    @Override
    public double area() {
        double sp = perimetro() / 2;
        return Math.sqrt(sp * (sp - lado1) * (sp - lado2) * (sp - lado3));
    }
}