package main.hilosActs2.Tarea2_8;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       Saldo saldo = new Saldo(234.55);
        System.out.println("El saldo inicial es de " + saldo.getSaldo() + "\n");

        Hilo hilo1 = new Hilo(saldo, "hilo 1");
        Hilo hilo2 = new Hilo(saldo, "hilo 2");
        Hilo hilo3 = new Hilo(saldo, "hilo 3");
        Hilo hilo4 = new Hilo(saldo, "hilo 4");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();


    }
}
