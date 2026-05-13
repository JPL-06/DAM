package dam.primer_año.und10_colecciones.act10;

import java.util.*;
import java.util.Objects;

public class A10{
public class Socio implements Comparable<Socio> {

    private String dni;
    private String nombre;

    public Socio(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }


    @Override
    public int compareTo(Socio otro) {
        return this.dni.compareTo(otro.dni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Socio socio = (Socio) obj;
        return Objects.equals(dni, socio.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Socio{" + "DNI='" + dni + '\'' + ", Nombre='" + nombre + '\'' + '}';
    }
}
}