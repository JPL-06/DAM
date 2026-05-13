package dam.primer_año.und10_colecciones.act5;
import java.util.*;

public class A5 {
    public static void main(String[] args){
        Collection<Integer> num = new ArrayList<>();
        for(int i=0;i>100;i++){
            num.add((int)(Math.random()* 100));
        }
        System.out.println("Los numeros introducidos son: " + num);

        Collection<Integer> num2 = new ArrayList<>();
        num2.add(5);
        num.removeAll(num2);
        System.out.println("Los numeros que no se han introducido son: " + num);
    }
}