package main.hilosActs2.Tarea2_8;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Saldo saldo = new Saldo(234.55);
        System.out.println("El saldo inicial es de \n" + saldo.getSaldo());

        Hilo hilo1 = new Hilo(saldo, "Hilo 1");
        Hilo hilo2 = new Hilo(saldo, "Hilo 2");
        Hilo hilo3 = new Hilo(saldo, "Hilo 3");
        Hilo hilo4 = new Hilo(saldo, "Hilo 4");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        sleep(10000);

        System.out.println("Saldo final: \n" + saldo.getSaldo());
    }
}

