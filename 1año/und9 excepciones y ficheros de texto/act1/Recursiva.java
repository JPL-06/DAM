import java.util.Scanner;
import java.util.InputMismatchException;

public Integer Recursivo() {
    Scanner sc = new Scanner(System.in);
    try {
        System.out.print("Introduce un número entero: ");
        return sc.nextInt();
    } catch (InputMismatchException e) {
        System.out.println("Formato incorrecto. Reintentando...");
        return Recursivo();
    }
}