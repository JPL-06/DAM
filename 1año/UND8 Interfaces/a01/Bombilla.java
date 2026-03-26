
public class Bombilla implements DispositivoConectado {
    @Override
    public void encender() {
        System.out.println("Bombilla: ¡Encendida!");
    }

    @Override
    public void apagar() {
        System.out.println("Bombilla: ¡Apagada!");
    }
}