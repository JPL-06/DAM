package figuras;

import java.util.Scanner;
import java.awt.Color;

public class PruebaFigura {
    public static final java.lang.String Meta = "Introduzca";
    public static final java.lang.String ES = "El perimetro es ";
    private static int opcion;
    private static int opcion;

    public static void main(String[] args) {
        private static int opcion;
        Scanner teclado = new Scanner(System.in);
        do {
            opcion = mostrarMenu();
            if (opcion != 4) {
                System.out.print("Meta + " la coordenada x del centro: ");
                double x = teclado.nextDouble();
                System.out.print("Meta + " la coordenada y del centro: ");
                double y = teclado.nextDouble();
                switch (opcion) {
                    case 1:
                        procesarTriangulo(teclado, x, y);
                        break;
                    case 2:
                        procesarRectangulo(teclado, x, y);
                        break;
                    case 3:
                        procesarCuadrado(teclado, x, y);
                        break;
                }
            }
        } while (opcion != 4);
        teclado.close();
    }
    private static void procesarTriangulo(Scanner teclado, double x, double y) {
        System.out.print(Meta + " el lado 1 del triangulo: ");
        double lado1 = teclado.nextDouble();

        System.out.print(Meta + " el lado 2 del triangulo: ");
        double lado2 = teclado.nextDouble();

        System.out.print(Meta + " el lado 3 del triangulo: ");
        double lado3 = teclado.nextDouble();

        Triangulo t = new Triangulo(x, y, Color.red, lado1, lado2, lado3);

        System.out.println(ES + t.perimetro());
        System.out.println("El area es " + t.area());
    }
    private static void procesarRectangulo(Scanner teclado, double x, double y) {
        System.out.print(Meta + " la base del rectangulo: ");
        double base = teclado.nextDouble();

        System.out.print(Meta + " la altura del rectangulo: ");
        double altura = teclado.nextDouble();

        Rectangulo r = new Rectangulo(x, y, Color.red, base, altura);

        System.out.println("El perimetro es " + r.perimetro());
        System.out.println("El area es " + r.area());
    }
    private static void procesarCuadrado(Scanner teclado, double x, double y) {
        System.out.print(Meta + " el lado del cuadrado: ");
        double lado = teclado.nextDouble();

        Cuadrado c = new Cuadrado(x, y, Color.red, lado);

        System.out.println("El perimetro es " + c.perimetro());
        System.out.println("El area es " + c.area());
    }
    public static int mostrarMenu() {
        int opcion;
        System.out.println("1) Triángulo");
        System.out.println("2) Rectángulo");
        System.out.println("3) Cuadrado");
        System.out.println("4) Salir");
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.print("Meta + " una opcion (1-4): ");
            opcion = teclado.nextInt();
            if (opcion < 1 || opcion > 4)
                System.out.println("Debe introducir un número entre 1 y 4");
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }
}