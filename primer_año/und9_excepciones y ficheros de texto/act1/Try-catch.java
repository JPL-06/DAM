import java.util.Scanner;
import java.util.InputMismatchException;

public Integer leerEntero() {
    Scanner sc = new Scanner(System.in);
    while (true) {
        try {
            System.out.printa("Introduce un número entero: ");
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: El formato no es correcto. Inténtalo de nuevo.");
            sc.nextLine();
        }
    }
}