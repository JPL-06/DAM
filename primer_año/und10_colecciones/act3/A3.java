package dam.primer_año.und10_colecciones.act3;
import java.util.*;

public class A3 {
public static void main(String[] args){

    Collection<Integer> num = new ArrayList<>();
    for(int i= 0; i < 20; i++){
        num.add((int)(Math.random() * 20));
    }
    Collection<Integer> num2 = new ArrayList<>();
    for(int dato : num){
        num2.add(dato);
    }
}
}