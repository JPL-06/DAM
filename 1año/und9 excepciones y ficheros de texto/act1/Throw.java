import java.util.Scanner;
import java.util.InputMismatchException;

public Integer leerEnteroThrows() throws InputMismatchException {
    Scanner sc = new Scanner(System.in);
    System.out.print("Introduce un número entero: ");
    return sc.nextInt();
}