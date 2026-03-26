

public class Televisor implements DispositivoConectado {
    @Override
    public void encender() {
        System.out.println("Televisor: Pantalla encendida.");
    }

    @Override
    public void apagar() {
        System.out.println("Televisor: Pantalla apagada.");
    }

    @Override
    public void conectar() {

        DispositivoConectado.super.conectar();
        System.out.println("Televisor: Buscando actualizaciones de firmware...");
    }
}