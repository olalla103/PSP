package main.hilosActs2.Tarea2_7;

public class Ejemplo2_7 {
    public static void main(String[] args) {
        HiloRunnable hilo1 = new HiloRunnable("Hilo1");
        HiloRunnable hilo2 = new HiloRunnable("Hilo2");
        HiloRunnable hilo3 = new HiloRunnable("Hilo3");
        HiloRunnable hilo4 = new HiloRunnable("Hilo4");
        HiloRunnable hilo5 = new HiloRunnable("Hilo5");

        Thread t1 = new Thread(hilo1);
        Thread t2 = new Thread(hilo2);
        Thread t3 = new Thread(hilo3);
        Thread t4 = new Thread(hilo4);
        Thread t5 = new Thread(hilo5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


    }
}
