import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Numeros {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("NumerosReales.txt"))) {
            String linea = br.readLine();
            
            if (linea != null && !linea.trim().isEmpty()) {
                String[] partes = linea.split(" ");
                double suma = 0;
                int contador = 0;

                for (String p : partes) {
                    suma += Double.valueOf(p);
                    contador++;
                }

                System.out.println("Suma total: " + suma);
                System.out.println("Media: " + (suma / contador));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en los datos o en el archivo: " + e.getMessage());
        }
    }
}