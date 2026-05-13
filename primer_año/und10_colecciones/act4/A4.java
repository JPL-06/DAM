package dam.primer_año.und10_colecciones.act4;
import java.util.*;

public class A4 {
    Collection<Integer> num = new ArrayList<>();
    
    for(int i = 1; i < 100; i++){
        num.add((int)(Math.random() * 10));
    }

    System.out.println("Los numeros introducidos son: ");

    for (int dato : num){
        System.out.println(dato + " ");
    }

    Iterator<Integer> it = num.iterator();
    
    while(it.hasNext()){
        int n = it.next();
        if (n == 5){
            it.remove();
        }
    }
    System.out.println("Los numeros sin el 5 son: ");
    for (int cas : num){
        System.out.println(cas + " ");
    }

}
