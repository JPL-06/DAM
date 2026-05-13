package dam.primer_año.und10_colecciones.act7;
import java.util.*;

public class A7 {
    public static void main(String[] args) {
        List<Integer> num = new ArrayList<>();
        num.add(0, 10);
        num.add(1, 20);
        num.add(2, 30);
        num.add(3, 40);

        List<Integer> num2 = new ArrayList<>();
        num2.add(0,99);
        num2.add(1,99);

        num.set(1, 5);

        num.addAll(2,num2);

        System.out.println("El indice 4 es: "+num.get(4));

        num.set(num.lastIndexOf(num.size()), 555);

        int numero = num.lastIndexOf(num.size());
        System.out.println("El indice es: "+numero);

        num.remove(1);
        num2.remove((Integer) 99);

        System.out.println("La coleccion num es: "+num);
    }
}
