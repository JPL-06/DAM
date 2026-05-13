package figuras;

import java.util.Scanner;
import java.awt.Color;

/**
 * Clase de prueba para gestionar figuras geométricas.
 */
public class PruebaFigura {
    public static final String Meta = "Introduzca";
    public static final String ES = "El perimetro es ";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        do {
            opcion = mostrarMenu();
            if (opcion != 4) {
                System.out.print(Meta + " la coordenada x del centro: ");
                double x = teclado.nextDouble();
                System.out.print(Meta + " la coordenada y del centro: ");
                double y = teclado.nextDouble();
                switch (opcion) {
                    case 1 -> procesarTriangulo(teclado, x, y);
                    case 2 -> procesarRectangulo(teclado, x, y);
                    case 3 -> procesarCuadrado(teclado, x, y);
                }
            }
        } while (opcion != 4);
        teclado.close();
    }

    private static void procesarTriangulo(Scanner teclado, double x, double y) {
        System.out.print(Meta + " el lado 1: ");
        double l1 = teclado.nextDouble();
        System.out.print(Meta + " el lado 2: ");
        double l2 = teclado.nextDouble();
        System.out.print(Meta + " el lado 3: ");
        double l3 = teclado.nextDouble();
        Triangulo t = new Triangulo(x, y, Color.red, l1, l2, l3);
        System.out.println(ES + t.perimetro());
    }

    private static void procesarRectangulo(Scanner teclado, double x, double y) {
        System.out.print(Meta + " la base: ");
        double b = teclado.nextDouble();
        System.out.print(Meta + " la altura: ");
        double a = teclado.nextDouble();
        Rectangulo r = new Rectangulo(x, y, Color.red, b, a);
        System.out.println(ES + r.perimetro());
    }

    private static void procesarCuadrado(Scanner teclado, double x, double y) {
        System.out.print(Meta + " el lado: ");
        double l = teclado.nextDouble();
        Cuadrado c = new Cuadrado(x, y, Color.red, l);
        System.out.println(ES + c.perimetro());
    }

    public static int mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1) Triangulo\n2) Rectangulo\n3) Cuadrado\n4) Salir");
        System.out.print(Meta + " opcion: ");
        return sc.nextInt();
    }
}