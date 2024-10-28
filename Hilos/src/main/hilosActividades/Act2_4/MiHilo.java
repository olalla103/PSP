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
                    CONTADOR++;
                    if (letra == 's') {
                        suspender.set(true);
                    } else if (letra == 'r') {
                        suspender.esperandoParaReanudar();
                    }
                    sleep(500);

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

    public static void main(String[] args) {
        // ATRIBUTOS
        Scanner sc = new Scanner(System.in);
        MiHilo miHilo = new MiHilo();
        char letra;
        miHilo.start(); // inicia el hilo


        System.out.println("Introduce un único caracter");
        letra = sc.next().charAt(0);
        while (letra != '*') {
            miHilo.run(letra); // ejecuta el método `run` con el carácter especificado
            System.out.println("Introduce una letra o un símbolo, es muy importante que sea un único caracter");
            letra = sc.next().charAt(0);
            miHilo.run(letra);
        }


    }

}
