package main.hilosActs2.Tarea2_9;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // NO sale el resultado esperado dado a que los consumidores consumen en negativo

        Cola cola = new Cola(); // Instancia de la cola compartida

        // Crear e iniciar hilos Productores
        Productor productor1 = new Productor(cola, 1);
        Productor productor2 = new Productor(cola, 2);

        // Crear e iniciar hilos Consumidores
        Consumidor consumidor1 = new Consumidor(cola, 1);
        Consumidor consumidor2 = new Consumidor(cola, 2);

        // Iniciar hilos
        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();


        sleep(1000);
        System.out.println("Todos los hilos han terminado.");
    }
}
