package a01;
public static void main(System[]args){
        Hora h1 = new Hora(10, 30);

        System.out.println("Hora inicial: " + h1);

        h1.inc();
        System.out.println("Después de incrementar un minuto: " + h1);

        if (h1.setMinutos(45)) {
            System.out.println("Minutos cambiados correctamente: " + h1);
        } else {
            System.out.println("No se pudieron cambiar los minutos");
        }

        if (h1.setMinutos(75)) {
            System.out.println("Minutos cambiados correctamente: " + h1);
        } else {
            System.out.println("No se pudieron cambiar los minutos (valor inválido)");
        }

        if (h1.setHora(22)) {
            System.out.println("Hora cambiada correctamente: " + h1);
        } else {
            System.out.println("No se pudo cambiar la hora");
        }

        if (h1.setHora(30)) {
            System.out.println("Hora cambiada correctamente: " + h1);
        } else {
            System.out.println("No se pudo cambiar la hora (valor inválido)");
        }

        Hora h2 = new Hora(23, 59);
        System.out.println("\nHora antes de incrementar: " + h2);
        h2.inc();
        System.out.println("Hora después de incrementar: " + h2);
}

