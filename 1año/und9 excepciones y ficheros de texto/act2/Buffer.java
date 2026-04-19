import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Buffer {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\JesusP\\Desktop\\visual proyects\\dam\\1año\\und9 excepciones y ficheros de texto\\act1\\Recursiva.java";


        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error con el búfer: " + e.getMessage());
        }
    }
}