package main.hilosActs2.Tarea2_10;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cola cola = new Cola();

        Productor productor = new Productor(cola, 1);

        Consumidor consumidor1 = new Consumidor(cola, 1);
        Consumidor consumidor2 = new Consumidor(cola, 2);

        // Iniciar hilos
        productor.start();
        consumidor1.start();
        consumidor2.start();

        Thread.sleep(1000);

        System.out.println("Todos los hilos han terminado.");
    }
}
