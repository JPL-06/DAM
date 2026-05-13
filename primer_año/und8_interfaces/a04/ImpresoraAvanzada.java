package DAM.1año.UND8 Interfaces.a04;

public class ImpresoraAvanzada implements Multifuncion {
    @Override
    public void imprimir() {
        System.out.println("ImpresoraAvanzada: Imprimiendo un documento...");
    }
    @Override
    public void escanear() {
        System.out.println("ImpresoraAvanzada: Escaneando un documento...");
    }
    
}
