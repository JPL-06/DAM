package DAM.1año.UND8 Interfaces.a04;

public interface Multifuncion implements Imprimible, Escaneable {
    @Override
    public void imprimir() {
        System.out.println("Multifuncion: Imprimiendo un documento...");
    }
    @Override
    public void escanear() {
        System.out.println("Multifuncion: Escaneando un documento...");
    }
}
