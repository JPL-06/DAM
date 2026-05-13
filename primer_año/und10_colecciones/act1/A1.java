package dam.primer_año.und10_colecciones.act1;
import java.util.Arrays;

public class A1 {
    public static void main(String[] args){
        Integer[] tabla = new Integer[]{3,2,5,7,11,13,17,19,23,29};
        int dato = 0;

        System.out.println("Tabla de enteros: " + Arrays.toString(tabla));
        tabla = guardar(tabla, dato);

    }
    public static <E> E[] guardar(E[] tabla, E dato){
        E[] tablafinal = Arrays.copyOf(tabla, tabla.length + 1);
        tablafinal[tabla.length] = dato;
        return tablafinal;
    }
}
