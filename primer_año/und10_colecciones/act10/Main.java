package dam.primer_año.und10_colecciones.act10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dam.primer_año.und10_colecciones.act10.A10.Socio;

public class Main {
    public static void main(String[] args) {
        
        Set<Socio> conjuntoRapido = new HashSet<>();

        conjuntoRapido.add(new Socio("1111A", "Ana"));
        conjuntoRapido.add(new Socio("2222B", "Luis"));
        conjuntoRapido.add(new Socio("3333C", "Marta"));
        conjuntoRapido.add(new Socio("1111A", "Carlos")); 

        System.out.println("¿Se ha duplicado el DNI '1111A'?");
        System.out.println("Tamaño del conjunto: " + conjuntoRapido.size()); 

        System.out.println("Contenido inicial: " + conjuntoRapido);

        Set<Socio> conjuntoOrdenado = new TreeSet<>(conjuntoRapido);
        

        conjuntoOrdenado.add(new Socio("0000Z", "Zaira")); 
        
        System.out.println("Listado ordenado automáticamente por DNI:");
        for (Socio s : conjuntoOrdenado) {
            System.out.println(s);
        }

        List<Socio> listaOrdenada = new ArrayList<>(conjuntoOrdenado);
        
        Socio segundoSocio = listaOrdenada.get(1);
        
        System.out.println("El segundo socio (ganador del premio) es:");
        System.out.println(segundoSocio);
    }
}
