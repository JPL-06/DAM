
public class Main {
    public static void main(String[] args) {
        DispositivoConectado.mostrarInfoGeneral();

        DispositivoConectado luz = new Bombilla();
        DispositivoConectado tele = new Televisor();

        luz.encender();
        luz.conectar();

        tele.encender();
        tele.conectar();
    }
}