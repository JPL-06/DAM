package figuras;
/**
 * La clase Punto representa un punto en el plano cartesiano.
 * @author Jesus
 * @version 1.0
 */
public class Punto {
    private double x;
    private double y;
    /**
     * Constructor por defecto que inicializa el punto en el origen (0,0).
     */
    public Punto() {
        x = 0;
        y = 0;
    }

    /**
     * Constructor que inicializa el punto con las coordenadas especificadas.
     * @param x La coordenada X del punto.
     * @param y La coordenada Y del punto.
     */
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Constructor de copia que crea un nuevo punto con las mismas coordenadas que otro punto.
     * @param p El punto del cual se copiarán las coordenadas.
     */
    public Punto(Punto p) {
        x = p.x;
        y = p.y;
    }
    /**
     * Obtiene la coordenada X del punto.
     * @return La coordenada X del punto.
     */
    public double getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y del punto.
     * @return La coordenada Y del punto.
     */
    public double getY() {
        return y;
    }
    
    /**
     * Establece la coordenada X del punto.
     * @param x La nueva coordenada X del punto.
     */

    public void setX(double x) {
        this.x = x;
    }
    /**
     * Establece la coordenada Y del punto.
     * @param y La nueva coordenada Y del punto.
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * Calcula la distancia entre este punto y otro punto dado.
     * @param p El otro punto con el cual se calculará la distancia.
     * @return La distancia entre los dos puntos.
     */
    public double distancia(Punto p) {
        return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }
    /**
     * Calcula el punto simétrico de este punto respecto al eje Y.
     * @return Un nuevo punto que es el simétrico de este punto.
     */
    public Punto simetrico() {
        Punto nuevoP = new Punto(this.x * -1, this.y);
        return nuevoP;
    }
    /**
     * Compara este punto con otro punto dado para determinar si son iguales.
     * @param p El otro punto con el cual se comparará este punto.
     * @return true si los puntos son iguales, false en caso contrario.
     */
    public boolean compara(Punto p) {
        if (p.x == x && p.y == y)
            return true;
        else
            return false;
    }
    /**
     * Devuelve una representación en forma de cadena del punto.
     * @return Una cadena que representa el punto en formato "(x,y)".
     */
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }
}