import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public void ejercicio4() {
    BufferedReader br = null;
    try {

        br = new BufferedReader(new FileReader("NumerosReales.txt"));
        String linea = br.readLine();

        if (linea != null) {

            String[] partes = linea.split(" ");
            double suma = 0;
            int contador = 0;

            for (String p : partes) {

                suma += Double.valueOf(p);
                contador++;
            }

            if (contador > 0) {
                double media = suma / contador;
                System.out.println("Suma total: " + suma);
                System.out.println("Media aritmetica: " + media);
            }
        }
    } catch (IOException e) {
        System.out.println("No se pudo leer el archivo de numeros.");
    } catch (NumberFormatException e) {
        System.out.println("El archivo contiene caracteres no numericos.");
    } finally {
        try {
            if (br != null) br.close();
        } catch (IOException e) {
            System.out.println("Error final al cerrar el archivo.");
        }
    }
}