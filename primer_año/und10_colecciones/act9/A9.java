package dam.primer_año.und10_colecciones.act9;
import java.util.*;

public class A9 {
    List<Integer> numrandom = new ArrayList<>();

    public A9() {
        for (int i = 0; i < 30; i++) {
            numrandom.add((int) (Math.random() * 100));
        }
        System.out.println("La lista de numeros aleatorios es: " + numrandom);

        Set<Integer> numlimpio = new HashSet<>(numrandom);
        System.out.println("La lista sin numeros repetidos es: " + numlimpio);

        Set<Integer> numTree = new TreeSet<>(numrandom);
        System.out.println("La lista sin numeros repetidos y ordenada es: " + numTree);

        Set<Integer> numLink = new LinkedHashSet<>(numrandom);
        System.out.println("La lista sin numeros repetidos y ordenada por insercion es: " + numLink);
    }

}
