package main.hilosActs2.Tarea2_9;


public class Consumidor extends Thread {
    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); // Recoge el número de la cola
            System.out.println(i + " -> Consumidor: " + n + ", consume: " + valor);

            // Pausa para ralentizar al consumidor
            try {
                sleep(200); // Pausa de 200ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}