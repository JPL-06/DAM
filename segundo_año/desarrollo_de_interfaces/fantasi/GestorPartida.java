package dam.segundo_año.desarrollo_de_interfaces.fantasi;

import java.util.ArrayList;

public class GestorPartida {
    private String nombreInvocador;
    private double presupuesto;
    private final ArrayList<Campeon> plantilla;

    public GestorPartida(String nombreInvocador, double presupuestoInicial) {
        this.nombreInvocador = nombreInvocador;
        this.presupuesto = presupuestoInicial;
        this.plantilla = new ArrayList<>();
    }

    public boolean fichar(Campeon c) {
        if (c.getValorMercado() > presupuesto) {
            return false;
        }
        presupuesto -= c.getValorMercado();
        plantilla.add(c);
        return true;
    }

    public void vender(Campeon c) {
        if (plantilla.remove(c)) {
            presupuesto += c.getValorMercado();
        }
    }

    public String getNombreInvocador() { return nombreInvocador; }
    public double getPresupuesto() { return presupuesto; }
    public ArrayList<Campeon> getPlantilla() { return plantilla; }
}
