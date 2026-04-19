import java.io.FileReader;
import java.io.IOException;

public void ejercicio2() {

    String ruta = "C:\\Users\\JesusP\\Desktop\\visual proyects\\dam\\1año\\und9 excepciones y ficheros de texto\\act1\\Recursiva.java";
    FileReader fr = null;

    try {
        fr = new FileReader(ruta);
        int caracter;
        System.out.println("Contenido (FileReader)");

        while ((caracter = fr.read()) != -1) {
            System.out.print((char) caracter);
        }
    } catch (IOException e) {
        System.out.println("Error al acceder al archivo: " + e.getMessage());
    } finally {

        try {
            if (fr != null) fr.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el fichero.");
        }
    }
}