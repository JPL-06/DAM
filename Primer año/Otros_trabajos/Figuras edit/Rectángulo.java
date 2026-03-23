package figuras;

import java.awt.Color;

public class Rectangulo extends Figura {
    private double base;
    private double altura;

    public Rectangulo(double x, double y, Color color, double base, double altura) {
        super(x, y, color);
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double perimetro() {
        return 2 * base + 2 * altura;
    }

    public double area() {
        return base * altura;
    }
}