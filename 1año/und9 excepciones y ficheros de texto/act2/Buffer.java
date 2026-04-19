import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public void ejercicio3() {
    String ruta = "C:\\Users\\JesusP\\Desktop\\visual proyects\\dam\\1año\\und9 excepciones y ficheros de texto\\act1\\Recursiva.java";
    BufferedReader br = null;

    try {
        br = new BufferedReader(new FileReader(ruta));
        String linea;
        System.out.println(" Contenido (BufferedReader)");
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
    } catch (IOException e) {
        System.out.println("Error en la lectura con bufer: " + e.getMessage());
    } finally {
        try {
            if (br != null) br.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el bufer.");
        }
    }
}