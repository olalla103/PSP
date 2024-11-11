package main.hilosActs2.Tarea2_7;

public class Ejemplo2_7 {
    public static void main(String[] args) {
        Hilo hilo1 = new Hilo("Hilo1");
        Hilo hilo2 = new Hilo("Hilo2");
        Hilo hilo3 = new Hilo("Hilo3");
        Hilo hilo4 = new Hilo("Hilo4");
        Hilo hilo5 = new Hilo("Hilo5");

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
