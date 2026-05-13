package dam.primer_año.und10_colecciones.act2;
import java.util.*;

public class A2 {
public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    System.out.println("Introduce un numero, si es -1 se saldra del programa");
    int numero = sc.nextInt();

    Collection<Integer> num = new ArrayList<>();
    try {
        do{
            num.add(numero);
            System.out.println("Introduce un numero, si es -1 se saldra del programa");
            numero = sc.nextInt();
        }
        while (numero < -1);
    } catch (Exception e){
        System.out.println("Error: " + e.getMessage());
    }

    System.out.println("Los numeros introducidos son: " + num);
    System.out.println("Los nuemeros pares son: ");

    Iterator<Integer> it = num.iterator();

    while(it.hasNext()){
        int n = it.next();
        if(n % 2 != 0){
            System.out.println(n + " ");
        }
    }
    System.out.println("La tabla sin los multiplos de 3 son:");
    
    Iterator<Integer> it2 = num.iterator();

    while(it2.hasNext()){
        int n2 = it2.next();
        if(n2 % 3 == 0){
            it2.remove();
        }
    }
 }
}