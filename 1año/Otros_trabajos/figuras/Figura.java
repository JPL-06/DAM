package figuras;

/**
 * La clase abstracta Figura representa una figura geométrica con un centro y un color.
 * Proporciona métodos para obtener y establecer el centro y el color, así como métodos abstractos 
 * para calcular el perímetro y el área de la figura. También incluye un método para comparar el área de esta figura 
 * con otra figura.
 * * @author Jesus
 * @version 1.0
 */
import java.awt.Color;

public abstract class Figura {
    private Punto centro;
    private Color color;

    /**
     * Constructor de la clase Figura.
     * @param x Coordenada X del centro.
     * @param y Coordenada Y del centro.
     * @param color Color de la figura.
     */
    public Figura(double x, double y, Color color) {
        centro = new Punto(x, y);
        this.color = color;
    }
    /**
     * Obtiene la coordenada X del centro de la figura.
     * @return La coordenada X del centro.
     */
    public double getXCentro() {
        return centro.getX();
    }
    /**
     * Obtiene la coordenada Y del centro de la figura.
     * @return La coordenada Y del centro.
     */
    public double getYCentro() {
        return centro.getY();
    }
        /**
        * Obtiene el color de la figura.
        * @return El color de la figura.
        */
    public Color getColor() {
        return color;
    }
    /**
     * Establece la coordenada X del centro de la figura.
     * @param x La nueva coordenada X del centro.
     */
    public void setXCentro(double x) {
        centro.setX(x);
    }
    /**
     * Establece la coordenada Y del centro de la figura.
     * @param y La nueva coordenada Y del centro.
     */
    public void setYCentro(double y) {
        centro.setY(y);
    }
    /**
     * Establece el color de la figura.
     * @param color El nuevo color de la figura.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Método abstracto para calcular el perímetro de la figura.
     * @return El perímetro de la figura.
     */
    public abstract double perimetro();
    /**
     * Método abstracto para calcular el área de la figura.
     * @return El área de la figura.
     */
    public abstract double area();
    /**
     * Compara el área de esta figura con otra figura.
     * @param otraFigura La figura con la que se comparará esta figura.
     * @return Un código entero que indica si esta figura es mayor, menor o igual en área a la otra figura:
     *         1 si esta figura es mayor, -1 si es menor, y 0 si son iguales.
     */
    public int esMayorQue(Figura otraFigura) {
        /* Compara el área de esta figura con otra figura. */
        int codigo;
        if (this.area() > otraFigura.area()) codigo = 1;
        else if (this.area() < otraFigura.area()) codigo = -1;
        else codigo = 0;
        return codigo;
    }
}