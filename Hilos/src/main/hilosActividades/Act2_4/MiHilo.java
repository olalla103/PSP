package main.hilosActividades.Act2_4;

import java.util.Scanner;

public class MiHilo extends Thread {
    SolicitaSuspender suspender = new SolicitaSuspender();
    private boolean stopHilo = false;
    private long CONTADOR = 0;

    public void Suspende() {
        suspender.set(true);
    }

    public void Reanuda() {
        suspender.set(false);
    }

    public void run(char letra) {
        try {
            // EJECUCIÓN
            if (letra != '*') {
                try {
                    if (letra == 's') {
                        suspender.set(true);
                    } else if (letra == 'r') {
                        suspender.set(false);
                        suspender.esperandoParaReanudar();
                    } else {
                        CONTADOR++;
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(getCONTADOR());
                stopHilo = true;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public long getCONTADOR() {
        return CONTADOR;
    }

}


