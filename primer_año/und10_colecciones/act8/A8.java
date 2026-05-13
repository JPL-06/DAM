package dam.primer_año.und10_colecciones.act8;
import java.util.*;

public class A8{
    List<String> ListaA = new ArrayList<>(Arrays.asList("Ana", "Luis", "Marta", "Luis"));
    List<String> ListaB = new ArrayList<>(Arrays.asList("Ana", "Luis", "Marta", "Luis"));

    public A8(){
        System.out.println("Son iguales?" + ListaA.equals(ListaB));
    }

    int primerLuis = ListaB.indexOf("Luis");
    int ultimoLuis = ListaB.lastIndexOf("Luis");

    public void mostrar(){
        System.out.println("El primer Luis esta en el indice: "+primerLuis);
        System.out.println("El ultimo Luis esta en el indice: "+ultimoLuis);
    }
    ListaA.sort(null);

     System.out.println("Son iguales?" + ListaA.equals(ListaB));

     ListaB.sort(Comparator.reverseOrder());
     System.out.println("ListaB ordenada al reves: "+ListaB);
}

