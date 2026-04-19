import java.io.FileReader;
import java.io.IOException;

public class Filereader {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\JesusP\\Desktop\\visual proyects\\dam\\1año\\und9 excepciones y ficheros de texto\\act1\\Recursiva.java";

        try (FileReader fr = new FileReader(ruta)) {
            int c;
            System.out.println("leyendo...");
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}