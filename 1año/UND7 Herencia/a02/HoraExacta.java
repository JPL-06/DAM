package a02;
import a01.Hora;

public class HoraExacta extends Hora{
    private byte seg;
    HoraExacta(byte hora, byte min, byte seg){
        super(hora, min);
        this.seg = seg;
    }
    @Override
    public void inc(){
        seg++;
        if(seg==60){
            seg=0;
            super.inc();
        }
    }
    public boolean setSegundos(byte seg){
        boolean retorno = false;
        if(seg >=0 && seg <=59){
            this.seg = seg;
            retorno = true;
        }
        return retorno;
    }
    public byte getSeg(){
        return seg;
    }
    public void setSeg(byte seg){
        this.seg = seg;
    }
    @Override
    public String toString(){
        return String.format(super.getHora(), super.getMin(), seg);
    }
// --- ACTIVIDAD 3: Método equals ---
    @Override
    public boolean equals(Object obj) {
        // ¿Son la misma instancia de memoria?
        if (this == obj) return true;
        
        // ¿El objeto es nulo o de una clase distinta?
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // Casting para comparar los campos
        HoraExacta otraHora = (HoraExacta) obj;
        
        // Comparamos hora y minutos (del padre) y segundos (propio)
        return this.hora == otraHora.hora && 
               this.minutos == otraHora.minutos && 
               this.segundos == otraHora.segundos;
    }
}