package dam.primer_año.und10_colecciones.act6;
import java.util.*;

public class A6 {
    public static void main(String []args){
        Collection<Integer> num = new ArrayList<>();
        for(int i =  0; i<10; i++){
            num.add((int)(Math.random()*100));
        }
    Integer[] array = num.toArray(new Integer[0]);
    
    Arrays.sort(array);
    Arrays.asList(array);

    System.out.println("la colecion vieja es:"+num);
    System.out.println("la coleccion oredenada es :"+Arrays.toString(array));
    }

}