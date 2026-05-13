import java.io.*;
import java.util.Scanner;

public class Duplicar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del archivo a duplicar: ");
        String nombreOriginal = sc.nextLine();
        String nombreCopia = "copia_de_" + nombreOriginal;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(nombreCopia))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Archivo duplicado con exito como: " + nombreCopia);

        } catch (IOException e) {
            System.out.println("Error durante la duplicacion: " + e.getMessage());
        }
    }
}