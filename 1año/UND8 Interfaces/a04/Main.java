package DAM.1año.UND8 Interfaces.a04;

public class Main {
public static void main (String[]args) {
    ImpresoraBasica a = new ImpresoraBasica();
    ImpresoraAvanzada b = new ImpresoraAvanzada();

    a.imprimir();
    b.escanear();
    
    a.escanear(); // No podra realizar esta accion 
}
}