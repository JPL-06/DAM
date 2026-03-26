package a01;
public class Hora {
    int hora;
    int min;

    public Hora(int hora, int min){
        setHora(hora);
        setMinutos(min);
    }

    public int getHora(){
        return hora;
    }

    public int getMin(){
        return min;
    }

    public boolean setHora(int hora){
        if(hora >= 0 && hora < 24){
            this.hora = hora;
            return true;
        }else{
            return false;
        }
    }

    public boolean setMinutos(int min){
        if(min >= 0 && min < 60){
            this.min = min;
            return true;
        }else{
            return false;
        }
    }

    public void inc(){
        min++;
        if(min == 60){
            min = 0;
            hora++;
            if(hora == 24){
                hora = 0;
            }
        }
    }

    @Override
    public String toString(){
        return "Hora: " + hora + " Min: " + min;
    }
}