package a04;

abstract class Instrumento {
    protected Notas[] notas;
    protected int num;

    public void setNotas(Notas[] notas) {
        if(notas.length <= 7 && notas.length >= 0){
            this.notas = notas;
            this.num= notas.length;
            for(int i = 0; i < notas.length; i++){
                if(notas[i] == null){
                    this.num++;
                }
            }
        } else {
            System.out.println("No se pueden establecer más notas");
        }
    }
    
    void add(){
        if (num < 7) {
            num ++;
            notas[num] = notas;
        } else{
            System.out.println("No se pueden añadir más notas");
        }
    }
    public abstract void Implementar();
    
            public void setNumNotas(int numNotas) {
            if(num >= 0 && num <= 7){
                this.num = num;
            } else {
                System.out.println("El número de notas debe estar entre 0 y 7.");
            }
        
    }


}
